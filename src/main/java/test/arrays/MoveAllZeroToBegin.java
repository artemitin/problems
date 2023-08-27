package test.arrays;

import java.util.Arrays;

/**
 * Move all zeroes if any to the left while maintaining the order of other elements in the array.
 */
public class MoveAllZeroToBegin {

    public static void main(String[] args) {
        int[] arr = {1, 10, 20, 0, 59, 63, 0, 88, 0};
//        int[] arr = {1, 2, 3, 4, 5};
        moveZerosToRight(arr);
//        moveZerosToLeft(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void moveZerosToLeft(int[] nums) {
        int read = nums.length - 1;
        int write = read;
        while (read >= 0) {
            if (nums[read] != 0) {
                nums[write--] = nums[read];
            }
            read--;
        }

        while (write >= 0) {
            nums[write--] = 0;
        }
    }

    static void moveZerosToRight(int[] nums) {
        int read = 0;
        int write = read;
        while (read < nums.length) {
            if (nums[read] != 0) {
                nums[write++] = nums[read];
            }
            read++;
        }

        while (write < nums.length) {
            nums[write++] = 0;
        }
    }
}
