package priorityQueue;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by pandemic on 27.05.17.
 */
public class PriorityQueue<T> implements IQueue {
    private int heapSize;
    private ArrayList<T> list;
    private Comparator comparator;

    public PriorityQueue(Comparator c) {
        heapSize = 0;
        list = new ArrayList<>(100);
        comparator = c;
    }

    @Override
    public void printQueue() {
        for(T i : list)
            System.out.print(i + " ");
        System.out.println();
    }

    @Override
    public void insert(Object key) {
        int i = heapSize;
        list.add((T)key);
        while((i > 0) && comparator.compare(list.get(parent(i)), key) == 1) {
            swap(i, parent(i));
            i = parent(i);
        }
        heapSize++;
    }

    @Override
    public void decreaseKey(int position, Object newKey) {
        if(comparator.compare(newKey, list.get(position)) == 1) {
            System.out.println("ERROR - new value is grater than actual");
            return;
        }
        list.set(position, (T)newKey);
        while(position > 0 && comparator.compare(list.get(parent(position)), newKey) == 1) {
            swap(position, parent(position));
            position = parent(position);
        }
    }

    @Override
    public T extractMin() {
        if(heapSize < 1) {
            System.out.println("Heap is empty!");
            return null;
        }
        T min = list.get(0);
        list.set(0, list.get(heapSize-1));
        heapSize--;
        list.remove(heapSize);
        heapify(0);
        return min;
    }

    @Override
    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        if(l < heapSize && comparator.compare(list.get(i), list.get(l)) == 1)
            smallest = l;
        else
            smallest = i;

        if(r < heapSize && comparator.compare(list.get(smallest), list.get(r)) == 1)
            smallest = r;

        if(smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    @Override
    public T getMin() {
        return list.get(0);
    }

    public boolean isEmpty() {
        if(heapSize == 0)
            return true;
        return false;
    }

    public int getPQSize() {
        return heapSize;
    }

    private int left(int i) {
        return (i*2 + 1);
    }

    private int right(int i) {
        return (i*2 + 2);
    }

    private void swap(int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    private int parent(int i) {
        return (i-1)/2;
    }
}
