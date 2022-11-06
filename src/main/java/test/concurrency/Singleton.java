package test.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class Singleton {

//    private static final Singleton THIS = new Singleton();
    private static Singleton THIS2;

    private Singleton() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Im initialized!!!!!!!!!!!!!!");
    }

    public String method() {
        return this.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Thread> threads = new HashSet<>();

        Set<String> singletons = ConcurrentHashMap.newKeySet();

        int N = 100;

        CyclicBarrier barrier = new CyclicBarrier(N);

        for (int i = 0; i < N; i++) {
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                Singleton instance = Singleton.getInstance2();
                singletons.add(instance.toString());
            });
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Unique singletons: " + singletons.size());
    }

//    public static Singleton getInstance() {
//        return THIS;
//    }

    public static Singleton getInstance2() {
        if (THIS2 == null) {
            synchronized (Singleton.class) {
                if (THIS2 == null) {
                    THIS2 = new Singleton();
                }
            }
        }
        return THIS2;
    }
}
