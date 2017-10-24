package osTree;

/**
 * Created by pandemic on 14.04.17.
 */
public class Node {
    int value;
    Node left;
    Node right;
    int size;

    public Node(int v, int size) {
        this.value = v;
        left = null;
        right = null;
        this.size = size;
    }
}
