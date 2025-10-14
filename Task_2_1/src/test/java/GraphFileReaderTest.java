import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.AdjacencyListGraph;
import ru.nsu.sharapov.AdjacencyMatrixGraph;
import ru.nsu.sharapov.Edge;
import ru.nsu.sharapov.GraphFileReader;
import ru.nsu.sharapov.IncidenceMatrixGraph;

public class GraphFileReaderTest {

    String file = "graphs/cycle_graph.txt";

    @Test
    void readAdjListGraph() {
        AdjacencyListGraph graph = GraphFileReader.readFromFile(file, AdjacencyListGraph.class);
        assertEquals(Set.of(0, 1, 2), graph.getNodes());
        assertEquals(Set.of(new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)), graph.getEdges());
    }

    @Test
    void readAdjMatrixGraph() {
        AdjacencyMatrixGraph graph = GraphFileReader.readFromFile(file, AdjacencyMatrixGraph.class);
        assertEquals(Set.of(0, 1, 2), graph.getNodes());
        assertEquals(Set.of(new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)), graph.getEdges());
    }

    @Test
    void readIncidenceMatrixGraph() {
        IncidenceMatrixGraph graph = GraphFileReader.readFromFile(file, IncidenceMatrixGraph.class);
        assertEquals(Set.of(0, 1, 2), graph.getNodes());
        assertEquals(Set.of(new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)), graph.getEdges());
    }
}