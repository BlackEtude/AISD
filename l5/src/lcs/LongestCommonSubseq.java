package lcs;

/**
 * Created by pandemic on 25.04.17.
 */

public class LongestCommonSubseq {
    static int comparisons;
    int[][] matrix;
    String a, b;

    LongestCommonSubseq(String a, String b) {
        this.a = a;
        this.b = b;
        findSubsequence();
    }

    private void findSubsequence() {
        matrix = new int[a.length()+1][b.length()+1];
        comparisons = 0;

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                comparisons++;
                if (a.charAt(i) == b.charAt(j))
                    matrix[i + 1][j + 1] = matrix[i][j] + 1;
                else {
                    comparisons++;
                    matrix[i + 1][j + 1] = Math.max(matrix[i + 1][j], matrix[i][j + 1]);
                }
            }
        }
    }

    public void printMatrix() {
        for(int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public String getSubsequence() {
        // read substring from the matrix
        StringBuilder buffer = new StringBuilder();
        int x = a.length(), y = b.length();
        while( x != 0 && y != 0) {
            if (matrix[x][y] == matrix[x-1][y])
                x--;
            else if (matrix[x][y] == matrix[x][y-1])
                y--;
            else {
                buffer.append(a.charAt(x-1));
                x--;
                y--;
            }
        }
        return buffer.reverse().toString();
    }


}
