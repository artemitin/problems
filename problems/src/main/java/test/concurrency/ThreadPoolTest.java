package test.concurrency;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ThreadPoolTest {

    private static final Map<String, Set<String>> STATS = new ConcurrentHashMap<>();
    private static final ExecutorService EXEC = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        int[] array = getInitArray(2);

        long start = System.currentTimeMillis();

        long sum = countSumRecStart(array); //2500

        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Stats size: " + STATS.size());
        String result = STATS.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e -> "task: " + e.getKey() + "; executors: " + String.join(",", e.getValue()))
                .collect(Collectors.joining("\n"));
        System.out.println(result);
        EXEC.shutdownNow();
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = 1;
        }
        return array;
    }

    private static long countSumRecStart(int[] array) throws Exception {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        SumTask sumTask = new SumTask(array, 0, array.length - 1, "0");
        Future<Integer> result = EXEC.submit(sumTask);
        return result.get();
    }

    private static class SumTask implements Callable<Integer> {
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
        public Integer call() throws ExecutionException, InterruptedException {
            if (left == right) {
                try {
                    System.out.println("task " + task + "; startedBy: " + Thread.currentThread().getName());
                    STATS.computeIfAbsent(task, k -> {
                        Set<String> set = new HashSet<>();
                        set.add(Thread.currentThread().getName());
                        return set;
                    });
                    Thread.sleep(100);
                    STATS.get(task).add(Thread.currentThread().getName());
                    System.out.println("task " + task + "; continuedBy: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return array[left];
            }
            int mid = (right + left) >> 1;
            Future<Integer> leftTask = EXEC.submit(new SumTask(array, left, mid, "" + left + "|" + mid));
            Future<Integer> rightTask = EXEC.submit(new SumTask(array, mid + 1, right, "" + (mid + 1) + "|" + right));
            return leftTask.get() + rightTask.get();
        }
    }
}
