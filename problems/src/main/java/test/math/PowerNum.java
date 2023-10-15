package test.math;

/**
 * Given a double number, x, and an integer, n, write a function to calculate x raised to the power n.
 */
public class PowerNum {

    public static void main(String[] args) {
        double result = power(2, -2);
        System.out.println(result);
    }

    static double power(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double result = 1;

        for (int i = 0; i < Math.abs(n); i++) {
            result *= x;
        }

        if (n < 0) {
            return 1 / result;
        }
        return result;
    }
}
