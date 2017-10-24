package osTree.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class Main {
    static Random rand = new Random();
    private static int[] createRandomList(int arraySize) {
        int[] list = new int[arraySize];
        int x;
        for(int i = 0; i < arraySize; i++) {
            x = rand.nextInt(1001);
            list[i] = x;
        }
        return list;
    }

    private static int[] createInjectiveList(int arraySize) {
        ArrayList<Integer> list = new ArrayList<>(arraySize);
        for(int i = 1; i <= arraySize; i++)
            list.add(i);
        Collections.shuffle(list);
        return list.stream().mapToInt(i -> i).toArray();
    }

    private static int[] createDescendList(int arraySize) {
        int[] list = new int[arraySize];
        for(int i = 0; i < arraySize; i++)
            list[i] = (arraySize - 1 - i);
        return list;
    }

    private static void measureAvg() {
        System.out.println("n;avg;min;max");
        int[] list;
        SortData avg = new SortData();
        for(int i = 5; i <= 10; i += 1) {
            avg.comparisons = 0;
            int maxComp = 0;
            int minComp = Integer.MAX_VALUE;
            for(int j = 0; j < 100; j++) {
                list = createRandomList(i);
                int stat = rand.nextInt(i-1) + 1;
                QuickSelect select = new QuickSelect(list, stat, false);
//                avg.comparisons += select.comparisons;
//                if(select.comparisons < minComp)
//                    minComp = select.comparisons;
//                if(select.comparisons > maxComp)
//                    maxComp = select.comparisons;
                avg.comparisons += select.comparisons;
            }
            avg.comparisons /= 100;
            System.out.println(i + ";" + avg.comparisons);
        }
    }

    public static void main(String[] args) {
        measureAvg();
    }
}
