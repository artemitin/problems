package test.dynamic;

/**
 * Find the number of ways a player can score n runs.
 *
 * Imagine a game where, in each turn, a player can score either 1, 2 or 4 runs.
 *  Given a score, n, find the total number of ways to score n runs.
 */
public class GameScoring {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        int result = scoringOptions(n);
        System.out.println(count);
        System.out.println(result);
    }

    // Scoring options are 1, 2, and 4
    public static int scoringOptions(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        count++;
        return scoringOptions(n - 1) + scoringOptions(n - 2) + scoringOptions(n - 4);
    }
}