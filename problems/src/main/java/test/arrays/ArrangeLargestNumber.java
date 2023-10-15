package test.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Given an array of integers,
 * find the largest number that can be made by creating all possible permutations of these integers.
 * <p>
 * As the largest number formed can be very large, Return a string instead of an integer.
 */
public class ArrangeLargestNumber {

    public static void main(String[] args) {
        int[] arr = {71, 5, 21, 52}; // -> 71, 5, 52, 21
        //95, 34, 30, 3 -> 95 34 3 30
        String result = largestNumber(arr);
        System.out.println(result);
    }

    private static class LargerNumberComparator implements Comparator<String> {
        // Takes two strings and returns 0, 1, or -1
        // by comparing the strings formed by the
        // concatenation of these strings, i.e., whether
        // they are greater, lesser or equal to each other.
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    static String largestNumber(int[] nums) {
        String result = Arrays.stream(nums).boxed()
                .map(String::valueOf)
                .sorted(Comparator.reverseOrder())
                .sorted(new LargerNumberComparator())
                .collect(Collectors.joining());
        if (result.charAt(0) == '0') return "0";
        else return result;
    }
}
