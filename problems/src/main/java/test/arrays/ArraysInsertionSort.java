package test.arrays;

import java.util.Arrays;

/**
 * Given an integer array, sorted in non-decreasing order,
 * return an array of the squares of each number, sorted in non-decreasing order.
 */
public class ArraysInsertionSort {

    public static void main(String[] args) {
        int[] arr = {29, 23, 82, 11};
//                 prev curr
//                      tmp
//                 {23, 29, 82, 11};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void insertionSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int current = 1;
        while (current < nums.length) {
            int prev = current - 1;
            int temp = nums[current];
            while (prev >= 0 && temp < nums[prev]) {
                nums[prev + 1] = nums[prev];
                nums[prev] = temp;
                prev -= 1;
            }
            current++;
        }
    }
}
