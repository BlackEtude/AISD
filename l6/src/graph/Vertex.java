package graph;

/**
 * Created by pandemic on 30.05.17.
 */
public class Vertex {
    final private String name;
    final private int id;

    Vertex(int id) {
        name = "Node " + id;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    int getId() {
        return id;
    }
}
