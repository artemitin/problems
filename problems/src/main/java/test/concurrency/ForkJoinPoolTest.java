package test.concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ForkJoinPoolTest {

    private static final Map<String, Set<String>> STATS = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
//        int[] array = getInitArray(Integer.MAX_VALUE >> 2);
        int[] array = getInitArray(100);

        long start = System.currentTimeMillis();

        long sum = countSumRecStart(array); //2500
//        long sum = countSum(array);           // 370

        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Stats size: " + STATS.size());
        String result = STATS.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e -> "task: " + e.getKey() + "; executors: " + String.join(",", e.getValue()))
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = 1;
        }
        return array;
    }

    private static long countSumRecStart(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        return new SumTask(array, 0, array.length - 1, "0").fork().join();
    }

    private static class SumTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int left;
        private final int right;
        private final String task;

        public SumTask(int[] array, int left, int right, String task) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.task = task;
        }

        @Override
        protected Integer compute() {
            if (left == right) {
                try {
                    System.out.println("task " + task + "; startedBy: " + Thread.currentThread().getName());
                    STATS.computeIfAbsent(task, k -> {
                        Set<String> set = new HashSet<>();
                        set.add(Thread.currentThread().getName());
                        return set;
                    });
                    Thread.sleep(1000);
                    STATS.get(task).add(Thread.currentThread().getName());
                    System.out.println("task " + task + "; continuedBy: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return array[left];
            }
            int mid = (right + left) >> 1;
            ForkJoinTask<Integer> leftTask =
                    new SumTask(array, left, mid, "" + left + "|" + mid).fork();
            ForkJoinTask<Integer> rightTask =
                    new SumTask(array, mid + 1, right, "" + (mid + 1) + "|" + right).fork();
            return leftTask.join() + rightTask.join();
        }
    }
}
