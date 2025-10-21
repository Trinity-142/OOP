package ru.nsu.sharapov;

import java.lang.reflect.Constructor;
import java.util.Set;

/**
 * Abstract graph.
 */
public abstract class AbstractGraph implements Graph {

    public <T extends Graph> T readFromFile(String filename, Class<T> graphType) {
        T graph;
        try {
            Constructor<T> constructor = graphType.getDeclaredConstructor();
            graph = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create graph");
        }
        return graph;
    }

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

    public abstract String toString();

    public abstract Integer getNodesCount();

    public abstract void addNode(Integer node);

    public abstract void removeNode(Integer node);

    public abstract void addEdge(Edge edge);

    public abstract void removeEdge(Edge edge);

    public abstract Set<Integer> getNeighbours(Integer node);

    public abstract Set<Edge> getEdges();

    public abstract Set<Integer> getNodes();
}
