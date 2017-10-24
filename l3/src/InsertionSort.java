/**
 * Created by pandemic on 19.04.17.
 */

class InsertionSort {
    private int[] list;
    private boolean showLog;
    int comparisons;

    InsertionSort(int[] list, boolean showLog) {
        this.list = list;
        this.showLog = showLog;
        comparisons = 0;
        printList();
        sort();
        printList();
    }

    private void sort() {
        int j, key;
        for (int i = 1; i < list.length; i++) {
            key = list[i];
            j = i;
            while(j > 0 && compareWithKey(j-1, key)) {
                swap(j, j-1);
                j--;
            }
            list[j] = key;
        }
    }

    private boolean compareWithKey(int pos, int key) {
        comparisons++;
        printLog("Comparison: " + list[pos] + " and key=" + key + "\n \t\t\t");
        return list[pos] > key;
    }

    private void swap(int i, int j) {
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    private void printList() {
        for(int val: list)
            printLog(val + " ");
        printLog("\n\n");
    }

    private void printLog(String msg) {
        if(showLog)
            System.out.print(msg);
    }

    int getMiddleElement() {
        return list[2];
    }
}
