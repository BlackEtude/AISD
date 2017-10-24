package osTree;

/**
 * Created by pandemic on 14.04.17.
 */
public interface ITree {
    void insert(int value);
    void delete(int value);
    void inorder();
    void min();
    void select(int i);
    void rank(int value);
    int size();
    int getSelectCompares();
    int getInsertCompares();
}
