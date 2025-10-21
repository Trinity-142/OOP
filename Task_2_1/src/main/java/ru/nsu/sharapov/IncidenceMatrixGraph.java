package ru.nsu.sharapov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Graph represented by incidence matrix.
 */
public class IncidenceMatrixGraph extends AbstractGraph {

    private final List<List<Integer>> adj;
    private final Map<Edge, Integer> edgeToIndex;
    private final Map<Integer, Integer> nodeToIndex;

    /**
     * Constructor.
     */
    public IncidenceMatrixGraph() {
        adj = new ArrayList<>();
        edgeToIndex = new HashMap<>();
        nodeToIndex = new HashMap<>();
    }

    @Override
    public <T extends Graph> T readFromFile(String filename, Class<T> graphType) {
        T graph = super.readFromFile(filename, graphType);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String str;
            while ((str = reader.readLine()) != null) {
                String[] from_to = str.split(" ");
                Integer from = Integer.parseInt(from_to[0]);
                Integer to = Integer.parseInt(from_to[1]);
                graph.addEdge(new Edge(from, to));
            }
            return graph;

        } catch (IOException e) {
            throw new RuntimeException("File error: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return String.format("Graph with %d nodes and %d edges.\nNodes: %s\nEdges: %s\n",
            nodeToIndex.size(), edgeToIndex.size(), getNodes(), getEdges());
    }

    @Override
    public Integer getNodesCount() {
        return nodeToIndex.size();
    }

    @Override
    public void addNode(Integer node) {
        if (nodeToIndex.containsKey(node)) {
            return;
        }

        nodeToIndex.put(node, adj.size());
        adj.add(new ArrayList<>());
        for (int i = 0; i < edgeToIndex.size(); ++i) {
            adj.getLast().add(0);
        }
    }


    @Override
    public void removeNode(Integer node) {
        if (!nodeToIndex.containsKey(node)) {
            return;
        }

        Integer nodeIndex = nodeToIndex.get(node);
        for (int i = 0; i < nodeToIndex.size(); ++i) {
            removeEdge(new Edge(nodeIndex, i));
            removeEdge(new Edge(i, nodeIndex));
        }
        nodeToIndex.remove(node);
    }

    @Override
    public void addEdge(Edge edge) {
        if (edgeToIndex.containsKey(edge)) {
            return;
        }

        addNode(edge.from());
        addNode(edge.to());
        for (int i = 0; i < nodeToIndex.size(); ++i) {
            adj.get(i).add(0);
        }
        Integer fromIndex = nodeToIndex.get(edge.from());
        Integer toIndex = nodeToIndex.get(edge.to());
        adj.get(fromIndex).set(edgeToIndex.size(), 1);
        adj.get(toIndex).set(edgeToIndex.size(), -1);
        edgeToIndex.put(edge, edgeToIndex.size());
    }

    @Override
    public void removeEdge(Edge edge) {
        if (!edgeToIndex.containsKey(edge)) {
            return;
        }

        for (int i = 0; i < nodeToIndex.size(); ++i) {
            adj.get(i).set(edgeToIndex.get(edge), 0);
        }
        edgeToIndex.remove(edge);
    }

    @Override
    public Set<Integer> getNeighbours(Integer node) {
        Set<Integer> res = new HashSet<>();
        if (!nodeToIndex.containsKey(node)) {
            return res;
        }

        for (int i = 0; i < edgeToIndex.size(); ++i) {
            Integer nodeIndex = nodeToIndex.get(node);
            if (adj.get(nodeIndex).get(i) == 1) {
                for (int j : nodeToIndex.keySet()) {
                    if (adj.get(nodeToIndex.get(j)).get(i) == -1) {
                        res.add(j);
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Set<Edge> getEdges() {
        return edgeToIndex.keySet();
    }

    @Override
    public Set<Integer> getNodes() {
        return nodeToIndex.keySet();
    }
}
