package ru.nsu.sharapov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Graph represented by adjacency list.
 */
public class AdjacencyListGraph extends AbstractGraph {

    private final Map<Integer, Set<Integer>> adj;
    private final Set<Edge> edges;

    /**
     * Constructor.
     */
    public AdjacencyListGraph() {
        adj = new HashMap<>();
        edges = new HashSet<>();
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
            adj.size(), edges.size(), getNodes(), getEdges());
    }

    @Override
    public Integer getNodesCount() {
        return adj.size();
    }

    @Override
    public void addNode(Integer node) {
        if (!adj.containsKey(node)) {
            adj.put(node, new HashSet<>());
        }
    }

    @Override
    public void removeNode(Integer node) {
        for (Map.Entry<Integer, Set<Integer>> entry : adj.entrySet()) {
            if (Objects.equals(entry.getKey(), node)) {
                for (int j : entry.getValue()) {
                    removeEdge(new Edge(node, j));
                }
            } else {
                removeEdge(new Edge(entry.getKey(), node));
            }
        }
        adj.remove(node);
    }

    @Override
    public void addEdge(Edge edge) {
        if (!edges.contains(edge)) {
            addNode(edge.from());
            addNode(edge.to());
            adj.get(edge.from()).add(edge.to());
            edges.add(edge);
        }
    }

    @Override
    public void removeEdge(Edge edge) {
        if (edges.contains(edge)) {
            adj.get(edge.from()).remove(edge.to());
            edges.remove(edge);
        }
    }

    @Override
    public Set<Integer> getNeighbours(Integer node) {
        if (adj.containsKey(node)) {
            return adj.get(node);
        }
        return new HashSet<>();
    }

    @Override
    public Set<Edge> getEdges() {
        return edges;
    }

    @Override
    public Set<Integer> getNodes() {
        return adj.keySet();
    }
}
