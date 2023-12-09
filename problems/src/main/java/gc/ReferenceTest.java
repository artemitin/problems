package gc;

import java.lang.ref.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<String> queue = new ReferenceQueue<>();

        Map<String, Reference<?>> refmap = new HashMap<>();
        String obj1 = "soft " + System.currentTimeMillis();
        refmap.put("abc", new SoftReference<>(obj1, queue));

        String obj3 = "weak " + System.currentTimeMillis();
        System.out.println("Weak Referent Obj is: " + obj3);
        refmap.put("weak", new WeakReference<>(obj3, queue));

        Map<String, String> objMap = new HashMap<>();
        String obj2 = "strong " + System.currentTimeMillis();
        objMap.put("o2", obj2);

        Thread watcher = new Thread(() -> {
            while (true) {
                try {
                    Reference<? extends String> remove = queue.remove(100500);
                    System.out.println("GC cleared object: " + remove.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        watcher.setDaemon(true);
        watcher.start();

        Thread thread = new Thread(() -> {
            long[] array;
            for (long i = 0; i < 10000; i++) {
                array = new long[10000000];
                Arrays.fill(array, ThreadLocalRandom.current().nextLong());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                if (i % (100) == 0) {
                    System.out.println(array[0]);
                    {
                        Reference<?> ref = refmap.get("abc");
                        System.out.println("Soft Ref is: " + ref);
                        if (ref != null) {
                            System.out.println("Soft Referent Obj is: " + ref.get());
                        }
                    }
                    {
                        Reference<?> ref = refmap.get("weak");
                        System.out.println("Weak Ref is: " + ref);
                        if (ref != null) {
                            System.out.println("Weak Referent Obj is: " + ref.get());
                        }
                    }

                    {
                        Object ref = objMap.get("o2");
                        System.out.println("Obj2 is: " + ref);
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        obj1 = null;
        obj2 = null;
        obj3 = null;
        thread.join();
//        System.out.println("Link is: " + obj1);
    }

    static class TestResource extends PhantomReference<String> {
        private long id;
        private TestResource(long id, String referent, ReferenceQueue<String> queue) {
            super(referent, queue);
            this.id = id;
            System.out.println("Phantom ref created: " + id);
        }
        private void close() {
            System.out.println("closed " + id);
        }
    }
}

