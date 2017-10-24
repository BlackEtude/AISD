package prim;

import graph.Edge;
import priorityQueue.PriorityQueue;
import java.util.ArrayList;

/**
 * Created by pandemic on 31.05.17.
 */

class Prim {
    static ArrayList<Edge> runAlgorithm(ArrayList<ArrayList<Edge>> graph) {
        ArrayList<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((Object o1, Object o2) -> {
            Edge first = (Edge)o1;
            Edge second = (Edge)o2;

            if(first.getWeight() < second.getWeight())
                return -1;
            else if(first.getWeight() > second.getWeight())
                return 1;
            else
                return 0;
        });

        for(Edge e : graph.get(0))
            pq.insert(e);

        boolean[] visited = new boolean[graph.size()];
        visited[0] = true;

        while(!pq.isEmpty()) {
            Edge e = pq.extractMin();

            if(visited[e.getDestination()])
                continue;

            visited[e.getSource()] = true;
            for(Edge edge : graph.get(e.getDestination())) {
                if (!visited[edge.getDestination()]) {
                    pq.insert(edge);
                }
            }
            visited[e.getDestination()] = true;
            mst.add(e);
        }
        return mst;
    }

    static ArrayList<ArrayList<Edge>> createGraph(Edge[] edges) {
        ArrayList<ArrayList<Edge>> G = new ArrayList<>();
        int length = edges.length * 2;
        for(int i = 0; i < length; i++)
            G.add(new ArrayList<>());

        for(Edge e : edges){
            Edge other = new Edge(e.getDestination(), e.getSource(), e.getWeight());
            G.get(e.getSource()).add(e);
            G.get(e.getDestination()).add(other);
        }
        return G;
    }
}
