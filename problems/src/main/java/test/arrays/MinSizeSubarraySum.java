package test.arrays;

/**
 * Given an array of positive integers, nums, along with a positive integer, target.
 * Find the length of the shortest contiguous subarray whose sum is greater than or equal to the target.
 * If there is no such subarray, return 0 instead.
 */
public class MinSizeSubarraySum {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 3};
        int result = minSubArrayLen(7, arr);
        System.out.println(result);
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int left = 0;
        int result = Integer.MAX_VALUE;

        // Iterate over the nums array
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // check if we can remove elements from the left side of the sub-array
            // while still satisfying the target condition
            while (sum >= s) {
                int currentSubArrSize = (i + 1) - left;
                result = Math.min(result, currentSubArrSize);
                sum -= nums[left++];
            }
        }

        if (result != Integer.MAX_VALUE)
            return result;
        else
            return 0;
    }
}
