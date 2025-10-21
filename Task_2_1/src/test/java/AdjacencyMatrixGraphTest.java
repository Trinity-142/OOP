import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.AdjacencyListGraph;
import ru.nsu.sharapov.AdjacencyMatrixGraph;
import ru.nsu.sharapov.Edge;
import ru.nsu.sharapov.Graph;
import ru.nsu.sharapov.IncidenceMatrixGraph;

public class AdjacencyMatrixGraphTest extends GraphTest {

    /**
     * Gives new graph object.
     *
     * @return graph
     */
    @Override
    public Graph getGraph() {
        return new AdjacencyMatrixGraph();
    }

    @Test
    void compare() {
        AdjacencyListGraph graph1 = new AdjacencyListGraph();
        AdjacencyMatrixGraph graph2 = new AdjacencyMatrixGraph();
        IncidenceMatrixGraph graph3 = new IncidenceMatrixGraph();

        Edge[] edges = {new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)};
        for (Edge edge : edges) {
            graph1.addEdge(edge);
            graph2.addEdge(edge);
            graph3.addEdge(edge);
        }

        assertEquals(graph2, graph3);
    }
}