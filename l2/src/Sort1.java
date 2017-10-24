import java.util.ArrayList;

public class Sort1 extends Sorter {
    int temp[];

    Sort1(ArrayList<Integer> list, boolean showLog) {
        super(list, showLog);
        temp = new int[list.size()];
        printList();
        sort(0, list.size() - 1);
        printList();
    }

    private void sort(int left, int right) {
        // check if low is smaller then high, if not then the array is sorted
        if ((right - left) < 9)
            insertionSort(left, right);
        else {
            if (left < right) {
                int middle = left + (right - left) / 2;
                sort(left, middle);
                sort(middle + 1, right);
                merge(left, middle, right);
            }
        }
    }

    private void insertionSort(int left, int right) {
        int in, out;
        for (out = left + 1; out <= right; out++) {
            int temp = list.get(out);
            in = out;
            while (in > left && !compare(temp, list.get(in - 1))) {
                swap(in, in-1);
                in--;
            }
            list.set(in, temp);
        }
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            temp[i] = list.get(i);
        }

        int i = low, j = middle + 1, k = low;
        while (i <= middle && j <= high) {
            if (compare(temp[j], temp[i])) {
                swapWithTemp(k, temp[i]);
                i++;
            } else {
                swapWithTemp(k, temp[j]);
                j++;
            }
            k++;
        }
        while (i <= middle) {
            swapWithTemp(k, temp[i]);
            k++;
            i++;
        }
    }

    void swapWithTemp(int index, int value) {
        sortData.swaps++;
        printLog("Swap: " + list.get(index) + " and " + value + "\n \t\t\t");
        printList();
        list.set(index, value);
    }

    public void swap(int d1, int d2) {
        sortData.swaps++;
        int temp = list.get(d1);
        list.set(d1, list.get(d2));
        list.set(d2, temp);
    }
}
