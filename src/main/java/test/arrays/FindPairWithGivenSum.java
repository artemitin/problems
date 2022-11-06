package test.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers and a value,
 * determine if there are any two integers in the array whose sum is equal to the given value.
 *
 */
public class FindPairWithGivenSum {

    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 4, 8, 3, 2};
        boolean found = findSumOfTwo(arr, 3);
        System.out.println(found);
    } //x + y = 3

    static boolean findSumOfTwo(int[] A, int val) {
        Set<Integer> unique = new HashSet<>();

        for (int e : A) {
            if (unique.contains(val - e)) {
                return true;
            }

            unique.add(e);
        }
        return false;
    }
}
