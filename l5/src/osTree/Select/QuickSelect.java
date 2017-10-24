/**
 * Created by pandemic on 20.04.17.
 */
package osTree.Select;
import java.util.Arrays;

public class QuickSelect extends Select {
    private int result;

    public QuickSelect(int[] l, int k, boolean showLog) {
        super(l, k, showLog);

        if(elemToFind < list.length) {
            printLog(Arrays.toString(list) + "\n");
            result = findKSmallestElement(list, elemToFind-1);
            printLog("Answer: " + result + "\n");
        }
        else {
            printLog("Wrong value\n");
            System.exit(1);
        }
    }

    private int findKSmallestElement(int[] list, int index) {
        int found = select(list,0,list.length-1,index);
        return list[found];
    }

    private int select(int[] list, int left, int right, int index) {
        for(;;) {
            if (left == right)
                return left;
            int pivotIndex = pivot(list, left, right);
            pivotIndex = partition(list, left, right, pivotIndex);
            if (index == pivotIndex) {
                printLog("Element found!\n");
                return index;
            }
            else if (index < pivotIndex) {
                printLog("Element in left part\n");
                right = pivotIndex - 1;
            }
            else {
                printLog("Element in right part\n");
                left = pivotIndex + 1;
            }
        }
    }

    private int pivot(int[] list, int left, int right) {
        if ((right - left) < 5)
            return findMedian(list, left, right);

        for (int i = left; i <= right; i += 5) {
            int rightBord = i + 4;

            if (rightBord > right)
                rightBord = right;

            int median5 = findMedian(list, i, rightBord);
            swap(list, median5, left + (int) Math.floor((i - left) / 5));
        }
        return select(list, left, left + (int) Math.ceil((right - left) / 5) - 1, left + (right - left) / 10);
    }

    private int partition(int[] list, int left, int right, int pivotIndex) {
        int pivot = list[pivotIndex];
        swap(list, right, pivotIndex);
        pivotIndex = right;

        int j = left;
        int i = left - 1;
        while(j < right) {
            if(compare(pivot, list[j]) && i != j) {
                i++;
                swap(list, i, j);
            }
            j++;
        }

        swap(list,i+1, pivotIndex);

        printLog("Partition with pivot = " + this.list[i+1] + " : ");
        printColor(i+1, left, right);
        return i+1;
    }

    private int findMedian( int[] list, int left, int right) {
        for (int j = left + 1; j <= right; j++) {
            int key = list[j];
            int i = j - 1;
            while ((i > -1) && compare(list[i], key)) {
                list[i + 1] = list[i];
                i--;
            }
            list[i + 1] = key;
        }
        return (left + right)/2;
    }
}