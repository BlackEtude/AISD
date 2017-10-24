import java.util.ArrayList;

/**
 * Created by pandemic on 18.03.17.
 */
class MergeSort extends Sorter {
    private int temp[];

    MergeSort(ArrayList<Integer> list, boolean showLog) {
        super(list, showLog);
        printList();
        temp = new int[list.size()];
        sort(0, list.size() - 1);
        printList();
    }

    private void sort(int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            sort(left, middle);
            sort(middle + 1, right);
            merge(left, middle, right);
        }
    }

    private void merge(int left, int middle, int right) {
        printLog("Merge in [" + left + ", " + right + "]\n");
        for (int i = left; i <= right; i++)
            temp[i] = list.get(i);

        int i = left, j = middle + 1, k = left;
        while (i <= middle && j <= right) {
            if (!compare(temp[i], temp[j])) {
                swap(k, temp[i]);
                i++;
            }
            else {
                swap(k, temp[j]);
                j++;
            }
            k++;
        }
        while (i <= middle) {
            swap(k, temp[i]);
            k++;
            i++;
        }
    }

    void swap(int index, int value) {
        sortData.swaps++;
        printLog("Swap: " + list.get(index) + " and " + value + "\n \t\t\t");
        printList();
        list.set(index, value);
    }
}





















