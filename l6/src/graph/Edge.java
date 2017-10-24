package graph;

/**
 * Created by pandemic on 30.05.17.
 */
public class Edge {
    private final Vertex source;
    private final Vertex destination;
    private final double weight;

    public Edge(int source, int destination, double weight) {
        this.source = new Vertex(source);
        this.destination = new Vertex(destination);
        this.weight = weight;
    }

    public int getDestination() {
        return destination.getId();
    }

    public int getSource() {
        return source.getId();
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return ("(" + source.getId() + ", " + destination.getId() + ") : " + weight);
    }
}
