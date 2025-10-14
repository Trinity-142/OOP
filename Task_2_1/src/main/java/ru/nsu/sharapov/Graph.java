package ru.nsu.sharapov;

import java.util.Set;

/**
 * Graph interface.
 */
public interface Graph {

    void addNode(Integer node);

    void removeNode(Integer node);

    void addEdge(Edge edge);

    void removeEdge(Edge edge);

    Integer getNodesCount();

    Set<Integer> getNeighbours(Integer node);

    Set<Edge> getEdges();

    Set<Integer> getNodes();

    boolean equals(Object obj);
}
