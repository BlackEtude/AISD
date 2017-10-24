package osTree;

/**
 * Created by pandemic on 14.04.17.
 */
public class BinarySearchTree implements ITree {
    private Node root;
    private int selectCompares;
    private int insertCompares;

    BinarySearchTree() {
        this.root = null;
        selectCompares = 0;
    }

    @Override
    public int size() {
        return root.size;
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    @Override
    public void insert(int v) {
        insertCompares = 0;
        root = insert(v, root);
    }

    @Override
    public void delete(int value) {
        root = delete(value, root);
    }

    @Override
    public void inorder() {
        if (root == null)
            System.out.println("Empty tree.");
        else
            inorder(root);
        System.out.println();
    }

    @Override
    public void min() {
        Node x = min(root);
        if (x == null)
            System.out.println();
        else
            System.out.println(x.value);
    }

    @Override
    public void select(int k) {
        selectCompares = 0;
        if (k < 0 || k >= size(root)) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        Node x = select(root, k);
//        System.out.println(x.value);
//        System.out.println("Select compares: " + selectCompares);
    }

    @Override
    public void rank(int value) {
        System.out.println(rank(value, root));
    }

    @Override
    public int getSelectCompares() {
        return selectCompares;
    }

    @Override
    public int getInsertCompares() {
        return insertCompares;
    }

    private Node insert(int v, Node node) {
        insertCompares++;
        if(node == null)
            node = new Node(v, 1);
        else if(v < node.value)
            node.left = insert(v, node.left);
        else
            node.right = insert(v, node.right);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private void inorder(Node node) {
        if(node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    private Node delete(int value, Node node) {
        //value not found
        if(node == null)
            return node;
        else if(value < node.value)
            node.left = delete(value, node.left);
        else if(value > node.value)
            node.right = delete(value, node.right);
        //two children
        else if(node.left != null && node.right != null) {
            node.value = min(node.right).value;
            node.right = delete(node.value, node.right);
        }
        else {
            if(node.left != null)
                return node.left;
            else
                return node.right;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node min(Node node) {
        if(node == null)
            return null;
        else if(node.left == null)
            return node;
        return min(node.left);
    }

    private Node select(Node node, int i) {
        selectCompares++;
        if(node == null)
            return null;
        int r = size(node.left);

        if (r > i)
            return select(node.left, i);
        else if(r < i)
            return select(node.right, i - r - 1);
        else
            return node;
    }

    private int rank(int value, Node node) {
        if(node == null)
            return 0;
        if(value < node.value)
            return rank(value, node.left);
        else if(value > node.value)
            return 1 + size(node.left) + rank(value, node.right);
        else
            return size(node.left);
    }
}
