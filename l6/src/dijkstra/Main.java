package dijkstra;

import graph.Graph;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by pandemic on 30.05.17.
 */
public class Main {
    public static void main(String args[]) {
        readInGraphData();
    }

    private static void readInGraphData() {
        try {
            Scanner scanner = new Scanner(new File("src/dijkstra/data.txt"));
            int numberOfVertices = Integer.parseInt(scanner.next());
            int numberOfEdges = Integer.parseInt(scanner.next());
            int vertexToFind;

            Graph graph = new Graph();

            for(int i = 0; i < numberOfVertices; i++)
                graph.addVertex(i);

            for(int i = 0; i < numberOfEdges; i++) {
                int from = scanner.nextInt(), to = scanner.nextInt();
                double cost = Double.parseDouble(scanner.next());
                graph.addEdge(from, to, cost);
            }
            vertexToFind = scanner.nextInt();

            Dijkstra dijkstra = new Dijkstra(graph, vertexToFind);
            dijkstra.calculateShortestPath();
        }
        catch (IOException ignored) {}
    }
}
