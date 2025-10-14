import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.AdjacencyListGraph;
import ru.nsu.sharapov.Edge;

public class StringRepresentationTest {

    @Test
    void printGraph() {
        Integer n = 3;
        Integer e = 3;

        AdjacencyListGraph graph = new AdjacencyListGraph(n, e);
        Edge[] edges = {new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)};
        for (Edge edge : edges) {
            graph.addEdge(edge);
        }

        assertEquals(graph.toString(),
            String.format("Graph with %d nodes and %d edges.\nNodes: %s\nEdges: %s\n", n, e,
                graph.getNodes(), graph.getEdges()));
    }
}