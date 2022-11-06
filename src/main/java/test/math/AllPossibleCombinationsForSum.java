package test.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive integer as the target,
 * print all the possible combinations of positive integers that sum to the target number.
 */
public class AllPossibleCombinationsForSum {

    public static void main(String[] args) {
        int x = 4;
        List<List<Integer>> arrayLists = printAllSum(x);
        System.out.println(arrayLists);
    }

    static void printAllSumRec(int target, int currentSum, int start, List<Integer> result,
                               List<List<Integer>> output) {
        // if current sum is equal to target, shallow copy the result into output array
        if (currentSum == target) {
            output.add(new ArrayList<>(result));
        }

        for (int i = start; i < target; i++) {
            // Increment sum in each recursive call
            int tempSum = currentSum + i;

            // If current sum is equal or less than target, push it in result array and call function recursively
            if (tempSum <= target) {
                result.add(i);
                printAllSumRec(target, tempSum, i, result, output);
                result.remove(result.size() - 1);
            } else {
                return;
            }
        }
    }

    static List<List<Integer>> printAllSum(int target) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        printAllSumRec(target, 0, 1, result, output);
        return output;
    }

}
