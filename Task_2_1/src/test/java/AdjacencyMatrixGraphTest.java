import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.AdjacencyMatrixGraph;
import ru.nsu.sharapov.Edge;

public class AdjacencyMatrixGraphTest {

    @Test
    void addNode() {
        Integer n = 3;
        Integer e = 3;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n, e);
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        assertEquals(Set.of(0, 1, 2), graph.getNodes());
    }

    @Test
    void removeNode() {
        Integer n = 3;
        Integer e = 3;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n, e);
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        graph.removeNode(2);
        assertEquals(Set.of(0, 1), graph.getNodes());
        assertEquals(Set.of(new Edge(0, 1)), graph.getEdges());
    }

    @Test
    void addEdge() {
        Integer n = 3;
        Integer e = 3;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n, e);
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        assertEquals(Set.of(new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)), graph.getEdges());
    }

    @Test
    void removeEdge() {
        Integer n = 3;
        Integer e = 3;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n, e);
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        graph.removeEdge(new Edge(0, 1));
        assertEquals(Set.of(new Edge(1, 2), new Edge(2, 0)), graph.getEdges());
    }

    @Test
    void getNeighbours() {
        Integer n = 3;
        Integer e = 3;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n, e);
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 0));
        assertEquals(Set.of(1), graph.getNeighbours(0));
    }
}