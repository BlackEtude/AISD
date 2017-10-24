package osTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by pandemic on 14.04.17.
 */
public class Main {
    private static ITree bst;

    public static void main(String args[]) {
        bst = new BinarySearchTree();
        try {
            readInstructions(new Scanner(new File("data/data.txt")));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readInstructions(Scanner scanner) {
        int numberOfOperations = Integer.parseInt(scanner.nextLine());
        System.out.println(numberOfOperations);
        for(int i = 0; i < numberOfOperations; i++) {
            runMethod(scanner.nextLine());
        }
    }

    private static void runMethod(String line) {
        Scanner s = new Scanner(line);
        String methodName = s.next();
        int param;
        try {
            if (s.hasNextInt()) {
                //run with param
                param = s.nextInt();
                Method m = bst.getClass().getDeclaredMethod(methodName, int.class);
                m.invoke(bst, param);
            } else {
                //run without param
                Method m = bst.getClass().getDeclaredMethod(methodName);
                m.invoke(bst);
            }
        }
        catch(NoSuchMethodException e) {
            System.out.println("No method");
        } catch (IllegalAccessException e) {
            System.out.println("No access");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}