import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int[] createRandomList(int arraySize) {
        int[] list = new int[arraySize];
        Random rand = new Random();
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
        System.out.println("n;swaps;comparisons");
        SortData avg = new SortData();
        int[] list;
        for(int i = 100; i <= 100000; i += 100) {
            avg.comparisons = 0;
            for(int j = 0; j < 100; j++) {
                list = createRandomList(i);
                QuickSelect select = new QuickSelect(list, i/2, false);
                avg.comparisons += select.comparisons;
            }
            avg.comparisons /= 100;
            System.out.println(i + ";" + avg.comparisons);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose one:\n1.RadixSort\n2.Randomized Select\n3.Select");
        int sort = scan.nextInt(), size, listType, stat;
        boolean toPrint = true;

        int[] list;
        System.out.println("Type list size:");
        size = scan.nextInt();
        if(size > 60)
            toPrint = false;

        System.out.println("Choose:\n1. Random-valued list\n2. Descend-valued list\n3. Injective-valued list");
        listType = scan.nextInt();
        switch(listType) {
            case 1:
                list = createRandomList(size);
                break;
            case 2:
                list = createDescendList(size);
                break;
            case 3:
                list = createInjectiveList(size);
                break;
            default:
                System.out.println("Wrong input");
                return;
        }
        if(sort == 1)
            new RadixSort(list, toPrint);
        else {
            System.out.println("Type order statistic");
            stat = scan.nextInt();
            if(sort == 2)
                new RandSelect(list, stat, toPrint);
            else if(sort == 3)
                new QuickSelect(list, stat, toPrint);
            else
                System.out.println("Wrong input");
        }
//        measureAvg();
    }
}
