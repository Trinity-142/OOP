package ru.nsu.sharapov;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AdjacencyListGraph extends AbstractGraph {

    private final Map<Integer, Set<Integer>> adj = new HashMap<>();

    /**
     * Constructor.
     *
     * @param N number of nodes
     * @param E number of edges
     */
    public AdjacencyListGraph(Integer N, Integer E) {
        super(N, E);
        for (int i = 0; i < N; ++i) {
            adj.put(i, new HashSet<>());
        }
    }

    /**
     * Adds node to graph.
     *
     * @param node node to add
     */
    @Override
    public void addNode(Integer node) {
        nodes.add(node);
    }

    /**
     * Removes node from graph.
     *
     * @param node node to remove
     */
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
        nodes.remove(node);
    }

    /**
     * Adds edge to graph.
     *
     * @param edge edge to add
     */
    @Override
    public void addEdge(Edge edge) {
        addNode(edge.from());
        addNode(edge.to());
        adj.get(edge.from()).add(edge.to());
        edges.add(edge);
    }

    /**
     * Removes edge from graph.
     *
     * @param edge edge to remove
     */
    @Override
    public void removeEdge(Edge edge) {
        adj.get(edge.from()).remove(edge.to());
        edges.remove(edge);
    }

    /**
     * Returns list of neighbour nodes for specified node.
     *
     * @param node node to get neighbours for
     * @return set of neighbour nodes
     */
    @Override
    public Set<Integer> getNeighbours(Integer node) {
        return adj.get(node);
    }

    /**
     * Returns set of edges in graph.
     *
     * @return set of edges
     */
    @Override
    public Set<Edge> getEdges() {
        return edges;
    }

    /**
     * Returns set of nodes in graph.
     *
     * @return set of nodes
     */
    @Override
    public Set<Integer> getNodes() {
        return nodes;
    }
}
