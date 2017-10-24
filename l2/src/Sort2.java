import java.util.ArrayList;

public class Sort2 extends Sorter {
    int[] data;

    Sort2(ArrayList<Integer> lista, boolean showLog) {
        super(lista, showLog);
        data = new int[lista.size()];

        for(int i = 0; i < lista.size(); i++) {
            data[i] = lista.get(i);
        }

        for(int i = 0; i < lista.size(); i++)
            printLog(data[i] + " ");
        printLog("\n");

        optimizedQuickSort(0, data.length - 1);

        for(int i = 0; i < lista.size(); i++)
            printLog(data[i] + " ");
    }

    private void optimizedQuickSort(int left, int right) {
        int size = right - left + 1;
        if (size < 10)
            insertionSort(left, right);
        else {
            int median = medianOf3(left, right);
            int partition = partition(left, right, median);
            optimizedQuickSort(left, partition - 1);
            optimizedQuickSort(partition + 1, right);
        }
    }

    private int medianOf3(int left, int right) {
        int center = (left + right) / 2;
        if (compare(data[left], data[center]))
            swap(left, center);
        if (compare(data[left], data[right]))
            swap(left, right);
        if (compare(data[center], data[right]))
            swap(center, right);

        swap(center, right - 1);
        return data[right - 1];
    }

    private int partition(int left, int right, int pivot) {
        printLog("Pivot: " + pivot + "\n");
        int i = left;
        int j = right - 1;
        while (true) {
            sortData.comparisons++;
            while (data[++i] < pivot) {
                sortData.comparisons++;
            }
            sortData.comparisons++;
            while (data[--j] > pivot) {
                sortData.comparisons++;
            }
            if (j <= i)
                break;
            else
                swap(i, j);
        }
        swap(i, right - 1);
        return i;
    }

    private void insertionSort(int left, int right) {
        int i, j;
        for (j = left + 1; j <= right; j++) {
            int key = data[j];
            i = j;
            while (i > left && !compare(key, data[i - 1])) {
                swap(i, i-1);
                i--;
            }
            data[i] = key;
        }
    }

    public boolean compare(int v1, int v2) {
        sortData.comparisons++;
        printLog("Compare: " + v1 + " and " + v2 + "\n \t\t\t");
        printList();
        return v1 > v2;
    }

    public void swap(int d1, int d2) {
        sortData.swaps++;
        printLog("Swap: " + data[d1] + " and " + data[d2] + "\n \t\t\t");
        printColor(d1, d2, ANSI_CYAN);
        int temp = data[d1];
        data[d1] = data[d2];
        data[d2] = temp;
    }

    void printColor(int i, int j, String color) {
        for(int val: data) {
            if(val == data[i] || val == data[j])
                printLog(color + val + ANSI_RESET + " ");
            else
                printLog(val + " ");
        }
        printLog("\n\n");
    }

}
