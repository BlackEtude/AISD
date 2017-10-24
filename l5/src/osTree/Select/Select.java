package osTree.Select;
import java.util.Random;

public class Select {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RED = "\u001B[31m";
    int[] list;
    public int comparisons;
    boolean showLog;
    int elemToFind;
    Random rand;

    Select(int[] l, int k, boolean showLog) {
        list = l;
        this.showLog = showLog;
        elemToFind = k;
        comparisons = 0;
        rand = new Random();
    }

    void printColor(int k, int start, int end) {
        for(int i = 0; i < list.length; i++) {
            if(list[k] == list[i])
                printLog(ANSI_RED + list[i] + ANSI_RESET + " ");
            else if(i >= start && i <= end)
                printLog(ANSI_YELLOW + list[i] + ANSI_RESET + " ");
            else
                printLog(list[i] + " ");
        }
        printLog("\n\n");
    }

    void printLog(String info) {
        if(showLog)
            System.out.print(info);
    }

    boolean compare(int val1, int val2) {
        comparisons++;
        return val1 > val2;
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
