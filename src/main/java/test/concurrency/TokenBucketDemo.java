package test.concurrency;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketDemo {
    public static void main(String[] args) throws InterruptedException {
        TokenBucketFilter.runTestMaxTokenIs1();
    }
}

class TokenBucketFilter {

    private final int maxTokens;
    private final int period;

    private final Queue<String> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition fifo = lock.newCondition();


    private long lastRequestTime = System.currentTimeMillis();
    private long possibleTokens = 0;

    public TokenBucketFilter(int maxTokens, int period) {
        this.maxTokens = maxTokens;
        this.period = period;
    }

    public void getToken() throws InterruptedException {
        lock.lock();
        String threadName = Thread.currentThread().getName();
        queue.add(threadName);

        while (!queue.peek().equals(threadName)) {
            System.out.println("Queue head is " + queue.peek() + " not match current thread: " + threadName);
            fifo.await();
        }

        queue.remove();

        long timeSinceLastRequest = System.currentTimeMillis() - lastRequestTime;
        possibleTokens += timeSinceLastRequest / period;

        if (possibleTokens > maxTokens) {
            possibleTokens = maxTokens;
        }

        if (possibleTokens == 0) {
            long millis = period - timeSinceLastRequest;
            System.out.println("no tokens - have to sleep for " + millis);
            Thread.sleep(millis);
        } else {
            possibleTokens--;
        }
        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting " + threadName + " token at " + (System.currentTimeMillis() / period));
        fifo.signalAll();
        lock.unlock();
    }

    public static void runTestMaxTokenIs1() throws InterruptedException {
        Set<Thread> allThreads = new HashSet<>();
        final TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(2, 1000);

        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    tokenBucketFilter.getToken();
                } catch (InterruptedException ie) {
                    System.out.println("We have a problem");
                }
            });
            thread.setName("Thread_" + i);
            allThreads.add(thread);
        }

        Thread.sleep(10000);

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
