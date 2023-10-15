package test.sort;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

//Top K Frequent Elements

public class TopnMostFrequent {

    public static final int RANGE = 20000000;

    public static void main(String[] args) throws InterruptedException {
//        int[] arr = {4, 2, 3, 2, 3, 1};
        int length = 100000000;
        int k = 100000;
        int[] arr = new Random().ints(length, 0, RANGE).toArray();

        Function<Supplier<?>, ?> measure = runnable -> {
            long start = System.currentTimeMillis();
            Object r = runnable.get();
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
            return r;
        };

        int[] result = (int[]) measure.apply(() -> topKFrequentMaxHeap(arr, k));

//        System.out.println(Arrays.toString(result));
//        System.out.println(result.length);
    }

    //minheap
    public static int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        int[] freq = new int[RANGE];
        for(int x: nums) {
            freq[x]++;
        }

        Comparator<Integer> comparator = (a, b) -> freq[a] - freq[b];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, comparator);

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                if (queue.size() < k) {
                    queue.offer(i);
                } else if (comparator.compare(i, queue.peek()) > 0) {
                    queue.offer(i);
                    queue.poll();
                }
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            Integer poll = queue.poll();
            if (poll != null) {
                result[i] = poll;
            }
        }
        return result;
    }

    //maxHeap less performant
    public static int[] topKFrequentMaxHeap(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        int[] freq = new int[RANGE];
        for(int x: nums) {
            freq[x]++;
        }

        Comparator<Integer> comparator = (a, b) -> freq[b] - freq[a];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, comparator);

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            Integer poll = queue.poll();
            if (poll != null) {
                result[i] = poll;
            }
        }
        return result;
    }

//    int shift = 10000;
//    public int[] topKFrequent(int[] A, int k) {
//        if(k == A.length) return A;
//        int[] freq = new int[20001];
//        int[] ans = new int[k];
//        Queue<Integer> pq = new PriorityQueue<>((a, b) -> freq[a] - freq[b]);
//
//        for(int x: A) {
//            freq[x+shift]++;
//        }
//        for(int i=0; i<freq.length; i++) {
//            if(freq[i] != 0) {
//                pq.add(i);
//                if(pq.size() > k) pq.poll();
//            }
//        }
//        for(int i=0; i<k; i++) {
//            ans[i] = pq.poll()-shift;
//        }
//        return ans;
//    }
}
