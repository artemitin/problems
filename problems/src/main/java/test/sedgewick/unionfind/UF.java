package test.sedgewick.unionfind;

import java.util.Arrays;
import java.util.Random;

public class UF {
    private static final int N = 200000000;
    private static final int[] id = new int[N];
    private static final int[] weights = new int[N];
//    private static final int[]  id = {0, 1, 9, 4, 9, 6, 6, 7, 8, 9};

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        Arrays.fill(weights, 1);

        Random r = new Random();

        System.out.println("measure union");
        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            union(r.nextInt(N), r.nextInt(N));
//        }
        for (int i = 0; i < 10000000 - 1; i++) {
            union(i, i + 1);
        }
        System.out.println((System.currentTimeMillis() - start));

        System.out.println("measure find"); //5
        boolean result = false;
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            result = connected(r.nextInt(N), r.nextInt(N));
        }
        System.out.println((System.currentTimeMillis() - start));

        System.out.println(result);

//        union(3, 5);
//        System.out.println(Arrays.toString(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
//        System.out.println(Arrays.toString(id));
    }

    private static void union(int i1, int i2) {
        if (i1 == i2)
            return;

        int r1 = root(i1);
        int r2 = root(i2);

        if (r1 == r2) {
            return;
        }

        //2200
        if (weights[r1] > weights[r2]) {
            id[r2] = r1;
            weights[r1] += weights[r2];
        } else {
            id[r1] = r2;
            weights[r2] += weights[r1];
        }

        //1500
//        id[r1] = r2;
    }

    private static boolean connected(int i1, int i2) {
        return root(i1) == root(i2);
    }

    public static int find(int i) {
        return i;
    }

    private static int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }
}
