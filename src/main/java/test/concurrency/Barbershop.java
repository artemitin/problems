package test.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barbershop {

    public static final int CUSTOMERS = 100;
    private final int maxCustomers;
    private final Queue<Customer> queue;
    private final Lock queueLock = new ReentrantLock();
    private final Condition customerExist = queueLock.newCondition();
    private final Lock chairLock = new ReentrantLock();

    public Barbershop(int maxCustomers) {
        this.maxCustomers = maxCustomers;
        this.queue = new LinkedList<>();
    }

    public boolean enterQueue(Customer customer) {
        queueLock.lock();
        System.out.println("queue size: " + queue.size());
        try {
            if (queue.size() < maxCustomers) {
                queue.add(customer);
                System.out.println("customer added to queue: " + customer.getName());
                if (queue.size() == 1) { //1st customer
                    customerExist.signal();
                }
                return true;
            } else {
                System.out.println("queue is full! " + customer.getName() + " not added!");
            }
        } finally {
            queueLock.unlock();
        }
        return false;
    }

    public Customer waitOrGetNextCustomer() {
        queueLock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("No customers - barber goes to sleep");
                customerExist.await();
            }
            return queue.remove();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            queueLock.unlock();
        }
    }

    public Queue<Customer> getQueue() {
        return queue;
    }

    public int getMaxCustomers() {
        return maxCustomers;
    }

    public Lock getQueueLock() {
        return queueLock;
    }

    public Lock getChairLock() {
        return chairLock;
    }

    public static void main(String[] args) throws InterruptedException {
        Barbershop barbershop = new Barbershop(5);

        ExecutorService executor = Executors.newFixedThreadPool(CUSTOMERS + 1);

        executor.submit(new Barber(barbershop));

        for (int i = 0; i < CUSTOMERS; i++) {
            Thread.sleep(2000);
            executor.submit(new Customer(barbershop, "customer" + i));
        }

        executor.shutdown();
    }
}

class Barber implements Runnable {

    private final Barbershop barbershop;

    public Barber(Barbershop barbershop) {
        this.barbershop = barbershop;
    }

    @Override
    public void run() {
        while (true) {
            Customer customer = barbershop.waitOrGetNextCustomer();
            serve(customer);
        }
    }

    public void serve(Customer customer) {
        System.out.println(customer + " serving");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Customer implements Runnable {
    private final String name;
    private final Barbershop barbershop;

    public Customer(Barbershop barbershop, String name) {
        this.barbershop = barbershop;
        this.name = name;
    }

    @Override
    public void run() {
        barbershop.enterQueue(this);
        //try enter the queue
        //check barber available
        //wake up barber
        //go to barber
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "'}";
    }
}
