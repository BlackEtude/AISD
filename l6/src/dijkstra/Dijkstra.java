package dijkstra;

import graph.*;
import priorityQueue.PriorityQueue;
import java.util.*;

/**
 * Created by pandemic on 27.06.17.
 */
class Dijkstra {
    private Map<Integer, Result> map;
    private PriorityQueue<Result> pq;
    private Graph graph;
    private int source;

    Dijkstra(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        map = new HashMap<>();
        pq = new PriorityQueue<>((Object o1, Object o2) -> {
            Result first = (Result)o1;
            Result second = (Result)o2;

            if(first.weight < second.weight)
                return -1;
            else if(first.weight > second.weight)
                return 1;
            else
                return 0;
        });
    }

    /**
     * Running time: O(ElogE)
     */
    void calculateShortestPath() {
        for (Integer vertex : graph.getVertices()) {
            Result result;
            if (source == vertex)
                result = new Result(vertex, 0d);
            else
                result = new Result(vertex, Double.POSITIVE_INFINITY);
            pq.insert(result);
            map.put(vertex, result);
        }

        while (!pq.isEmpty()) {
            Result queueElem = pq.extractMin();

            //skip already added elems
            if (map.get(queueElem.destination) != queueElem)
                continue;

            for (Edge edge : graph.getEdges(queueElem.destination)) {
                double newPath = queueElem.weight + edge.getWeight();

                if (newPath < map.get(edge.getDestination()).weight) {
                    Result newResult = new Result(edge.getDestination(), newPath);
                    map.put(edge.getDestination(), newResult);
                    newResult.setPath(edge.getSource(), map);
                    pq.insert(newResult);
                }
            }
        }

        for (int i = 0; i < map.size(); i++) {
            System.out.println("From " + source + " to " + map.get(i).destination + ", cost " + map.get(i).weight);
            for(Edge e : map.get(i).path)
                System.out.println(e.toString());
        }
    }

    private class Result {
        int destination;
        double weight;
        List<Edge> path;

        Result(int name, double weight) {
            this.destination = name;
            this.weight = weight;
            path = new ArrayList<>();
        }

        void setPath(int prevVertex, Map<Integer, Result> map) {
            if(prevVertex != source)
                path.addAll(map.get(prevVertex).path);
            path.add(new Edge(prevVertex, destination, weight));
        }
    }
}
