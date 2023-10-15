package test.arrays;

import java.util.Arrays;

/**
 * Given an integer array, sorted in non-decreasing order,
 * return an array of the squares of each number, sorted in non-decreasing order.
 */
public class SquaresSortedArray {

    public static void main(String[] args) {
        int[] arr = {-4, -1, 1, 3, 10};
        int[] result = sortedSquares(arr);
        System.out.println(Arrays.toString(result));
    }

    static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int i = right;

        while (i >= 0) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (leftSquare > rightSquare) {
                result[i] = leftSquare;
                left++;
            }
            if (leftSquare <= rightSquare) {
                result[i] = rightSquare;
                right--;
            }
            i--;
        }

        return result;
    }
}
