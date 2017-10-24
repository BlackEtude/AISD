package osTree;

import osTree.Select.*;

import java.util.Random;

/**
 * Created by pandemic on 18.05.17.
 */
public class ITreeTest {
    private static Random random;

    public static void main(String args[]) {
        random = new Random();
        int maxTreeSize = 1000, numberOfTests = 100;

        testSelectFromSameLists(maxTreeSize, numberOfTests);
//        testSelectFromDifferentLists(maxTreeSize, numberOfTests);
    }

    private static int[] generateRandomList(int size) {
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = random.nextInt(1000) + 1;
        }
        return list;
    }

    private static void testSelectFromSameLists(int maxTreeSize, int numberOfTests) {
        System.out.println("n;OSSelect;Select;RandSelect");
        int list[];
        for(int i = 5; i < maxTreeSize; i++) {
            list = generateRandomList(i);
            selectFromTree(list, numberOfTests);
            selectFromList(list, numberOfTests);
            randSelectFromList(list, numberOfTests);
        }
    }

    private static void testSelectFromDifferentLists(int maxTreeSize, int numberOfTests) {
        System.out.println("n;OSSelect;Select;RandSelect");
        int list[];
        for(int i = 5; i < maxTreeSize; i++) {
            list = generateRandomList(i);
            selectTree(list, numberOfTests);
            selectFromList(list, numberOfTests);
            randSelectFromList(list, numberOfTests);
        }
    }

    private static void selectTree(int[] arrayList, int numberOfTests) {
        double average = 0, treeCost = 0;
        ITree tree = null;
        for(int i = 0; i < numberOfTests; i++) {
            tree = new BinarySearchTree();
            for(int j = 0; j < arrayList.length; j++) {
                tree.insert(arrayList[j]);
                treeCost += tree.getInsertCompares();
            }
            int s = random.nextInt(tree.size());
            tree.select(s);
            average += tree.getSelectCompares();
        }
        average /= numberOfTests;
        System.out.print(tree.size() + ";" + (average+treeCost));

    }

    private static void selectFromTree(int[] arrayList, int numberOfTests) {
        ITree tree = new BinarySearchTree();
        int treeCost = 0;
        for(int i = 0; i < arrayList.length; i++) {
            tree.insert(arrayList[i]);
            treeCost += tree.getInsertCompares();
        }

        double average = 0;
        for(int i = 0; i < numberOfTests; i++) {
            int s = random.nextInt(tree.size());
            tree.select(s);

            average += tree.getSelectCompares();
        }
        average /= numberOfTests;
        System.out.print(tree.size() + ";" + (average+treeCost));
    }

    private static void selectFromList(int[] arrayList, int numberOfTests) {
        int comparisons = 0;
        for(int j = 0; j < numberOfTests; j++) {
            int stat = random.nextInt(arrayList.length-1) + 1;
            QuickSelect select = new QuickSelect(arrayList, stat, false);
            comparisons += select.comparisons;
        }
        comparisons /= numberOfTests;
        System.out.print(";" + comparisons);
    }

    private static void randSelectFromList(int[] arrayList, int numberOfTests) {
        int comparisons = 0;
        for(int j = 0; j < numberOfTests; j++) {
            int stat = random.nextInt(arrayList.length-1) + 1;
            RandQuickSelect select = new RandQuickSelect(arrayList, stat, false);
            comparisons += select.comparisons;
        }
        comparisons /= numberOfTests;
        System.out.print(";" + comparisons + "\n");
    }
}
