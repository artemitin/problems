package test;

import java.util.function.Supplier;

public class MeasureUtil {

    public static <T> T runMeasured(Supplier<T> runnable) {
        long start = System.currentTimeMillis();
        T topNlist = runnable.get();
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
        return topNlist;
    }
}
