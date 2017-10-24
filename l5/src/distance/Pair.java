package distance;

import java.util.Comparator;

/**
 * Created by pandemic on 17.05.17.
 */
public class Pair implements Comparable<Pair> {
    private String word; //word member of pair
    private int value; //value member of pair

    Pair(String first, int second) {
        this.word = first;
        this.value = second;
    }

    String getWord() {
        return word;
    }

    int getValue() {
        return value;
    }



    @Override
    public int compareTo(Pair pair) {
        return Comparators.VALUE.compare(this, pair);
    }

    static class Comparators {
        static Comparator<Pair> VALUE = new Comparator<Pair>() {
            public int compare(Pair o1, Pair o2) {
                return o1.value - o2.value;
            }
        };
    }
}
