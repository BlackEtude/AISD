package basic;

/**
 * Created by pandemic on 14.04.17.
 */
public class BinarySearchTree implements ITree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public void insert(int v) {
        root = insert(v, root);
    }

    @Override
    public void delete(int value) {
        root = delete(value, root);
    }

    private Node delete(int value, Node node) {
        if(node == null)       //value not found
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
        return node;
    }

    @Override
    public void inorder() {
        if(root == null)
            System.out.println("Empty tree.");
        else
            inorder(root);
        System.out.println();
    }

    @Override
    public void find(int value) {
        if(find(value, root) != null)
            System.out.println(1);
        else
            System.out.println(0);
    }

    @Override
    public void min() {
        Node ret = min(root);
        if(ret == null)
            System.out.println();
        else {
            System.out.println(ret.value);
        }
    }

    @Override
    public void max() {
        Node ret = max(root);
        if(ret == null)
            System.out.println();
        else {
            System.out.println(ret.value);
        }
    }


    private Node insert(int v, Node node) {
        if(node == null) {
            node = new Node(v);
        }
        else if(v < node.value) {
            node.left = insert(v, node.left);
        }
        else {
            node.right = insert(v, node.right);
        }
        return node;
    }

    private void inorder(Node node) {
        if(node != null) {
            inorder(node.right);
            System.out.print(node.value + " ");
            inorder(node.left);
        }
    }

    private Node find(int value, Node node) {
        if(node == null)
            return null;
        else if(value < node.value)
            return find(value, node.left);
        else if(value > node.value)
            return find(value, node.right);
        else
            return node;
    }

    private Node min(Node node) {
        if(node == null)
            return null;
        else if(node.left == null)
            return node;
        return min(node.left);
    }


    private Node max(Node node) {
        if(node == null)
            return null;
        else if(node.right == null)
            return node;
        return max(node.right);
    }
}
