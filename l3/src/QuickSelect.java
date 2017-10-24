/**
 * Created by pandemic on 20.04.17.
 */
import java.util.Arrays;
import java.util.Random;

class QuickSelect {
    private int[] list;
    private int elemToFind;
    int comparisons;
    private boolean showLog;
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RED = "\u001B[31m";
    Random rand;

    QuickSelect(int[] l, int k, boolean showLog) {
        list = l;
        elemToFind = k - 1;
        this.showLog = showLog;
        comparisons = 0;
        rand = new Random();
        if(elemToFind < list.length) {
            printLog(Arrays.toString(list) + "\n");
            System.out.println("Answer: " + select(list, elemToFind));
            System.out.println("Sorted list: ");
            sortList();
            System.out.println(Arrays.toString(list) + "\n");
        }
        else {
            printLog("Wrong value\n");
            System.exit(1);
        }
    }

    public int select(int[] list, int index) {
        int found = select(list,0,list.length-1,index);
        return list[found];
    }

    public int select(int[] list,int left, int right,int index) {
        for(;;) {
            if (left == right)
                return left;
            int pivotIndex = pivot(list, left, right);
            pivotIndex = partition(list, left, right, pivotIndex);
            if (index == pivotIndex)
                return index;
            else if (index < pivotIndex)
                right = pivotIndex - 1;
            else
                left = pivotIndex + 1;
        }
    }

    private int pivot(int[] list, int left, int right) {
        if ((right - left) < 5)
            return findMedian(list, left, right);

        for (int i = left; i <= right; i += 5) {
            int subRight = i + 4;

            if (subRight > right)
                subRight = right;

            int median5 = findMedian(list, i, subRight);
            swap(list, median5, left + (int) Math.floor((i - left) / 5));
        }
        return select(list, left, left + (int) Math.ceil((right - left) / 5) - 1, left + (right - left) / 10);
    }
    private int partition(int[] list, int left, int right, int pivotIndex) {
        int pivot = list[pivotIndex];

        swap(list,right,pivotIndex);
        pivotIndex = right;

        int j = left;
        int i = left-1;
        while(j < right) {
            if(list[j] < pivot && i != j) {
                i++;
                swap(list,i,j);
            }
            j++;
        }

        swap(list,i+1, pivotIndex);

        printLog("Partition with pivot = " + this.list[i+1] + " : ");
        printColor(i+1, left, right);
        return i+1;
    }

    private int findMedian( int[] arr, int left, int right) {
        for (int j = left+1; j <= right; j++) {
            int key = arr[j];
            int i = j - 1;
            while ((i > -1) && (arr[i] > key)) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
        return (left + right)/2;
    }

    private void swap(int[] arr,int i,int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void sortList(){
        RadixSort sort = new RadixSort(list, false);
        list = sort.getList();
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

    private void printLog(String info) {
        if(showLog)
            System.out.print(info);
    }
}