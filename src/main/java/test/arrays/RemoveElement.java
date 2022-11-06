package test.arrays;

public class RemoveElement {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
//        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
//      int[] arr = {0, 1, 3, 2, 3, 0, 4, 2};
//      int[] arr = {0, 1, 3, 0, 3, 0, 4, 2};
//      int[] arr = {0, 1, 3, 0, 4};
        int result = removeElement(arr, 2);
        System.out.println(result);
    }

    static int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;


//        int i = 0;
//        int j = 0;
//
//        while (i < nums.length && j < nums.length) {
//            if (nums[i] == val) {
//                //fast-forward until there's a valid digit
//                for (; j < nums.length && nums[j] == val; j++) ;
//
//                if (j > nums.length - 1) {
//                    return i;
//                }
//                //if the last one is invalid
//                if (nums[j] != val) {
//                    nums[i] = nums[j];
//                    nums[j] = val;
//                    i++;
//                    j++;
//                }
//            } else {
//                i++;
//                j++;
//            }
//        }
//
//        //first N cells with result;
//        return i;
    }
}
