package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IncidenceMatrixGraph extends AbstractGraph {

    private final List<List<Integer>> adj = new ArrayList<>();
    private final Map<Integer, Edge> indexToEdge = new HashMap<>();

    /**
     * Constructor.
     *
     * @param n number of nodes
     * @param e number of edges
     */
    public IncidenceMatrixGraph(Integer n, Integer e) {
        super(n, e);
        for (int i = 0; i < n; ++i) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < e; ++j) {
                row.add(0);
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
        for (int i = 0; i < e; ++i) {
            if (adj.get(node).get(i) != 0) {
                Edge edge = indexToEdge.get(i);
                adj.get(edge.from()).set(i, 0);
                adj.get(edge.to()).set(i, 0);
                removeEdge(indexToEdge.get(i));
            }
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
        adj.get(edge.from()).set(indexToEdge.size(), 1);
        adj.get(edge.to()).set(indexToEdge.size(), -1);
        edges.add(edge);
        indexToEdge.put(indexToEdge.size(), edge);
    }

    /**
     * Removes edge from graph.
     *
     * @param edge edge to remove
     */
    @Override
    public void removeEdge(Edge edge) {
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
        for (int i = 0; i < e; ++i) {
            if (adj.get(node).get(i) == 1) {
                for (int j = 0; j < n; ++j) {
                    if (adj.get(j).get(i) == -1) {
                        res.add(j);
                    }
                }
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
