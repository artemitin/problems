package test.math;

/**
 * Given a double number, x, and an integer, n, write a function to calculate x raised to the power n.
 */
public class PowerNumLogN {

    public static void main(String[] args) {
        double result = power(2, -2);
        System.out.println(result);
    }

    static double powerRec(double x, int n) {
        // returns 1 if power is 0
        if (n == 0)
            return 1;

        // returns given number if power is 1
        if (n == 1)
            return x;

        // keep dividing power by 2 recursively
        // until the base case reached, i.e., n == 1
        double temp = powerRec(x, n / 2);

        // if power is even, the result is temp * temp
        if (n % 2 == 0) {
            return temp * temp;
        }
        // if power is odd, the result is x * temp * temp
        else {
            return x * temp * temp;
        }
    }

    static double power(double x, int n) {
        // Handles negative power
        boolean isNegative = false;
        if (n < 0) {
            isNegative = true;
            n *= -1;
        }

        double result = powerRec(x, n);

        // if power is negative, result is divided by 1
        // to return negative power
        if (isNegative) {
            return 1 / result;
        }

        return result;
    }
}
