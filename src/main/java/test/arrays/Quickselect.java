package test.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given an array of integers nums, sort it in ascending order using the quicksort algorithm.
 */
public class Quickselect {

    public static void main(String[] args) {
        int N = 1300;
//        int[] arr = IntStream.range(0, N).toArray();
//        Arrays.sort(arr);
//        int[] arr = new Random().ints(12000, 0, 12000).toArray();
        int[] arr = {55, 23, 26, 2, 25};
        long start = System.currentTimeMillis();
        int nth = 2;
        int i = quickSelect(arr, nth);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
//        System.out.println(Arrays.toString(arr));
        System.out.printf("%sth largest: %s", nth, i);
    }

    static int quickSelect(int[] arr, int n) {
        int low = 0;
        int high = arr.length - 1;
        // iPivot is the Kth largest
        while (true) {
            int iPivot = partition(arr, low, high);
            if (iPivot == n - 1) {
                return arr[iPivot];
            }
            if (n - 1 < iPivot) {
                // find in left partition
                high = iPivot - 1;
            }
            if (iPivot < n - 1) {
                // find in right partition
                low = iPivot + 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // Initializing pivot's index to low
        int pivotValue = arr[low];
        int i = low;
        int j = high;

        // Loop till i pointer crosses j pointer
        while (i < j) {
            // Increment the 'i' pointer till it finds an element greater than pivot
            while (i <= high && arr[i] <= pivotValue)
                i++;
            // Decrement the 'j' pointer till it finds an element less than pivot
            while (arr[j] > pivotValue)
                j--;

            // Swap the numbers on 'i' and 'j'
            if (i < j) {
                // swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // Swap pivot element with element on 'j' pointer.
        arr[low] = arr[j];
        arr[j] = pivotValue;

        // return the pivot index
        return j;
    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}
