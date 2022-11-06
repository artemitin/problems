package test.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class MaxDiff {

    public static void main(String[] args) {
        int[] arr = {11, 8, 9, 2, 2, 10, 6};
        int result = maxDiff(arr);
        System.out.println(result);
    }

    static int maxDiff(int[] nums) {
        int max = 0;
        int buy = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - buy;

            if (diff > max) {
                max = diff;
            }

            if (nums[i] < buy) {
                buy = nums[i];
            }
        }

        return max;
    }
}
