package test.test;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Dining philosophers
 */
public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {
        BlockingDeque<String> listForks = Stream.of("1", "2", "3", "4", "5")
                .collect(
                        () -> new LinkedBlockingDeque<>(5),
                        LinkedBlockingDeque::addLast,
                        LinkedBlockingDeque::addAll
                );

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                int counter = 0;
                final String name = Thread.currentThread().getName();

                while (true) {
                    System.out.println(name + ": PREPARE DINE");

                    String left = null;
                    String right = null;

                    synchronized (listForks) {
                        if (listForks.size() >= 2) {
                            left = listForks.takeFirst();
                            System.out.println(name + ": TOOK LEFT");

                            right = listForks.takeFirst();
                            System.out.println(name + ": TOOK RIGHT");
                        }
                    }

                    System.out.println(name + ": START DINE");
//                    Thread.sleep(1000);
                    System.out.println(name + ": FINISH DINE: " + ++counter);

                    if (left != null) {
                        listForks.addLast(left);
                        System.out.println(name + ": RETURN LEFT");
                    }

                    if (right != null) {
                        listForks.addLast(right);
                        System.out.println(name + ": RETURN RIGHT");
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }

    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;

        for (int num : nums) {
            if (num <= k) {
                k++;
                sum += num;
            }
        }

        return (long) (1 + k) * k / 2 - sum;
    }
}