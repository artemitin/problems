package test.dynamic;

/**
 * Find the nth number in the Fibonacci sequence.
 */
public class Fibo {

    public static void main(String[] args) {
        int result = getFibonacci(8);
        System.out.println(result);
    }

    static int getFibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int nth = 0;
        int preprev = 0;
        int prev = 1;

        for(int i = 2; i <= n; i++) {
            nth = preprev + prev;
            preprev = prev;
            prev = nth;
        }

        return nth;
    }

}
