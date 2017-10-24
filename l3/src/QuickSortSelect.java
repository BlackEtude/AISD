/**
 * Created by pandemic on 19.04.17.
 */
class QuickSortSelect extends Sorter {

    QuickSelect select;


    QuickSortSelect(int[] list, boolean showLog) {
        super(list, showLog);
        printList();
        quickSort(0, list.length - 1);
        printList();
    }

    private void quickSort(int left, int right) {
        select = new QuickSelect(list, (left+right)/2, false);
        int index = select.select(list, left, right, (right+left)/2);
        sortData.comparisons += select.comparisons;
        index = partition(left, right, index);
    }

    private int partition(int left, int right, int pivotIndex) {
        int pivot = list[pivotIndex];
        swap(right, pivotIndex);
        pivotIndex = right;

        int j = left;
        int i = left - 1;
        while (j < right) {
            if (list[j] < pivot && i != j) {
                i++;
                swap(i, j);
            }
            j++;
        }

        swap(i + 1, pivotIndex);

        return i + 1;
    }
}

