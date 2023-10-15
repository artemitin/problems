package test.arrays;

/**
 * Given an array of integers nums, sort it in ascending order using the merge sort algorithm.
 */
public class MergeSortIter {

    public static void main(String[] args) {
        int N = 1300000000;
        int[] arr = new int[N];
        for (int i = N - 1; i > 0; i--) {
            arr[i] = i;
        }

//        int[] arr = {38, 27, 43, 3, 9, 82, 10};
//        int[] arr = {55, 23, 26, 2, 25};
//        int[] arr = {4, 3, 2, 1};
        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
    }

    static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /* 'left' is for left index and 'right' is right index of the sub-array of arr to be sorted */
    private static void mergeSort(int[] arr, int left, int right) {
        //init
        int iStack = 0;
        int[] stack = new int[2];
        stack[iStack++] = left;
        stack[iStack++] = right;

        while (iStack > 0) {
            right = stack[--iStack];
            left = stack[--iStack];

            if (left < right) {
                // Same as (left + right) / 2, but avoids overflow
                // for large left and right
                int mid = left + (right - left) / 2;
                merge(arr, left, mid, right);
                stack[iStack++] = left;
                stack[iStack++] = mid;

                // Sort first and second halves
                mergeSort(arr, left, mid);
                mergeSort(arr, mid + 1, right);

                merge(arr, left, mid, right);
            }
        }

    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    // Inplace Implementation
    static void merge(int[] arr, int left, int mid, int right) {
        int start1 = left;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = right;

        // If the direct merge is already sorted
        if (arr[end1] <= arr[start2]) {
            return;
        }

        // Two pointers to maintain left
        // of both arrays to merge
        while (start1 <= end1 && start2 <= end2) {
            // If element 1 is in right place
            if (arr[start1] <= arr[start2]) {
                start1++;
            } else {
                int value = arr[start2];
                int index = start2;

                // Shift all the elements between element 1
                // element 2, right by 1.
                while (index != start1) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[start1] = value;

                // Update all the pointers
                start1++;
                end1++;
                start2++;
            }
        }
    }
}
