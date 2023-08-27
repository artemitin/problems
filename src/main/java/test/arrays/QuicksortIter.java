package test.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given an array of integers nums, sort it in ascending order using the quicksort algorithm.
 */
public class QuicksortIter {

    public static void main(String[] args) {
        int N = 1300000000;
        int[] arr = new int[N];
        for (int i = N - 1; i > 0; i--) {
            arr[i] = i;
        }
//        Arrays.sort(arr);

//        int[] arr = {55, 23, 26, 2, 25};

        long start = System.currentTimeMillis();
        quickSortIter(arr);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
//        System.out.println(Arrays.toString(arr));
    }

    private static void quickSortIter(int[] arr) {
        //init
        int iStack = 0;
        int low = 0;
        int high = arr.length - 1;
        int[] stack = new int[]{-1, -1};
        stack[iStack++] = low;
        stack[iStack++] = high;

        while (iStack > 0) {
            high = stack[--iStack];
            low = stack[--iStack];
            // pivot_index is the partitioning index
            int iPivot = partition(arr, low, high);

            // first partition
            if (low < iPivot) {
                stack[iStack++] = low;
                stack[iStack++] = iPivot - 1;
            }

            //second partition
            if (high > iPivot) {
                stack[iStack++] = iPivot + 1;
                stack[iStack++] = high;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotI =low;
        int storeI = low + 1;

        for (int i = low + 1; i <= high; i++) {
            if (arr[i] <= arr[pivotI]) {
                swap(arr, i, storeI++);
            }
        }
        swap(arr, pivotI, storeI - 1);

        return storeI - 1;
    }


    private static void swap(int[] arr, int i1, int i2) {
//        arr[i1] = arr[i1] ^ arr[i2];
//        arr[i2] = arr[i1] ^ arr[i2];
//        arr[i1] = arr[i1] ^ arr[i2];
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}
