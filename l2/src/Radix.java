/**
* Created by pandemic on 13.04.17.
*/
import java.util.Random;

public class Radix {
    public static void main(String[] args){
        final int SIZE = 5;
        Random r = new Random();
        int[] test = new int[SIZE];
        for (int i = 0; i < SIZE; i++)
            test[i] = r.nextInt(Integer.MAX_VALUE);
        test = sort(test);

        for (Integer i : test){
            System.out.println(i);
        }
    }

    private static int[] sort(int[] input){
        int maxIntPlace = 1000000000;
        // Largest place for a 32-bit int is the 1 billion's place
        for(int place=1; place <= maxIntPlace; place *= 10)
            input = countingSort(input, place);
        return input;
    }

    private static int[] countingSort(int[] input, int place){
        int[] out = new int[input.length];

        int[] count = new int[10];

        for(int i=0; i < input.length; i++){
            int digit = getDigit(input[i], place);
            count[digit] += 1;
        }

        for(int i=1; i < count.length; i++)
            count[i] += count[i-1];

        for(int i = input.length-1; i >= 0; i--){
            int digit = getDigit(input[i], place);

            out[count[digit]-1] = input[i];
            count[digit]--;
        }

        return out;

    }

    private static int getDigit(int value, int digitPlace){
        return ((value/digitPlace ) % 10);
    }

}
