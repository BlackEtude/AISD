package priorityQueue;

/**
 * Created by pandemic on 27.05.17.
 */
public class Main {
    public static void main(String args[]) {

        IQueue pq = new PriorityQueue((o1, o2) -> {
            int first = (int) o1;
            int second = (int) o2;

            if(first < second)
                return -1;
            else if(first > second)
                return 1;
            else
                return 0;
        });
        pq.insert(14);
        pq.insert(10);
        pq.insert(7);
        pq.insert(9);
        pq.insert(3);
        pq.insert(2);
        pq.insert(4);
        pq.insert(5);
        pq.printQueue();

        int min = (int)pq.extractMin();
        System.out.println("Min: " + min);
        pq.printQueue();

    }
}