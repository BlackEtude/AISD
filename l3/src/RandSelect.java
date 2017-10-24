import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by pandemic on 14.04.17.
 */

class RandSelect extends Select {
    private int[] list;
    private int elemToFind;
    private boolean showLog;
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RED = "\u001B[31m";
    int comparisons;
    Random rand;

    RandSelect(int[] list, int k, boolean showLog) {
        super(list, k, showLog);
        findKSmallestElement();
        System.out.println("Number of comparisons: " + comparisons + "\n");
    }

    private void findKSmallestElement() {
        if(elemToFind < list.length) {
            printLog(Arrays.toString(list) + "\n");
            printLog("" + randSelect(0, list.length - 1, elemToFind) + "\n");
            sortList();
            printLog("Sorted list: ");
            printLog(Arrays.toString(list) + "\n");
        }
         else {
            printLog("Wrong value");
            System.exit(1);
        }
    }

    private int randSelect(int p, int q, int i) {
        if(p == q) {
            printLog("Element found!\n");
            return list[p];
        }
        int r = randPartition(list, p, q);
        int k = r - p + 1;

        if(i == k) {
            printLog("Element found!\n");
            return list[r];
        }
        else if(i < k) {
            printLog("Element in left part\n");
            return randSelect(p, r - 1, i);
        }
        else {
            printLog("Element in right part\n");
            return randSelect(r + 1, q, i - k);
        }
    }

    private int randPartition(int[] list, int left, int right) {
        if (right - left <= 0)
            return right;

        int storeIndex = left;
        int pivotIndex = rand.nextInt(right - left + 1) + left;
        swap(pivotIndex, right);

        for (int i = left; i < right; i++) {
            if (compare(list[right], list[i])) {
                swap(storeIndex, i);
                storeIndex++;
            }
        }
        swap(storeIndex, right);
        printLog("Partition with pivot = " + list[storeIndex] + " : ");
        printColor(storeIndex, left, right);
        return storeIndex;
    }

    private void printColor(int k, int start, int end) {
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

    private boolean compare(int val1, int val2) {
        comparisons++;
        return val1 > val2;
    }

    private void swap(int i, int j) {
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    private void sortList(){
        RadixSort sort = new RadixSort(list, false);
        list = sort.getList();
    }

    private void printLog(String msg) {
        if(showLog)
            System.out.print(msg);
    }
}
