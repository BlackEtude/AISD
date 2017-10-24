import java.util.ArrayList;

public class DPQuicksort extends Sorter {
    DPQuicksort(ArrayList<Integer> list, boolean showLog) {
        super(list, showLog);
        int[] data = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            data[i] = list.get(i);

        sort(data, 0, data.length);
    }

    private void sort(int[] data, int left, int right) {
        dualPivotQuicksort(data, left, right - 1, 3);
    }

    private void dualPivotQuicksort(int[] a, int left, int right, int div) {
        int len = right - left;
        if (len < 27) {
            for (int i = left + 1; i <= right; i++)
                for (int j = i; j > left && a[j] < a[j - 1]; j--)
                    swap(a, j, j - 1);
            return;
        }

        int pom = len / div;
        //medians
        int m1 = left + pom, m2 = right - pom;
        if (m1 <= left)
            m1 = left + 1;
        if (m2 >= right)
            m2 = right - 1;
        if (compare(a[m2], a[m1])) {
            swap(a, m1, left);
            swap(a, m2, right);
        }
        else {
            swap(a, m1, right);
            swap(a, m2, left);
        }

        int pivot1 = a[left], pivot2 = a[right];
        int p = left + 1, q = right - 1;        //pointers

        for (int k = p; k <= q; k++) {
            if (compare(pivot1, a[k]))
                swap(a, k, p++);
            else if (compare(a[k], pivot2)) {
                while (k < q && compare(a[q], pivot2)) q--;
                swap(a, k, q--);
                if (compare(pivot1, a[k]))
                    swap(a, k, p++);
            }
        }
        int dist = q - p;
        if (dist < 13)
            div++;

        swap(a, p - 1, left);
        swap(a, q + 1, right);
        dualPivotQuicksort(a, left, p - 2, div);
        dualPivotQuicksort(a, q + 2, right, div);

        if (dist > len - 13 && pivot1 != pivot2) {
            for (int k = p; k <= q; k++) {
                sortData.comparisons++;
                if (a[k] == pivot1) {
                    swap(a, k, p++);
                }
                else if (a[k] == pivot2) {
                    swap(a, k, q--);
                    if (a[k] == pivot1) {
                        swap(a, k, p++);
                    }
                }
            }
        }

        if (compare(pivot2, pivot1)) {
            dualPivotQuicksort(a, p, q, div);
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        sortData.swaps++;
        printLog("Swap: " + a[i]+ " and " + a[j] + "\n \t\t\t");
    }

    public boolean compare(int v1, int v2) {
        sortData.comparisons++;
        printLog("Compare: " + v1 + " and " + v2 + "\n \t\t\t");
        return v1 > v2;
    }
}


