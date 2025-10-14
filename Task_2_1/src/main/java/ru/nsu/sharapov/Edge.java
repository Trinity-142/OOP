package ru.nsu.sharapov;

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