package test.math;

/**
 * Given a double number, x, and an integer, n, write a function to calculate x raised to the power n.
 */
public class SquareRoot {

    // EPSILON is used to denote a small quantity, like an error,
    // or perhaps a term which will be taken to zero in some limit
    private static final double EPSILON = 0.00001;

    public static void main(String[] args) {
        double result = squareRoot(16.0);
        System.out.println(result);
    }

    private static double squareRootRec(double num, double low, double high) {
        double mid = (low + high) / 2;
        double sqr = mid * mid;

        // finds difference of calculated square and given number
        double diff = Math.abs(sqr - num);

        // we can't do a == b for doubles because
        // of rounding errors, so we use error threshold
        // EPSILON. Two doubles a and b are equal if
        // abs(a-b) <= EPSILON

        if (diff <= EPSILON) {
            return mid;
        }

        // if the square of mid is smaller than num, then in the next recursive call,
        // low will be changed to mid and high remains unchanged.
        if (sqr < num) {
            return squareRootRec(num, mid, high);
        }

        // otherwise, that is, if the square of mid is greater than num, then,
        // in the next recursive call, high will be changed to mid and
        // low remains unchanged.
        return squareRootRec(num, low, mid);
    }

    public static double squareRoot(double num) {
        if (num < 0) {
            return -1;
        } else {
            // calls recursive function with given number, low and high
            return squareRootRec(num, 0, 1 + num / 2);
        }
    }
}
