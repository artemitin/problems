package test.concurrency;

class ReorderingExample {

    // shared variables
    int sharedA = 0;
    int sharedB = 0;

    // executed by thread1
    void method1() {
        synchronized (this) {
            sharedA = 1;
            sharedB = 2;
        }
        System.out.println("Setter exited:      " + System.currentTimeMillis() + " " + System.nanoTime());
    }

    // executed by thread2
    void method2() {
        System.out.println("A = " + sharedA + "; B = " + sharedB + "; time: " + System.currentTimeMillis() + " " + System.nanoTime());
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            final ReorderingExample reorderingExample = new ReorderingExample();
            Thread thread1 = new Thread(reorderingExample::method1);
            Thread thread2 = new Thread(reorderingExample::method2);

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("---------------------------");
        }
    }
}