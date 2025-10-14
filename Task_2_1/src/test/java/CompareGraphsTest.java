import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.AdjacencyListGraph;
import ru.nsu.sharapov.AdjacencyMatrixGraph;
import ru.nsu.sharapov.Edge;
import ru.nsu.sharapov.IncidenceMatrixGraph;

public class CompareGraphsTest {

    @Test
    void compare() {
        Integer n = 3;
        Integer e = 3;

        AdjacencyListGraph graph1 = new AdjacencyListGraph(n, e);
        AdjacencyMatrixGraph graph2 = new AdjacencyMatrixGraph(n, e);
        IncidenceMatrixGraph graph3 = new IncidenceMatrixGraph(n, e);

        Edge[] edges = {new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)};
        for (Edge edge : edges) {
            graph1.addEdge(edge);
            graph2.addEdge(edge);
            graph3.addEdge(edge);
        }

        assertEquals(graph1, graph2);
        assertEquals(graph2, graph3);
        assertEquals(graph1, graph3);
    }
}
