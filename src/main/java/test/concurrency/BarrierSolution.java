package test.concurrency;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BarrierSolution {
    public static void main(String[] args) throws InterruptedException {
        Set<Thread> threads = new HashSet<>();

        Barrier barrier = new Barrier(3);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    barrier.enter();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threads.add(t);
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

class Barrier {

    private final int max;
    private int waitAtBarrier = 0;
    private int released = 0;

    public Barrier(int max) {
        this.max = max;
    }

    public synchronized void enter() throws InterruptedException {
        // block any new threads from proceeding till,
        // all threads from previous barrier are released
        while(waitAtBarrier == max) {
            wait();
        }

        // increment the counter whenever a thread arrives at the barrier.
        waitAtBarrier++;

        if (waitAtBarrier == max) {
            // wake up all the threads.
            notifyAll();
            released = max;
        } else {
            System.out.println(Thread.currentThread().getName() + ": WAIT; waitCount: " + waitAtBarrier);
            // wait till all threads reach barrier
            while (waitAtBarrier < max) {
                wait();
            }
        }

        released--;
        if (released == 0) {
            // remember to wake up any threads
            // waiting on line#49
            waitAtBarrier = 0;
            notifyAll();
        }

        System.out.println(Thread.currentThread().getName() + ": PASS; waitCount: " + waitAtBarrier);

    }
}