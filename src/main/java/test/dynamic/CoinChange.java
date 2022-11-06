package test.dynamic;

import java.util.Arrays;

/**
 * Given an integer array representing coins of different denominations and an integer amount representing a total amount of money,
 * return the number of ways the coins can sum up to the amount.
 */
public class CoinChange {

    public static void main(String[] args) {
        int result = solveCoinChange(new int[]{1, 2, 5}, 7);
        System.out.println(result);
    }

    public static int solveCoinChange(int[] denominations, int amount) {
        // Initializing our solution array
        int[] results = new int[amount + 1];

        // Each array index holds the number of ways to
        // reach an amount equal to the index
        results[0] = 1;

        for (int den : denominations) {
            // Iteratively calculate the number of ways to reach an
            // amount and store it into the solutions array
            for (int i = den; i <= amount; i++) {
                results[i] += results[i - den];
            }
        }

        return results[amount];
    }
}