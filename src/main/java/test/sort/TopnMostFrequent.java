package test.sort;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

//Top K Frequent Elements

public class TopnMostFrequent {

    public static void main(String[] args) throws InterruptedException {
//        int[] arr = {5, 2, 5, 5, 2, 1, 1, 1};
        int length = 100000;
        int k = 10000;
        int[] arr = new Random().ints(length, 0, 20000).toArray();

        Function<Supplier<?>, ?> measure = runnable -> {
            long start = System.currentTimeMillis();
            Object r = runnable.get();
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
            return r;
        };

        int[] result = (int[]) measure.apply(() -> topKFrequent(arr, k));

//        System.out.println(Arrays.toString(result));
        System.out.println(result.length);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        int[] freq = new int[20000];
        for(int x: nums) {
            freq[x]++;
        }

        Comparator<Integer> comparator = (a, b) -> freq[a] - freq[b];
        Queue<Integer> queue = new PriorityQueue<>(k, comparator);

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                if (queue.size() < k) {
                    queue.offer(i);
                } else if (comparator.compare(i, queue.peek()) > 0) {
                    queue.poll();
                    queue.offer(i);
                }
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
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
