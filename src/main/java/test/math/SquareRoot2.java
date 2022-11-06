package test.math;

/**
 * Given a double number, x, and an integer, n, write a function to calculate x raised to the power n.
 */
public class SquareRoot2 {

    // EPSILON is used to denote a small quantity, like an error,
    // or perhaps a term which will be taken to zero in some limit
    private static final double EPSILON = 0.00001;

    public static void main(String[] args) {
        double result = squareRoot(16.0);
        System.out.println(result);
    }

    public static double squareRoot(double num) {
        double low = 0;

        // square root can never be more than
        // half of number except if number is <= 1
        // so square root of any number always lies
        // between 0 and 1 + (num / 2)
        double high = 1 + num / 2;

        while (low < high) {
            double mid = (low + high) / 2;
            double sqr = mid * mid;

            // we can't do a == b for doubles because
            // of rounding errors, so we use error threshold
            // EPSILON. Two doubles a and b are equal if
            // abs(a-b) <= EPSILON
            double diff = Math.abs(num - sqr);

            if (diff <= EPSILON) {
                return mid;
            }

            // if sqr < num, set low = mid (square root lies in the upper half, i.e., between mid
            // and high)
            if (sqr < num) {
                low = mid;
            }

            // if sqr > num, set high = mid (square root lies in the lower half, i.e., between low
            // and mid)
            else {
                high = mid;
            }
        }

        return -1;
    }
}
