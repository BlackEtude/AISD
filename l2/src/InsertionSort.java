import java.util.ArrayList;

/**
 * Created by pandemic on 16.03.17.
 */
class InsertionSort extends Sorter {
    InsertionSort(ArrayList<Integer> list, boolean showLog) {
        Sorter.list = list;
        sortData = new SortData();
        this.showLog = showLog;
        printList();
        sort();
        printList();
    }

    private void sort() {
        int j, key;
        for (int i = 1; i < list.size(); i++) {
            key = list.get(i);
            printListWithKey(i);
            j = i;
            while(j > 0 && compareWithKey(j-1, key)) {
                swap(j, j-1);
                j--;
            }
            list.set(j, key);
        }
    }

    private boolean compareWithKey(int pos, int key) {
        sortData.comparisons++;
        printLog("Comparison: " + list.get(pos) + " and key=" + key + "\n \t\t\t");
        printColor(pos, pos, ANSI_YELLOW);
        return list.get(pos) > key;
    }

    private  void printListWithKey(int keyPosition) {
        printLog("Key = " + list.get(keyPosition) + "\n \t\t\t");
        for(int val: list) {
            if(val == list.get(keyPosition))
                printLog(ANSI_RED + val + ANSI_RESET + " ");
            else
                printLog(val + " ");
        }
        printLog("\n\n");
    }
}