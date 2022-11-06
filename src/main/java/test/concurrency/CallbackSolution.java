package test.concurrency;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CallbackSolution {
    public static void main(String args[]) throws InterruptedException {
        DeferredCallbackExecutor.runTestTenCallbacks();
    }
}

class DeferredCallbackExecutor {

    PriorityQueue<CallBack> q = new PriorityQueue<>((o1, o2) -> (int) (o1.executeAt - o2.executeAt));
    ReentrantLock lock = new ReentrantLock();
    Condition newCallbackArrived = lock.newCondition();

    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return q.peek().executeAt - currentTime;
    }

    public void start() throws InterruptedException {
        long sleepFor;

        while (true) {
            lock.lock();

            while (q.size() == 0) {
                newCallbackArrived.await();
            }

            while (q.size() != 0) {
                sleepFor = findSleepDuration();

                if (sleepFor <= 0)
                    break;

                newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
            }

            CallBack cb = q.poll();
            System.out.printf("Executed at %s required at %s: message: %s%n",
                            System.currentTimeMillis() / 1000, cb.executeAt / 1000, cb.message);

            lock.unlock();
        }
    }

    public void registerCallback(CallBack callBack) {
        lock.lock();
        q.add(callBack);
        newCallbackArrived.signal();
        lock.unlock();
    }

    static class CallBack {
        long executeAt;
        String message;

        public CallBack(long executeAfter, String message) {
            this.executeAt = System.currentTimeMillis() + (executeAfter * 1000);
            this.message = message;
        }
    }

    public static void runTestTenCallbacks() throws InterruptedException {
        Set<Thread> allThreads = new HashSet<>();
        final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

        Thread service = new Thread(() -> {
            try {
                deferredCallbackExecutor.start();
            } catch (InterruptedException ie) {

            }
        });

        service.start();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                CallBack cb = new CallBack(1, "Hello this is " + Thread.currentThread().getName());
                deferredCallbackExecutor.registerCallback(cb);
            });
            thread.setName("Thread_" + (i + 1));
            thread.start();
            allThreads.add(thread);
            Thread.sleep(1000);
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}