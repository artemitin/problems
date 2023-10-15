package test.dynamic;

/**
 * Given an array, find the contiguous subarray with the largest sum.
 * Return the largest sum.
 * Remember that a subarray contains at least one number.
 */
public class LargestSum {

    public static void main(String[] args) {
        int[] arr = {-4 ,2, -5, 1 ,2, 3, 6, -5, 1};
        int result = findMaxSumSubArray(arr);
        System.out.println(result);
    }

    public static int findMaxSumSubArray(int[] nums) {
        // Check if the size of the array is at least 2
        if (nums.length < 1) {
            return 0;
        }

        int curMax = nums[0];
        int globMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (curMax < 0) {
                curMax = nums[i];
            } else {
                curMax += nums[i];
            }

            // If the current subarray max is greater than the global
            // max, update the global max
            if (globMax < curMax) {
                globMax = curMax;
            }
        }

        return globMax;
    }
}
