import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.Edge;
import ru.nsu.sharapov.Graph;
import ru.nsu.sharapov.TopoSort;

abstract public class GraphTest {

    private final String graphFile = "graphs/graph.txt";
    private final String cycleGraphFile = "graphs/cycle_graph.txt";
    private Graph graph;

    /**
     * Gives new graph object.
     *
     * @return graph
     */
    public abstract Graph getGraph();

    @Test
    void addNode() {
        graph = getGraph();
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        assertEquals(Set.of(0, 1, 2), graph.getNodes());
    }

    @Test
    void removeNode() {
        graph = getGraph();
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        graph.removeNode(2);
        assertEquals(Set.of(0, 1), graph.getNodes());
        assertEquals(Set.of(new Edge(0, 1)), graph.getEdges());
    }

    @Test
    void addEdge() {
        graph = getGraph();
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        assertEquals(Set.of(new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)), graph.getEdges());
    }

    @Test
    void removeEdge() {
        graph = getGraph();
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        graph.removeEdge(new Edge(0, 1));
        assertEquals(Set.of(new Edge(1, 2), new Edge(2, 0)), graph.getEdges());
    }

    @Test
    void getNeighbours() {
        graph = getGraph();
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        assertEquals(Set.of(1), graph.getNeighbours(0));
    }

    @Test
    void readGraphFromFile() {
        graph = getGraph();
        graph = graph.readFromFile(graphFile);
        assertEquals(Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), graph.getNodes());
        assertEquals(
            Set.of(new Edge(0, 1), new Edge(0, 2), new Edge(0, 3), new Edge(1, 3), new Edge(2, 4),
                new Edge(2, 5), new Edge(3, 5), new Edge(4, 5), new Edge(5, 6), new Edge(6, 7),
                new Edge(7, 9), new Edge(8, 9)), graph.getEdges());
    }

    @Test
    void printGraph() {
        graph = getGraph();
        Edge[] edges = {new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)};
        for (Edge edge : edges) {
            graph.addEdge(edge);
        }

        assertEquals(graph.toString(),
            String.format("Graph with %d nodes and %d edges.\nNodes: %s\nEdges: %s\n",
                graph.getNodes().size(), graph.getEdges().size(), graph.getNodes(),
                graph.getEdges()));
    }

    @Test
    void cycleGraphTopoSort() {
        graph = getGraph();
        graph = graph.readFromFile(cycleGraphFile);
        assertThrows(IllegalArgumentException.class, () -> TopoSort.sort(graph));
    }

    @Test
    void sortAdjListGraph() {
        graph = getGraph();
        graph = graph.readFromFile(graphFile);
        List<Integer> sorted = TopoSort.sort(graph);
        for (Edge edge : graph.getEdges()) {
            int u = edge.from();
            int v = edge.to();
            assertTrue(sorted.indexOf(u) < sorted.indexOf(v));
        }
    }
}
