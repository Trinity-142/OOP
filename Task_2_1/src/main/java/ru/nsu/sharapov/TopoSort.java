package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopoSort {

    /**
     * Topological sort of graph.
     *
     * @param graph graph
     * @return topological sequence of nodes
     */
    public static List<Integer> sort(Graph graph) {
        Map<Integer, State> state = new HashMap<>();
        for (int i = 0; i < graph.getNodesCount(); ++i) {
            state.put(i, State.UNVISITED);
        }
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < graph.getNodesCount(); ++i) {
            if (state.get(i) == State.UNVISITED && !dfs(i, graph, state, res)) {
                throw new IllegalArgumentException("Graph contains cycles");
            }
        }

        return res.reversed();
    }

    /**
     * Dfs for toposort.
     *
     * @param node  node number
     * @param graph graph
     * @param state state of node: unvisited, visited or finished
     * @param res   reversed topological sequence
     * @return false if cycle detected, else true
     */
    private static boolean dfs(Integer node, Graph graph, Map<Integer, State> state,
        List<Integer> res) {
        state.put(node, State.VISITED);
        for (Integer next : graph.getNeighbours(node)) {
            if (state.get(next) == State.VISITED) {
                return false;
            }
            if (state.get(next) == State.UNVISITED && !dfs(next, graph, state, res)) {
                return false;
            }
        }
        state.put(node, State.FINISHED);
        res.add(node);
        return true;
    }

    private enum State {
        UNVISITED, VISITED, FINISHED
    }
}