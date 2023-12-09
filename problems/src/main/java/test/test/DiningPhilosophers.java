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
                final String name = Thread.currentThread().getName();

                while (true) {
                    System.out.println(name + ": PREPARE EAT");

                    String left = null;
                    String right = null;

                    synchronized (listForks) {
                        if (listForks.size() >= 2) {
                            left = listForks.takeFirst();
                            System.out.println(name + ": TAKE LEFT");

                            right = listForks.takeFirst();
                            System.out.println(name + ": TAKE RIGHT");
                        }
                    }

                    System.out.println(name + ": START EAT");
                    Thread.sleep(1000);
                    System.out.println(name + ": FINISH EAT: ");

                    if (left != null) {
                        listForks.addLast(left);
                        System.out.println(name + ": PUT LEFT");
                    }

                    if (right != null) {
                        listForks.addLast(right);
                        System.out.println(name + ": PUT RIGHT");
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}