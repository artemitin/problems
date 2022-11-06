package test.arrays;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * We’re given an array of interval pairs as input where each interval has a start and end timestamp.
 * The input array is sorted by starting timestamps. Merge the overlapping intervals and return a new output array.
 *
 */
public class MergeArrayWithOverlappingIntervals {

    public static void main(String[] args) {
        ArrayList<Pair> pairs = new ArrayList<>();
        //{{1,3},{2,6},{8,10},{15,18}};
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(2,6));
        pairs.add(new Pair(8,10));
        pairs.add(new Pair(15,18));
        ArrayList<Pair> merged = mergeIntervals(pairs);
        System.out.println(merged);
    } /*1 2 3
          2 3 4 5 6
                    8 10*/
    static ArrayList<Pair> mergeIntervals(ArrayList<Pair> v) {
        ArrayList<Pair> result = new ArrayList<>();

        Iterator<Pair> iterator = v.iterator();
        Pair merged = iterator.next();

        while(iterator.hasNext()) {
            Pair current = iterator.next();
            if (merged.second < current.first) {
                result.add(merged);
                merged = current;
            } else if (merged.second < current.second) {
                merged.second = current.second;
            }
        }
        result.add(merged);

        return result;
    }
}

// Creating a tuple class as java does not support returning
// multiple arguments
class Pair {
    public int first;
    public int second;

    public Pair(int x, int y) {
        this.first = x;
        this.second = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}