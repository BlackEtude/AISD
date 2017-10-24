/**
 * Created by pandemic on 13.04.17.
 */

class RadixSort {
    private int[] list;
    private boolean showLog;
    private int base;
    private int swaps;

    RadixSort(int[] l, boolean showLog) {
        list = l;
        this.showLog = showLog;
        printList(list);
        swaps = 0;
        list = sort(list);
        printLog("Sorted list: ");
        printList(list);
        printLog("Swaps: " + swaps);
    }

    private int chooseProperBase() {
        int maxElem = 0;
        for(int i : list) {
            if(i > maxElem)
                maxElem = i;
        }
        base = Integer.toBinaryString(maxElem).length();
        return maxElem;
    }

    private int[] sort(int[] input){
        int maxIntPlace = chooseProperBase();
        for(int place = 1; place <= maxIntPlace; place *= base) {
            input = countingSort(input, place);
        }
        return input;
    }

    private int[] countingSort(int[] input, int place){
        int[] out = new int[input.length];
        int[] count = new int[base];

        //add by digit to count[]
        for (int i : input) {
            int digit = getDigit(i, place);
            count[digit]++;
        }
        printLog("Count: ");
        printList(count);

        //sum every number with previous in count[]
        for(int i = 1; i < count.length; i++)
            count[i] += count[i-1];

        //go backward through input
        for(int i = input.length-1; i >= 0; i--){
            int digit = getDigit(input[i], place);
            out[count[digit]-1] = input[i];
            swaps++;
            count[digit]--;
        }
        printLog("List: ");
        printList(out);
        return out;
    }

    private int getDigit(int value, int digitPlace){
        return ((value / digitPlace) % base);
    }

    private void printList(int[] list) {
        for(int val: list)
            printLog(val + " ");
        printLog("\n\n");
    }

    private void printLog(String info) {
        if(showLog)
            System.out.print(info);
    }

    int[] getList() {
        return list;
    }
}
