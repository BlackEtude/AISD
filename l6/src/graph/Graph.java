package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by pandemic on 02.06.17.
 */
public class Graph {
    private Set<Edge> edges;
    private Map<Integer, Set<Edge>> vertices;

    public Graph() {
        edges = new HashSet<>();
        vertices = new HashMap<>();
    }

    public void addVertex(Integer v) {
        vertices.put(v, new HashSet<>());
    }

    public void addEdge(Integer v1, Integer v2, Double weight) {
        if(!vertices.containsKey(v1) || !vertices.containsKey(v2))
            return;

        Edge edge = new Edge(v1, v2, weight);
        vertices.get(v1).add(edge);
        edges.add(edge);
    }

    public Set<Integer> getVertices() {
        return new HashSet<>(vertices.keySet());
    }

    public Set<Edge> getEdges(Integer ver) {
        return vertices.get(ver);
    }
}
