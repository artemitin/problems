package test.dynamic;

import java.util.Arrays;

/**
 * Find the number of ways a player can score n runs.
 * <p>
 * Imagine a game where, in each turn, a player can score either 1, 2 or 4 runs.
 * Given a score, n, find the total number of ways to score n runs.
 */
public class GameScoringIter {

    static int count = 0;

    public static void main(String[] args) {
        int n = 20;
        int result = scoringOptions(n);
        System.out.println(count);
        System.out.println(result);
    }

    // Scoring options are 1, 2, and 4
    public static int scoringOptions(int n) {
        // Initializing our solution vector
        int[] Vs = new int[n + 1];
        Arrays.fill(Vs, -1);

        // Each vector index holds the number of ways to
        // reach a score equal to the index
        Vs[0] = 1;

        // Varaibles to store scores
        int s4, s2, s1 = 0;

        // Iteratively calculate the number of ways to reach a
        // score and store it into the solutions vector
        for (int r = 1; r <= n; r++) {
            // Return 0 if index is less than 0, otherwise
            // set to array value
            s1 = r - 1 < 0 ? 0 : Vs[r - 1];
            s2 = r - 2 < 0 ? 0 : Vs[r - 2];
            s4 = r - 4 < 0 ? 0 : Vs[r - 4];

            // Using our recurrence relation to calculate new answers
            Vs[r] = s1 + s2 + s4;
        }
        return Vs[n];
    }
}