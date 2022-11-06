package test.arrays;

import java.util.Arrays;

/**
 * Given an integer array, sorted in non-decreasing order,
 * return an array of the squares of each number, sorted in non-decreasing order.
 */
public class ArraysSelectionSort {

    public static void main(String[] args) {
        int[] arr = {29, 3, 23, 82, 11, 5};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void selectionSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int minI = i + 1;
            for (int j = minI; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    minI = j;
                }
            }
            if (nums[minI] < nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[minI];
                nums[minI] = tmp;
            }
        }
    }
}
