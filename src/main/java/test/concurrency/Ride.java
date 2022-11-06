package test.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Ride {

    private int reps = 0;
    private int dems = 0;

    CyclicBarrier barrier = new CyclicBarrier(4);
    ReentrantLock lock = new ReentrantLock();
    Semaphore demsWaiting = new Semaphore(0);
    Semaphore repsWaiting = new Semaphore(0);

    void seatDemocrat() throws InterruptedException, BrokenBarrierException {
        boolean rideLeader = false;
        lock.lock();

        dems++;

        if (dems == 4) {
            // Seat all the dems in the Uber ride.
            demsWaiting.release(3);
            dems -= 4;
            rideLeader = true;
        } else if (dems == 2 && reps >= 2) {
            // Seat 2 dems & 2 reps
            demsWaiting.release(1);
            repsWaiting.release(2);
            rideLeader = true;
            dems -= 2;
            reps -= 2;
        } else {
            lock.unlock();
            demsWaiting.acquire();
        }

        seated();
        barrier.await();

        if (rideLeader) {
            drive();
            lock.unlock();
        }
    }

    void seatRepublican() throws InterruptedException, BrokenBarrierException {
        boolean rideLeader = false;
        lock.lock();

        reps++;

        if (reps == 4) {
            // Seat all the reps in the Uber ride.
            repsWaiting.release(3);
            reps -= 4;
            rideLeader = true;
        } else if (reps == 2 && dems >= 2) {
            // Seat 2 reps & 2 dems
            repsWaiting.release(1);
            demsWaiting.release(2);
            rideLeader = true;
            reps -= 2;
            dems -= 2;
        } else {
            lock.unlock();
            repsWaiting.acquire();
        }

        seated();
        barrier.await();

        if (rideLeader) {
            drive();
            lock.unlock();
        }
    }

    void seated() {
        System.out.println(Thread.currentThread().getName() + "  seated");
        System.out.flush();
    }

    void drive() {
        System.out.println("Uber Ride on Its wayyyy... with ride leader " + Thread.currentThread().getName());
        System.out.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        final Ride uberSeatingProblem = new Ride();
        Set<Thread> allThreads = new HashSet<>();

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(() -> {
                try {
                    uberSeatingProblem.seatDemocrat();
                } catch (InterruptedException | BrokenBarrierException ie) {
                    System.out.println("We have a problem");

                }

            });
            thread.setName("Democrat_" + (i + 1));
            allThreads.add(thread);

            Thread.sleep(50);
        }

        for (int i = 0; i < 14; i++) {
            Thread thread = new Thread(() -> {
                try {
                    uberSeatingProblem.seatRepublican();
                } catch (InterruptedException | BrokenBarrierException ie) {
                    System.out.println("We have a problem");

                }
            });
            thread.setName("Republican_" + (i + 1));
            allThreads.add(thread);
            Thread.sleep(20);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
