package ru.nsu.sharapov;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract graph.
 */
public abstract class AbstractGraph implements Graph {

    protected Integer n;
    protected Integer e;
    protected Set<Integer> nodes = new HashSet<>();
    protected Set<Edge> edges = new HashSet<>();

    /**
     * Constructor.
     *
     * @param n number of nodes
     * @param e number of edges
     */
    public AbstractGraph(Integer n, Integer e) {
        this.n = n;
        this.e = e;
    }

    /**
     * Equals override.
     *
     * @param obj the reference object with which to compare
     * @return equals or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Graph other) {
            return this.getEdges().equals(other.getEdges()) && this.getNodes()
                .equals(other.getNodes());
        } else {
            return false;
        }
    }

    /**
     * String representation.
     *
     * @return string
     */
    @Override
    public String toString() {
        return String.format("Graph with %d nodes and %d edges.\nNodes: %s\nEdges: %s\n",
            n, e, getNodes(), getEdges());
    }

    public Integer getNodesCount() {
        return n;
    }

    /**
     * Adds node to graph.
     *
     * @param node node to add
     */
    public abstract void addNode(Integer node);

    /**
     * Removes node from graph.
     *
     * @param node node to remove
     */
    public abstract void removeNode(Integer node);

    /**
     * Adds edge to graph.
     *
     * @param edge edge to add
     */
    public abstract void addEdge(Edge edge);

    /**
     * Removes edge from graph.
     *
     * @param edge edge to remove
     */
    public abstract void removeEdge(Edge edge);

    /**
     * Returns list of neighbour nodes for specified node.
     *
     * @param node node to get neighbours for
     * @return set of neighbour nodes
     */
    public abstract Set<Integer> getNeighbours(Integer node);

    /**
     * Returns set of edges in graph.
     *
     * @return set of edges
     */
    public abstract Set<Edge> getEdges();

    /**
     * Returns set of nodes in graph.
     *
     * @return set of nodes
     */
    public abstract Set<Integer> getNodes();
}
