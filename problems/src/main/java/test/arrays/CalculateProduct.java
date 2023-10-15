package test.arrays;

import java.util.Arrays;

/**
 * Given an integer array, nums, return an array, answer,
 * such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * .
 */
public class CalculateProduct {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5}; //24, 12, 8, 6
//      int[] nums = {1, 1, 1, 1, 1};
        // x {2, 3, 4, 5}       left = {1, nums[i]*left[i-1], 1*2, 1*2*3, 1*2*3*4}; i = 1..nums.length-1 ++
        // {1} x {3, 4, 5}      right = {1, nums[nums.length - j]*right[j-1], 3*4*5, 4*5, nums[j] * right[j + 1], 1} j = 1..nums.length-1 ++
        // {1, 2} x {4, 5}
        // {1, 2, 3} x {5}
        // {1, 2, 3, 4} x   right[4]=1, right[3]=right[4]*nums[4], right[2]=right[3]*nums[3], right[1]=right[2]*nums[2], right[0]=right[1]*nums[1],
        int[] result = productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            answer[i] = left[i] * right[i];
        }

        return answer;
    }
}
