package test.misc;

import java.util.Random;

public class Test {

    @org.junit.jupiter.api.Test
    void test() {
        long start = System.currentTimeMillis();
        byte[] bytes = new byte[10];
        System.out.println(bytes[1]);

        System.out.println(System.currentTimeMillis() - start);
    }
}
