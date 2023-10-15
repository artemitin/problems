package test.math;

import java.util.List;

/**
 * Given an array of positive numbers from 1 to n,
 * such that all numbers from 1 to n are present except one, find the missing number.
 */
public class FindMissingNumber {

    public static void main(String[] args) {
//        List<Integer> input = List.of(3, 7, 1, 2, 0, 4, 5);
        List<Integer> input = List.of(0, 1, 2);
        int result = findMissing(input);
        System.out.println(result);
    }

    static int findMissing(List<Integer> input) {
        // calculate sum of all integers
        // in input list
        int actualSum = 0;
        for (Integer x : input) {
            actualSum += x;
        }
        int n = input.size();

        // find the sum of first n numbers using the arithmetic series sum formula,
        // i.e., (n∗(n+1))/2
        int sumOfN = (n * (n + 1)) / 2;

        // The difference between sumOfN - actualSum, is the missing number in the array
        return sumOfN - actualSum;
    }

}
