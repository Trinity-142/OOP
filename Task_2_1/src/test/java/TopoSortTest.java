import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.AdjacencyListGraph;
import ru.nsu.sharapov.AdjacencyMatrixGraph;
import ru.nsu.sharapov.Edge;
import ru.nsu.sharapov.GraphFileReader;
import ru.nsu.sharapov.IncidenceMatrixGraph;
import ru.nsu.sharapov.TopoSort;

public class TopoSortTest {

    String graphFile = "graphs/graph.txt";
    String cycleGraphFile = "graphs/cycle_graph.txt";

    @Test
    void cycleGraph() {
        AdjacencyListGraph graph = GraphFileReader.readFromFile(cycleGraphFile,
            AdjacencyListGraph.class);
        assertThrows(IllegalArgumentException.class, () -> TopoSort.sort(graph));
    }

    @Test
    void sortAdjListGraph() {
        AdjacencyListGraph graph = GraphFileReader.readFromFile(graphFile,
            AdjacencyListGraph.class);
        List<Integer> sorted = TopoSort.sort(graph);
        for (Edge edge : graph.getEdges()) {
            int u = edge.from();
            int v = edge.to();
            assertTrue(sorted.indexOf(u) < sorted.indexOf(v));
        }
    }

    @Test
    void sortAdjMatrixGraph() {
        AdjacencyMatrixGraph graph = GraphFileReader.readFromFile(graphFile,
            AdjacencyMatrixGraph.class);
        List<Integer> sorted = TopoSort.sort(graph);
        for (Edge edge : graph.getEdges()) {
            int u = edge.from();
            int v = edge.to();
            assertTrue(sorted.indexOf(u) < sorted.indexOf(v));
        }
    }

    @Test
    void sortIncidenceMatrixGraph() {
        IncidenceMatrixGraph graph = GraphFileReader.readFromFile(graphFile,
            IncidenceMatrixGraph.class);
        List<Integer> sorted = TopoSort.sort(graph);
        for (Edge edge : graph.getEdges()) {
            int u = edge.from();
            int v = edge.to();
            assertTrue(sorted.indexOf(u) < sorted.indexOf(v));
        }
    }
}
