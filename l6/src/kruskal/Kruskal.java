package kruskal;

import priorityQueue.PriorityQueue;
import graph.Edge;

import java.util.*;

class Kruskal {

    private HashSet vertices[];
    private PriorityQueue<Edge> edges;
    private ArrayList<Edge> mstEdges;

    Kruskal(PriorityQueue<Edge> edges, HashSet vertices[]) {
        this.vertices = vertices;
        this.edges = edges;
        mstEdges = new ArrayList<>();
    }

    ArrayList<Edge> runAlgorithm() {
        int numberOfEdges = edges.getPQSize();
        for (int i = 0; i < numberOfEdges; i++) {
            Edge currentEdge = edges.extractMin();

            //successfully extracted
            if (currentEdge != null) {
                if (nodesAreInDifferentSets(currentEdge.getSource(), currentEdge.getDestination())) {
                    HashSet source, destination;
                    int dstHashSetIndex;

                    //transfer vertices from 'from' or 'to'
                    if (vertices[currentEdge.getSource()].size() > vertices[currentEdge.getDestination()].size()) {
                        source = vertices[currentEdge.getDestination()];
                        destination = vertices[dstHashSetIndex = currentEdge.getSource()];
                    }
                    else {
                        source = vertices[currentEdge.getSource()];
                        destination = vertices[dstHashSetIndex = currentEdge.getDestination()];
                    }

                    Object srcArray[] = source.toArray();
                    for (Object elem : srcArray) {
                        // move each node from <set>src into <set>set: dst, update appropriate index in vertices
                        if (source.remove(elem)) {
                            destination.add(elem);
                            vertices[(Integer) elem] = vertices[dstHashSetIndex];
                        }
                    }
                    mstEdges.add(currentEdge);
                }
            }
        }
        return mstEdges;
    }

    private boolean nodesAreInDifferentSets(int a, int b) {
        return(!vertices[a].equals(vertices[b]));
    }
}