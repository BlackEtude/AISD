package distance;
/**
 * Created by pandemic on 25.04.17.
 */
public class EditDistance {
    int comparisons;

    int findMinDist(String word1, String word2) {
        int[][] distances = new int[word1.length() + 1][word2.length() + 1];
        char c1, c2;
        int a, b, c;
        comparisons = 0;

        for (int i = 0; i <= word1.length(); i++)
            distances[i][0] = i;

        for (int j = 0; j <= word2.length(); j++)
            distances[0][j] = j;

        for (int i = 0; i < word1.length(); i++) {
            c1 = word1.charAt(i);
            for (int j = 0; j < word2.length(); j++) {
                c2 = word2.charAt(j);
                comparisons++;
                if (c1 == c2)
                    distances[i + 1][j + 1] = distances[i][j];
                else {
                    a = distances[i][j] + 1;
                    b = distances[i][j + 1] + 1;
                    c = distances[i + 1][j] + 1;

                    comparisons += 2;
                    int min = a > b ? b : a;
                    min = c > min ? min : c;
                    distances[i + 1][j + 1] = min;
                }
            }
        }
        return distances[word1.length()][word2.length()];
    }

    public int getComparisons() {
        return comparisons;
    }
}
