package test.dynamic;

public class LevenshteinDistanceOptimized {

    // Returns the least of the three values
    static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int levenshteinDistance(String str1, String str2) {
        // Get string lengths
        int m = str1.length();
        int n = str2.length();

        // Degenerate cases
        if (str1.equals(str2))
            return 0;

        // If string 1 is empty
        if (m == 0)
            return n;

        // If string 2 is empty
        if (n == 0)
            return m;

        // Create two work arrays of integer distances
        int[] d1 = new int[n + 1];
        int[] d2 = new int[n + 1];

        // Initialize d1 (the previous row of distances)
        // This row is A[0][i]: edit distance for an empty str1
        // The distance is just the number of characters to delete from str2
        for (int i = 0; i < n; i++)
            d1[i] = i;

        int cost;
        for (int i = 0; i < m; i++) {
            // Calculate d2 (current row distances) from the previous row d1

            // First element of d2 is A[i+1][0]
            // Edit distance is delete (i+1) chars from str1 to match empty str2
            d2[0] = i + 1;

            // Use the formula to fill in the rest of the row
            for (int j = 0; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j))
                    cost = 0; // no operation required
                else
                    cost = 1;
                d2[j + 1] = minimum(
                        d1[j + 1] + 1,  //deletion
                        d2[j] + 1,      //insertion
                        d1[j] + cost    //substitution
                );
            }

            // Copy d2(current row) to d1(previous row) for next iteration
            for (int j = 0; j <= n; j++)
                d1[j] = d2[j];
        }
        return d2[n];
    }

    public static void main(String[] args) {
        String[] inputs1 = {"kitten", "apples", "racecar"};
        String[] inputs2 = {"sitting", "oranges", "racecar"};
        for (int i = 0; i < inputs1.length; i++) {
            int ld = levenshteinDistance(inputs1[i], inputs2[i]);
            System.out
                    .println(i + 1 + ".  levenshteinDistance(" + inputs1[i] + ", " + inputs2[i] + ") = " + ld);
            System.out.println(
                    "------------------------------------------------------------------------------------------------------\n");
        }
    }
}