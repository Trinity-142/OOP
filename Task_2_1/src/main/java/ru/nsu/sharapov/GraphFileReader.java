package ru.nsu.sharapov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class GraphFileReader {

    /**
     * Reads graph data from file and creates specified graph type instance.
     *
     * @param filename  text file with graph data in "vertices edges" and "from to" format
     * @param graphType type of graph to create
     * @return new graph instance filled with data from file
     */
    public static <T extends Graph> T readFromFile(String filename, Class<T> graphType) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] str = reader.readLine().split(" ");
            Integer n = Integer.parseInt(str[0]);
            Integer e = Integer.parseInt(str[1]);
            Constructor<T> constructor = graphType.getDeclaredConstructor(Integer.class,
                Integer.class);
            T graph = constructor.newInstance(n, e);

            for (int i = 0; i < e; ++i) {
                str = reader.readLine().split(" ");
                Integer from = Integer.parseInt(str[0]);
                Integer to = Integer.parseInt(str[1]);
                graph.addEdge(new Edge(from, to));
            }

            return graph;

        } catch (IOException e) {
            throw new RuntimeException("File error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create graph", e);
        }
    }
}
