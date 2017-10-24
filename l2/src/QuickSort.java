import java.util.ArrayList;

class QuickSort extends Sorter {
    QuickSort(ArrayList<Integer> list, boolean showLog) {
        super(list, showLog);
        printList();
        quickSort(0, list.size() - 1);
        printList();
    }

    private void quickSort(int left, int right) {
        int index = partition(left, right);
        if (left < index - 1)
            quickSort(left, index - 1);
        if (index < right)
            quickSort(index, right);
    }

    private int partition(int left, int right) {
        int i = left, j = right;
        int pivot = list.get((left + right) / 2);
        printLog("Pivot: " + pivot + "\n \t\t\t");
        printColor((left + right) / 2, (left + right) / 2, ANSI_RED);
        while (i <= j) {
            while (compare(pivot, list.get(i))) {
                i++;
            }
            while (compare(list.get(j), pivot)) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        return i;
    }
}
