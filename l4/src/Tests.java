import basic.BinarySearchTree;
import basic.ITree;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pandemic on 22.04.17.
 */
public class Tests {
    private static ArrayList<ITree> forest;
    public static void main(String[] args) {
        int numberOfTrees = 2000;
        int numberOfTests = 20000;
        System.out.println("Number of trees: " + numberOfTrees + ", tests: " + numberOfTests);
        System.out.println("n;avg;min;max");
        for(int i = 1; i < numberOfTests; i++) {
            System.out.print(i + ";");
            forest = seedForest(numberOfTrees, i);
            testDepth(forest);
        }
    }

    //las 2000 drzew, każde o rozmiarze i (i z przedz [1, 20000])
    private static ArrayList<ITree> seedForest(int trees, int size) {
        ArrayList<ITree> forest = new ArrayList<>();
        ITree tree;
        for(int i = 0; i < trees; i++) {
            tree = makeRandomTree(size);
            forest.add(tree);
        }
        return forest;
    }

    private static ITree makeRandomTree(int size) {
        ITree tree = new BinarySearchTree();
        Random random = new Random();
        for(int i = 0; i < size; i++)
            tree.insert(random.nextInt(1000) + 1);
        return tree;
    }

    private static ArrayList<ITree> seedDescendForest(int trees, int size) {
        ArrayList<ITree> forest = new ArrayList<>();
        ITree tree;
        for(int i = 0; i < trees; i++) {
            tree = makeTree(size);
            forest.add(tree);
        }
        return forest;
    }

    private static ITree makeTree(int size) {
        ITree tree = new BinarySearchTree();
        for(int i = 0; i < size; i++)
            tree.insert(i);
        return tree;
    }

    //10 testów dla każdego drzewa w lesie
    private static void testDepth(ArrayList<ITree> forest) {
        int tests = 10;
        Random random = new Random();
        double average = 0, min = Integer.MAX_VALUE, max = 0;
        for(ITree tree : forest) {
            double averageFind = 0;
            for(int i = 0; i < tests; i++) {
                tree.conditionFind(random.nextInt(1000) + 1, false);
                if(tree.getCompares() > max)
                    max = tree.getCompares();
                if(tree.getCompares() < min)
                    min = tree.getCompares();
                averageFind += tree.getCompares();
            }
            averageFind /= tests;
            average += averageFind;
        }

        average /= (double)forest.size();
        System.out.print(average + ";" + min + ";" + max + "\n");
    }
}
