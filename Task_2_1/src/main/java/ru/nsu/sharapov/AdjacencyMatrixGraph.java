package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdjacencyMatrixGraph extends AbstractGraph {

    private final List<List<Boolean>> adj = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param n number of nodes
     * @param e number of edges
     */
    public AdjacencyMatrixGraph(Integer n, Integer e) {
        super(n, e);
        for (int i = 0; i < n; ++i) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                row.add(false);
            }
            adj.add(row);
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
        for (int i = 0; i < n; ++i) {
            removeEdge(new Edge(node, i));
        }

        for (int i = 0; i < n; ++i) {
            removeEdge(new Edge(i, node));
        }
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
        adj.get(edge.from()).set(edge.to(), true);
        edges.add(edge);
    }

    /**
     * Removes edge from graph.
     *
     * @param edge edge to remove
     */
    @Override
    public void removeEdge(Edge edge) {
        adj.get(edge.from()).set(edge.to(), false);
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
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < adj.size(); ++i) {
            if (adj.get(node).get(i)) {
                res.add(i);
            }
        }
        return res;
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
