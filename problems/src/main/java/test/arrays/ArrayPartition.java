package test.arrays;

//You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
//
//We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
//
//The subarray consists of exactly 2, equal elements. For example, the subarray [2,2] is good.
//The subarray consists of exactly 3, equal elements. For example, the subarray [4,4,4] is good.
//The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
//Return true if the array has at least one valid partition. Otherwise, return false.

//solution: https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/solutions/3901261/100-o-n-dynamic-programming-sliding-window-video-optimal-solution/
public class ArrayPartition {

    public static void main(String[] args) {
//        int[] arr = IntStream.range(0, N).toArray();
//        Arrays.sort(arr);
//        int[] arr = new Random().ints(12000, 0, 12000).toArray();
//        int[] arr = {1,1,1,1,1,1,1,2,2/*,1,2,3,4,5,6,7,8,9*/};
        int[] arr = {676575,676575,676575,533985,533985,40495,40495,40495,40495,40495,40495,40495,
                782020,782021,782022,782023,782024,782025,782026,782027,782028,782029,782030,782031,782032,782033,782034,782035,782036,782037,782038,782039,782040,378070,378070,378070,378071,378072,378073,378074,378075,378076,378077,378078,378079,378080,378081,378082,378083,378084,378085,378086,378087,378088,378089,378090,378091,378092,378093,129959,129959,129959,129959,129959,129959};
        long start = System.currentTimeMillis();
        boolean validPartitions = validPartition(arr);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
        System.out.println(validPartitions);
    }

    public static boolean validPartition(int[] nums) {
        int n = nums.length;

        if (n == 1) return false;
//      [i-2, i-1, i]
//        0    1   2
        boolean[] dp = new boolean[]{true, false, n > 1 && nums[0] == nums[1]};

        for (int i = 2; i < n; i++) {
            boolean current_dp = false;

//            check for 2 equal elements
            if (nums[i] == nums[i-1] && dp[1]) {
                current_dp = true;
            }
//            check for 3 equal elements
            else if (nums[i] == nums[i-1] && nums[i] == nums[i-2] && dp[0]) {
                current_dp = true;
            }
//            check for 3 consecutive
            else if (nums[i] - nums[i-1] == 1 && nums[i-1] - nums[i-2] == 1 && dp[0]) {
                current_dp = true;
            }

            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = current_dp;
        }

        return dp[2];
    }


}
