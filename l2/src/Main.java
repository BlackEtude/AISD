import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ArrayList<Integer> createRandomList(int arraySize) {
        ArrayList<Integer> list = new ArrayList<>(arraySize);
        Random rand = new Random();
        int x;
        for(int i = 0; i < arraySize; i++) {
            x = rand.nextInt(1001);
            list.add(x);
        }
        return list;
    }

    private static ArrayList<Integer> createDescendList(int arraySize) {
        ArrayList<Integer> list = createRandomList(arraySize);
        list.sort(Collections.reverseOrder());
        return list;
    }

    private static void measureAvg() {
        System.out.println("n;swaps;comparisons");
        SortData avg = new SortData();
        ArrayList<Integer> list;
        for(int i = 100; i <= 100000; i += 100) {
            avg.comparisons = 0;
            avg.swaps = 0;
            for(int j = 0; j < 100; j++) {
                list = createDescendList(i);
                DPQuicksort sort = new DPQuicksort(list, false);
                avg.swaps += sort.sortData.swaps;
                avg.comparisons += sort.sortData.comparisons;
            }
            avg.comparisons /= 100;
            avg.swaps /= 100;
            System.out.println(i + ";" + avg.swaps + ";" + avg.comparisons);
        }
    }

    public static void main(String[] args) {
        System.out.println("Wybierz:\n1. generuj ciąg losowy\n2. generuj ciąg posortowany malejąco\n");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        System.out.println("Wybrano: " + input);
        System.out.println("Podaj rozmiar danych\n");
        int size = scan.nextInt();
        boolean toPrint = true;
        if(size > 10)
            toPrint = false;

        ArrayList<Integer> list1;
        switch(input) {
            case 1:
                list1 = createRandomList(size);
                new RadixSort(list1, toPrint);
                break;
            case 2:
                list1 = createDescendList(size);
                new RadixSort(list1, toPrint);
                break;
            default:
                System.out.println("Invalid input!");
        }
//        measureAvg();
    }
}
