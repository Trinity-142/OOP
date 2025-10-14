package ru.nsu.sharapov;

/**
 * Edge.
 *
 * @param from node from
 * @param to node to
 */
public record Edge(Integer from, Integer to) {

    /**
     * String representation.
     *
     * @return string
     */
    @Override
    public String toString() {
        return from + " -> " + to;
    }
}