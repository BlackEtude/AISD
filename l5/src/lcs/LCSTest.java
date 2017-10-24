package lcs;

import java.util.Random;

/**
 * Created by pandemic on 18.05.17.
 */
public class LCSTest {
    static Random random;

    private static void test() {
        random = new Random();

        int tests = 100;
        double avg;
        LongestCommonSubseq lcs;
        for (int i = 1; i <= 1000; i++) {
            avg = 0;
            for (int j = 1; j <= tests; j++) {
                String a = generateString(i);
                String b = generateString(i);
                lcs = new LongestCommonSubseq(a, b);
                avg += lcs.comparisons;
            }
            avg /= tests;
            System.out.println(i + ";" + avg);
        }
    }


    public static String generateString(int length) {
        random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyz";
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
            text[i] = characters.charAt(random.nextInt(characters.length()));
        return new String(text);
    }

    public static void main(String args[]) {
        String word1 = "ABCBDAB";
        String word2 = "BDCABA";
        LCSTest test = new LCSTest();
        test.test();

        LongestCommonSubseq lcs = new LongestCommonSubseq(word1, word2);
        System.out.println("Answer: " + lcs.getSubsequence() + "\nLength: " + lcs.getSubsequence().length());
    }
}
