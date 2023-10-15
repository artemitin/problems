package test.dynamic;

/**
 * Find the nth number in the Fibonacci sequence.
 */
public class NthTriboNumber {

    public static void main(String[] args) {
        int result = tribonacci(4);
        System.out.println(result);
    }

    static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 1;
        }

        int n0 = 0;
        int n1 = 1;
        int n2 = 1;
        int n3 = 0;

        for(int i = 3; i <= n; i++) {
            n3 = n0 + n1 + n2;
            n0 = n1;
            n1 = n2;
            n2 = n3;
        }

        return n3;
    }

}
