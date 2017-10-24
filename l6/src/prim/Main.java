package prim;

/**
 * Created by pandemic on 27.05.17.
 */

import graph.Edge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Edge[] edges;

        try {
            Scanner scanner = new Scanner(new File("src/prim/data2.txt"));
            Integer.parseInt(scanner.next());
            int numberOfEdges = Integer.parseInt(scanner.next());
            edges = new Edge[numberOfEdges];

            for(int i = 0; i < numberOfEdges; i++) {
                int from = scanner.nextInt(), to = scanner.nextInt();
                double cost = Double.parseDouble(scanner.next());
                edges[i] = new Edge(from, to, cost);
            }

            ArrayList<ArrayList<Edge>> graph = Prim.createGraph(edges);
            ArrayList<Edge> mst = Prim.runAlgorithm(graph);

            System.out.println("MST by Prim's algorithm:");
            for(Edge e : mst)
                System.out.println("Edge: [" + e + "] with cost: " + e.getWeight());
            System.out.println("Sum: " + getMSTSize(mst));
        }
        catch (IOException ignored) {}
    }

    private static double getMSTSize(List<Edge> mstEdges) {
        double sum = 0.0;
        for(Edge e : mstEdges)
            sum += e.getWeight();
        return sum;
    }

}
