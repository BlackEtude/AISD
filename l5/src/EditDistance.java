import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by pandemic on 25.04.17.
 */
public class EditDistance {
    public static void main(String args[]) {
        String word1 = "POLYNOMIAL";
        String word2 = "EXPONENTIAL";

        System.out.println("\n");
        System.out.println("return: " + findMinDist(word2, word1));

        openFile();
    }

    private static void autocomplete() {

    }

    private static int findMinDist(String word1, String word2) {
        int[][] distances = new int[word1.length() + 1][word2.length() + 1];
        char c1, c2;
        int a, b, c;

        for (int i = 0; i <= word1.length(); i++)
            distances[i][0] = i;

        for (int j = 0; j <= word2.length(); j++)
            distances[0][j] = j;

        for (int i = 0; i < word1.length(); i++) {
            c1 = word1.charAt(i);
            for (int j = 0; j < word2.length(); j++) {
                c2 = word2.charAt(j);
                if (c1 == c2)
                    distances[i + 1][j + 1] = distances[i][j];
                else {
                    a = distances[i][j] + 1;
                    b = distances[i][j + 1] + 1;
                    c = distances[i + 1][j] + 1;

                    int min = a > b ? b : a;
                    min = c > min ? min : c;
                    distances[i + 1][j + 1] = min;
                }
            }
        }

//        printWord(distances);
        return distances[word1.length()][word2.length()];
    }

    private static void printWord(int[][] word) {
        for(int[] i : word) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    private static void openFile() {
        File file = new File("data/words.txt");
        try {
            Scanner fileScanner = new Scanner(file);

            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
//            System.out.println("Line: " + line);

            int counter = 0;
            while(fileScanner.hasNext() && counter < 4) {
                String w = fileScanner.nextLine();
                if(findMinDist(w, line) < 2) {
                    System.out.println(w);
                    counter++;
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
