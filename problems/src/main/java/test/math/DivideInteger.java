package test.math;

/**
 * Divide two integers without using '/' or '*' operators.
 */
public class DivideInteger {

    public static void main(String[] args) {
        int result = integerDivide(40, 5);
        System.out.println(result);
    }

    public static int integerDivide(int dividend, int divisor) {
        // We will return -1 if the divisor is '0'.
        if (divisor == 0) {
            return -1;
        }

        // Returns 0 if dividend is less than divisor
        if (dividend < divisor) {
            return 0;
        }

        // Returns 1 if dividend is equal to divisor
        else if (dividend == divisor) {
            return 1;
        }

        // Returns dividend is divisor is equal to 1
        else if (divisor == 1) {
            return dividend;
        }

        int q = 1;
        int val = divisor;

        // If divisor is less than dividend
        while (val < dividend) {
            val <<= 1;
            // functionally equivalent to, but faster than 'val = val + val;
            q <<= 1;
            // functionally equivalent to, but faster than 'q = q + q;
        }

        // We have increased val so that it's higher than the dividend,
        // which means that we need to move back one step and handle the overflow
        if (val > dividend) {
            val >>= 1;
            // functionally equivalent to, but faster than 'val = val / 2;'
            q >>= 1;
            // functionally equivalent to, but faster than 'q = q / 2;'

            return q + integerDivide(dividend - val, divisor);
        }

        return q;
    }

    public static int integerDivideNaive(int dividend, int divisor) {
        int n = 0;
        int sum = divisor;
        while (sum <= dividend) {
            sum += divisor;
            n++;
        }

        return n;
    }

}
