package test.misc;

import java.util.Arrays;

/**
 * Given an array of integers and a value,
 * determine if there are any three integers in the array whose sum equals the given value.
 */
public class SumOfThree {

    public static void main(String[] args) {
        System.out.println(findSumOfThree(new int[] {3, 7, 1, 2, 8, 4, 5}, 20));
    }

    public static boolean findSumOfThree(int[] nums, int sum) {
        Arrays.sort(nums); //1, 2, 3, 4, 5, 7, 8  => 20

        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int triples = nums[i] + nums[low] + nums[high];
                if (triples == sum) {
                    return true;
                }

                if (triples < sum) {
                    low++;
                } else {
                    high--;
                }
            }
        }

        return false;
    }
}
