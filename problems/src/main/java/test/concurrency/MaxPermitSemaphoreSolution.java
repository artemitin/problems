package test.concurrency;

import java.util.HashSet;
import java.util.Set;

public class MaxPermitSemaphoreSolution {
    public static void main(String[] args) throws InterruptedException {
        MaxPermitSemaphore semaphore = new MaxPermitSemaphore(3);

        Set<Thread> threads = new HashSet<>();
        Set<Thread> releasers = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(semaphore::acquire);
            threads.add(t);
        }

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(semaphore::release);
            releasers.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }

        Thread.sleep(1000);

        for (Thread t : releasers) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        for (Thread t : releasers) {
            t.join();
        }
    }
}

class MaxPermitSemaphore {

    private final int maxPermits;
    private int permits;

    public MaxPermitSemaphore(int maxPermits) {
        this.maxPermits = maxPermits;
        this.permits = maxPermits;
    }

    public synchronized void acquire() {
        while (permits == 0) {
            try {
                System.out.printf("Thread %s has no permit, waiting \n", Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        permits--;

        System.out.printf("Thread %s acquired a permit, available permits: %s \n",
                Thread.currentThread().getName(), permits);
    }

    public synchronized void release() {
        if (permits < maxPermits) {
            permits++;
            System.out.printf("Thread %s released a permit, available permits: %s \n",
                    Thread.currentThread().getName(), permits);
            notifyAll();
        }
    }
}
