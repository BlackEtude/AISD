/**
 * Created by pandemic on 25.04.17.
 */
public class LongestCommonSubseq {
    public static void main(String args[]) {
        String word1 = "ABCBDAB";
        String word2 = "BDCABA";
        System.out.println("Answer: " + lcs(word1, word2));
    }

    private static String lcs(String a, String b){
        int word1leng = a.length();
        int word2leng = b.length();
        if(word1leng == 0 || word2leng == 0)
            return "";
        else if(a.charAt(word1leng-1) == b.charAt(word2leng-1))
            return lcs(a.substring(0,word1leng - 1), b.substring(0,word2leng - 1)) + a.charAt(word1leng - 1);
        else {
            String x = lcs(a, b.substring(0, word2leng - 1));
            String y = lcs(a.substring(0,word1leng - 1), b);
            return (x.length() > y.length()) ? x : y;
        }
    }
}
