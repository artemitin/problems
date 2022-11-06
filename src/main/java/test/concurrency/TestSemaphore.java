package test.concurrency;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) throws InterruptedException {
        Semaphore s = new Semaphore(0);

        Thread t = new Thread(() -> {
            try {
                s.acquire();
                System.out.println("aquired!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(s::release);

        t.start();
        t2.start();
        t.join();
    }
}
