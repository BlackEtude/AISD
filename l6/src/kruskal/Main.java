package kruskal;

import graph.Edge;
import priorityQueue.PriorityQueue;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pandemic on 27.05.17.
 */
public class Main {
    private static final int MAX_NODES = 20;

    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(new File("src/kruskal/data2.txt"));
            Integer.parseInt(scanner.next());
            int numberOfEdges = Integer.parseInt(scanner.next());

            HashSet vertices[] = new HashSet[MAX_NODES];
            PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> {
                Edge first = (Edge)o1;
                Edge second = (Edge)o2;

                if(first.getWeight() < second.getWeight())
                    return -1;
                else if(first.getWeight() > second.getWeight())
                    return 1;
                else
                    return 0;
            });

            for(int i = 0; i < numberOfEdges; i++) {
                int from = scanner.nextInt(), to = scanner.nextInt();
                double cost = Double.parseDouble(scanner.next());
                edges.insert(new Edge(from, to, cost));
                addVertex(vertices, to, from);
            }

            Kruskal k = new Kruskal(edges, vertices);
            ArrayList<Edge> mst = k.runAlgorithm();

            System.out.println("MST by Kruskal's algorithm: ");
            for(Edge e : mst)
                System.out.println("Edge: [" + e + "] with cost: " + e.getWeight());
            System.out.println("Sum: " + getMSTSize(mst));
        }
        catch (IOException ignored) {}
    }


    private static void addVertex(HashSet vertices[], int to, int from) {
        if (vertices[from] == null) {
            vertices[from] = new HashSet(2 * MAX_NODES);
            vertices[from].add(from);
        }
        if (vertices[to] == null) {
            vertices[to] = new HashSet(2 * MAX_NODES);
            vertices[to].add(to);
        }
    }

    private static double getMSTSize(List<Edge> mstEdges) {
        double sum = 0.0;
        for(Edge e : mstEdges)
            sum += e.getWeight();
        return sum;
    }
}
