package test.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Move all zeroes if any to the left while maintaining the order of other elements in the array.
 */
public class KthLargestNumberInArray {

    public static void main(String[] args) {
        int size = 1000000;
        int kth = 1000;
        int[] array = new Random().ints(size, -100000, 100000).toArray();
//        System.out.println(Arrays.toString(array));
//        int[] array = {10, -1, 10};

        long start = System.currentTimeMillis();
        int n = kthLargestNumber2(array, kth);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
//        System.out.println(n);
//        int check = check(array, kth);
//        System.out.println(n == check);
//        System.out.println(check);

        start = System.currentTimeMillis();
        n = kthLargestNumber1(array, kth);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        n = kthLargestNumber3(array, kth);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));


    }

    static int check(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static int kthLargestNumber1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    static int kthLargestNumber2(int[] nums, int k) {
        Comparator<Integer> comparator = (a, b) -> a - b;
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, comparator);

        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (comparator.compare(num, queue.peek()) > 0) {
                queue.offer(num);
                queue.poll();
            }
        }
        return queue.peek();
    }

    public static int kthLargestNumber3(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    private String quickSelect(String nums[], int start, int end, int k) {
        int i = start;
        int j = end;
        String pivot = nums[i + (j - i) / 2];
        while (i <= j) {
            while (i <= j && compare(pivot, nums[i]) < 0) i++;
            while (j >= 0 && compare(nums[j], pivot) < 0) j--;
            if (i <= j) swap(nums, i++, j--);
        }
        if (start + k - 1 <= j) {
            return quickSelect(nums, start, j, k);
        } else if (start + k - 1 >= i) {
            return quickSelect(nums, i, end, k - (i - start));
        } else {
            return nums[j + 1];
        }
    }

    private void swap(String nums[], int i, int j) {
        String temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int compare(String s1, String s2) {
        return s1.length() == s2.length() ? s1.compareTo(s2) : Integer.compare(s1.length(), s2.length());
    }
}
