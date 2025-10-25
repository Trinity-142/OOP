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
 * Graph represented by adjacency matrix.
 */
public class AdjacencyMatrixGraph extends AbstractGraph {

    private final List<List<Boolean>> adj;
    private final Map<Integer, Integer> nodeToIndex;
    private final Set<Edge> edges;

    /**
     * Constructor.
     */
    public AdjacencyMatrixGraph() {
        adj = new ArrayList<>();
        nodeToIndex = new HashMap<>();
        edges = new HashSet<>();
    }

    @Override
    public void readFromFile(String filename) {
        this.fillFromFile(filename);
    }

    @Override
    public String toString() {
        return String.format("Graph with %d nodes and %d edges.\nNodes: %s\nEdges: %s\n",
            nodeToIndex.size(), edges.size(), getNodes(), getEdges());
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
        for (int i = 0; i < nodeToIndex.size() - 1; ++i) {
            adj.getLast().add(false);
        }
        for (List<Boolean> adj : adj) {
            adj.add(false);
        }
    }

    @Override
    public void removeNode(Integer node) {
        if (!nodeToIndex.containsKey(node)) {
            return;
        }

        for (int i = 0; i < adj.size(); ++i) {
            removeEdge(new Edge(node, i));
        }
        for (int i = 0; i < adj.size(); ++i) {
            removeEdge(new Edge(i, node));
        }
        nodeToIndex.remove(node);
    }

    @Override
    public void addEdge(Edge edge) {
        if (edges.contains(edge)) {
            return;
        }

        addNode(edge.from());
        addNode(edge.to());
        Integer fromIndex = nodeToIndex.get(edge.from());
        Integer toIndex = nodeToIndex.get(edge.to());
        adj.get(fromIndex).set(toIndex, true);
        edges.add(edge);
    }

    @Override
    public void removeEdge(Edge edge) {
        if (!edges.contains(edge)) {
            return;
        }

        Integer fromIndex = nodeToIndex.get(edge.from());
        Integer toIndex = nodeToIndex.get(edge.to());
        adj.get(fromIndex).set(toIndex, false);
        edges.remove(edge);
    }

    @Override
    public Set<Integer> getNeighbours(Integer node) {
        Set<Integer> res = new HashSet<>();
        if (!nodeToIndex.containsKey(node)) {
            return res;
        }

        for (int i : nodeToIndex.keySet()) {
            Integer neighbourIndex = nodeToIndex.get(i);
            Integer nodeIndex = nodeToIndex.get(node);
            if (adj.get(nodeIndex).get(neighbourIndex)) {
                res.add(i);
            }
        }
        return res;
    }

    @Override
    public Set<Edge> getEdges() {
        return edges;
    }

    @Override
    public Set<Integer> getNodes() {
        return nodeToIndex.keySet();
    }
}
