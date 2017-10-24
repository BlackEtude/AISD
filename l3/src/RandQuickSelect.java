import java.util.Arrays;

/**
 * Created by pandemic on 14.04.17.
 */

class RandQuickSelect extends Select {
    int result;

    RandQuickSelect(int[] list, int k, boolean showLog) {
        super(list, k, showLog);
        findKSmallestElement();
        printLog("Number of comparisons: " + comparisons + "\n");
    }

    private void findKSmallestElement() {
        if(elemToFind < list.length) {
            printLog(Arrays.toString(list) + "\n");
            result = randSelect(0, list.length - 1, elemToFind);
            printLog("" + result + "\n");
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
        swap(list, pivotIndex, right);

        for (int i = left; i < right; i++) {
            if (compare(list[right], list[i])) {
                swap(list, storeIndex, i);
                storeIndex++;
            }
        }
        swap(list, storeIndex, right);
        printLog("Partition with pivot = " + list[storeIndex] + " : ");
        printColor(storeIndex, left, right);
        return storeIndex;
    }

    private void sortList(){
        RadixSort sort = new RadixSort(list, false);
        list = sort.getList();
    }
}
