package test.tree;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Transform an array to a Max Heap
 */
public class HeapifyArray {

    public static void main(String[] args) {
//        siftDownTest();
        heapifyTest();
    }

    private static void heapifyTest() {
//        int[] array = ThreadLocalRandom.current().ints(7, 0, 10).toArray();
        int[] array = {7, 9, 8, 6, 9, 1, 6};
        System.out.println("Random Array: " + Arrays.toString(array));
        System.out.println(printHeap(array));
        validateHeap(array);

        System.out.println("\nArray shall be heapified\n");

        heapify(array);
        System.out.println(printHeap(array));
        validateHeap(array);
    }

    private static void heapify(int[] array) {
        for (int i = (array.length / 2) - 1; i >= 0; i--) {
            heapify(array, i);
        }
    }

    // creates a min-heap from a random array
    private static void heapify(int[] array, int parent) {
        System.out.println("Heapifying: [" + parent + "]: " + array[parent]);
        System.out.println(Arrays.toString(array));
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        int min = parent;
        if (left < array.length && array[left] < array[parent]) {
            min = left;
        }
        if (right < array.length && array[right] < array[min]) {
            min = right;
        }
        if (min != parent) {
            swap(array, parent, min);
            if (2 * min + 1 < array.length) {
                System.out.println("going recursive");
                heapify(array, min);
            }
        } else {
            System.out.println("swap skipped for index " + min);
        }
    }

    private static void siftDownTest() {
        int[] heap = {0, 4, 5, 6, 20, 10, 12, 13, 14, 18, 19}; //wrong heap
        System.out.println("Min Heap: " + Arrays.toString(heap));
        System.out.println(printHeap(heap));
        validateHeap(heap);

        System.out.println("\nHeap shall be restored\n");

        //restore heap property
        siftDown(heap, 4);
        System.out.println(printHeap(heap));
        validateHeap(heap);
    }


    // restores min-heap for the particular node
    private static void siftDown(int[] heap, int parent) {
        while (2 * parent + 1 < heap.length) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            int min = left;
            if (right < heap.length && heap[right] < heap[left]) {
                min = right;
            }
            if (heap[parent] <= heap[min]) {
                break;
            }
            swap(heap, parent, min);
            parent = min;
        }
    }

    private static void swap(int[] array, int i1, int i2) {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    private static void validateHeap(int[] heap) {
        validateSubtree(0, heap);
    }

    private static void validateSubtree(int iRoot, int[] heap) {
        int iL = 2 * iRoot + 1;
        int iR = 2 * iRoot + 2;
        if (iL < heap.length) {
            if (heap[iRoot] > heap[iL]) {
                System.out.println(String.format("Error! parent [%s]:%s > left: [%s]:%s",
                        iRoot, heap[iRoot], iL, heap[iL]));
            }
            validateSubtree(iL, heap);
        }
        if (iR < heap.length) {
            if (heap[iRoot] > heap[iR]) {
                System.out.println(String.format("Error! parent [%s]:%s > right: [%s]:%s",
                        iRoot, heap[iRoot], iR, heap[iR]));
            }
            validateSubtree(iR, heap);
        }
    }

    private static String printHeap(int[] heap) {
        StringBuilder sb = new StringBuilder();
        int nOnLevel = 1;
        int level = 1;
        int count = 0;
        int i = 0;

        while (i < heap.length) {
            count++;
            if (count == 1) {
                sb.append("Level: ").append(level).append("; items: ").append(Math.min(nOnLevel, heap.length - i)).append("\t");
            }
            sb.append("[").append(i).append("]:").append(heap[i]);
            if (count < nOnLevel) {
                sb.append(" ");
            } else {
                level++;
                sb.append('\n');
                nOnLevel *= 2;
                count = 0;
            }
            i++;
        }

        return sb.toString();
    }

    static int[] toMinHeap(int[] input) {
        Arrays.sort(input);
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i];
        }
        return result;
    }

    static int[] toMaxHeap(int[] input) {
        Arrays.sort(input);
        int[] result = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            result[input.length - 1 - i] = input[i];
        }
        return result;
    }
}
