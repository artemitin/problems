package test.dynamic;

import java.util.Arrays;

/**
 * Find the number of ways a player can score n runs.
 * <p>
 * Imagine a game where, in each turn, a player can score either 1, 2 or 4 runs.
 * Given a score, n, find the total number of ways to score n runs.
 */
public class GameScoringMemo {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        int result = scoringOptions(n);
        System.out.println(count);
        System.out.println(result);
    }

    // Scoring options are 1, 2, and 4
    public static int scoringOptions(int n) {
        int[] results = new int[n + 1];
        Arrays.fill(results, -1);
        results[0] = 1;

        return scoringOptionsInternal(n, results);
    }

    // Scoring options are 1, 2, and 4
    public static int scoringOptionsInternal(int n, int[] results) {
        if (n < 0) {
            return 0;
        }

        if (results[n] != -1) {
            return results[n];
        }
        count++;
        return results[n] = scoringOptionsInternal(n - 1, results) + scoringOptionsInternal(n - 2, results) + scoringOptionsInternal(n - 4, results);
    }
}