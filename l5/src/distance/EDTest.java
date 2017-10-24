package distance;

import lcs.LCSTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pandemic on 18.05.17.
 */
public class EDTest {
    public static void main(String args[]) {
//        String word1 = "POLYNOMIAL";
//        String word2 = "EXPONENTIAL";
//
        EditDistance ed = new EditDistance();
//        System.out.println("Answer: " + ed.findMinDist(word2, word1));
//        System.out.println("Comparisons: " + ed.getComparisons());
        System.out.println("Enter word:");
        openFile(ed);

        test();
    }

    private static void test() {
        EditDistance ed = new EditDistance();

        int tests = 100;
        double avg;
        for(int i = 1; i <= 1000; i++) {
            avg = 0;
            for (int j = 1; j <= tests; j++) {
                String a = LCSTest.generateString(i);
                String b = LCSTest.generateString(i);
                ed.findMinDist(a, b);
                avg += ed.getComparisons();
            }
            avg /= tests;
            System.out.println(i + ";" + avg);
        }
    }

    private static void openFile(EditDistance ed) {
        File file = new File("data/words.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            ArrayList<Pair> list = new ArrayList<>();
            System.out.println("Searching...");
            while(fileScanner.hasNext()) {
                String w = fileScanner.nextLine();
                Integer distance = ed.findMinDist(w, line);
                list.add(new Pair(w, distance));
                Collections.sort(list, Pair.Comparators.VALUE);
                if(list.size() > 3) {
                    list.remove(3);
                    if(alreadyFoundBest(list))
                        break;
                }
            }
            printList(list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printList(List<Pair> list) {
        for(Pair pair : list)
            System.out.print(pair.getWord() + " : " + pair.getValue() + "\n");
        System.out.println();
    }

    private static boolean alreadyFoundBest(ArrayList<Pair> list) {
        return list.get(0).getValue() == 0 && list.get(1).getValue() == 1 && list.get(2).getValue() == 1;
    }
}
