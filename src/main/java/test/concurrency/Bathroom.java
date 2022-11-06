package test.concurrency;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class Bathroom {
    enum InUseBy {
        MEN,
        WOMEN,
        NONE
    }

    private final int max = 3;
    private final Semaphore bathCapacity = new Semaphore(max);
    private int count = 0;
    private InUseBy inUseBy = InUseBy.NONE;

    synchronized void maleUseBathroom() throws InterruptedException {
        while (inUseBy == InUseBy.WOMEN) {
            System.out.println(Thread.currentThread().getName() + " WAITING; use: " + inUseBy + "; count: " + count);
            wait();
        }
        bathCapacity.acquire();
        inUseBy = InUseBy.MEN;
        count++;
    }

    synchronized void femaleUseBathroom() throws InterruptedException {
        while (inUseBy == InUseBy.MEN) {
            System.out.println(Thread.currentThread().getName() + " WAITING use: " + inUseBy + "; count: " + count);
            wait();
        }

        bathCapacity.acquire();
        inUseBy = InUseBy.WOMEN;
        count++;
    }

    synchronized void exit() {
        count--;
        if (bathCapacity.availablePermits() == max) {
            inUseBy = InUseBy.NONE;
        }
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " LEFT; use: " + inUseBy + "; count: " + count);
    }

    public void wash() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " WASHING; use: " + inUseBy + "; count: " + count);
        Thread.sleep(new Random().nextLong(500, 2000));
        bathCapacity.release();
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Thread> threads = new HashSet<>();

        Bathroom bathroom = new Bathroom();

        for (int i = 0; i < 10; i++) {
            Thread man = new Thread(() -> {
                try {
                    bathroom.maleUseBathroom();
                    bathroom.wash();
                    bathroom.exit();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "MAN" + i);
            threads.add(man);
        }

        for (int i = 0; i < 10; i++) {
            Thread woman = new Thread(() -> {
                try {
                    bathroom.femaleUseBathroom();
                    bathroom.wash();
                    bathroom.exit();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "WOMAN" + i);
            threads.add(woman);
        }

        for (Thread t : threads) {
            Thread.sleep(new Random().nextLong(0, 1000));
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }
}
