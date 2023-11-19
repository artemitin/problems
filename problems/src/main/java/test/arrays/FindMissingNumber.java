package test.arrays;

import test.MeasureUtil;

import java.util.Arrays;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 */
public class FindMissingNumber {

    public static void main(String[] args) {
        int size = 10;
//        int[] array = new Random().ints(size, -10, 10).toArray();
//        System.out.println(Arrays.toString(array));
//        int[] array = new int[]{4, 3, -1, 1};
        int[] array = new int[]{1};
//       3, 4, -1, 1
//       4, 3, 5, 1
//       -1, 1, 3, 4

        int n = MeasureUtil.runMeasured(() -> firstMissingPositive(array));
        System.out.println("Result: " + n);
    }

    public static int firstMissingPositive(int[] nums) {
        //array is incomplete. Look in range 1...nums.length
        //mark
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int ref = nums[i];
            while (ref > 0 && ref <= nums.length) {
                int tmp = nums[ref - 1];
                nums[ref - 1] = -1;
                if (tmp < 1 || tmp > nums.length) {
                    break;
                }
                ref = tmp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public int firstMissingPositive2(int[] nums) {
        Arrays.sort(nums);

        int ans = 1;

        for (int e : nums) {
            if (e == ans) {
                ans++;
            }
        }

        return ans;
    }

    //with memoryO(n)
    public int firstMissingPositive3(int[] nums) {
        int n = nums.length + 1;
        int[] cache = new int[n];
        for (int e : nums) {
            if (e > 0 && e < n)
                cache[e - 1] = e;
        }
        for (int i = 0; i < n; i++) {
            if (cache[i] == 0) {
                return i + 1;
            }
        }

        return n;
    }
}
