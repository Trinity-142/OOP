package ru.nsu.sharapov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Set;

/**
 * Abstract graph.
 */
public abstract class AbstractGraph implements Graph {

    @Override
    public void fillFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String str;
            while ((str = reader.readLine()) != null) {
                String[] from_to = str.split(" ");
                Integer from = Integer.parseInt(from_to[0]);
                Integer to = Integer.parseInt(from_to[1]);
                this.addEdge(new Edge(from, to));
            }
        } catch (IOException e) {
            throw new RuntimeException("File error: " + e.getMessage(), e);
        }
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

    public abstract void readFromFile(String filename);

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
