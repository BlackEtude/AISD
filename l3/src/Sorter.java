import java.util.ArrayList;

/**
 * Created by pandemic on 16.03.17.
 */
class Sorter {
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RED = "\u001B[31m";
    static int[] list;
    SortData sortData;
    private boolean showLog;

    Sorter(int[] list, boolean showLog) {
        Sorter.list = list;
        sortData = new SortData();
        this.showLog = showLog;
    }

    void printList() {
        for(int val: list)
            printLog(val + " ");
        printLog("\n\n");
    }

    void printColor(int i, int j, String color) {
        for(int val: list) {
            if(val == list[i] || val == list[j])
                printLog(color + val + ANSI_RESET + " ");
            else
                printLog(val + " ");
        }
        printLog("\n\n");
    }

    void swap(int i, int j) {
        sortData.swaps++;
//        printLog("Swap: " + list.get(i) + " and " + list.get(j) + "\n \t\t\t");
//        printColor(i, j, ANSI_CYAN);
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;

    }

    boolean compare(int val1, int val2) {
        sortData.comparisons++;
//        printLog("Compare: " + val1 + " and " + val2 + "\n \t\t\t");
//        printList();
        return val1 > val2;
    }

    void printLog(String info) {
        if(showLog)
            System.out.print(info);
    }
}

class SortData {
    long comparisons;
    long swaps;

    SortData() {
        comparisons = 0;
        swaps = 0;
    }
}
