package priorityQueue;

/**
 * Created by pandemic on 27.05.17.
 */
public interface IQueue<T> {
    void insert(T key);
    void decreaseKey(int x, T newKey);
    T extractMin();
    void printQueue();
    void heapify(int i);
    T getMin();
}
