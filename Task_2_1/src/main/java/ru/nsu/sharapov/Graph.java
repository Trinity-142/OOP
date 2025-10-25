package ru.nsu.sharapov;

import java.util.Set;

/**
 * Graph interface.
 */
public interface Graph {

    /**
     * Adds node to graph. If graph already contains the node, the call leaves the graph unchanged
     * and returns.
     *
     * @param node node to add
     */
    void addNode(Integer node);

    /**
     * Removes node from graph. If graph doesn't contain the node, the call leaves the graph
     * unchanged and returns.
     *
     * @param node node to remove
     */
    void removeNode(Integer node);

    /**
     * Adds edge to graph. If graph already contains the edge, the call leaves the graph unchanged
     * and returns.
     *
     * @param edge edge to add
     */
    void addEdge(Edge edge);

    /**
     * Removes edge from graph. If graph doesn't contain the edge, the call leaves the graph
     * unchanged and returns.
     *
     * @param edge edge to remove
     */
    void removeEdge(Edge edge);

    /**
     * Gives count of nodes in graph.
     *
     * @return count of nodes
     */
    Integer getNodesCount();

    /**
     * Returns set of neighbour nodes for specified node.
     *
     * @param node node to get neighbours for
     * @return set of neighbour nodes. If node hasn't any neighbours, returns empty set.
     */
    Set<Integer> getNeighbours(Integer node);

    /**
     * Returns set of edges in graph.
     *
     * @return set of edges. If graph hasn't any edges, returns empty set
     *
     */
    Set<Edge> getEdges();

    /**
     * Returns set of nodes in graph.
     *
     * @return set of nodes. If graph hasn't any nodes, returns empty set
     */
    Set<Integer> getNodes();

    /**
     * Reads graph data from file and creates specified graph type instance.
     *
     * @param filename  text file with graph data in "vertices edges" and "from to" format
     */
    void readFromFile(String filename);

    /**
     * Fills graph with data from file.
     *
     * @param filename text file with graph data in "vertices edges" and "from to" format
     */
    void fillFromFile(String filename);

    /**
     * Equals override.
     *
     * @param obj the reference object with which to compare
     * @return equals or not
     */
    boolean equals(Object obj);

    /**
     * String representation.
     *
     * @return string
     */
    String toString();
}
