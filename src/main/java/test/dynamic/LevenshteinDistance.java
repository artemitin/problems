package test.dynamic;

public class LevenshteinDistance {

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

        // For all i and j, d[i,j] will hold the Levenshtein distance between
        // the first i characters of str1 and the first j characters of str2;
        // note that d has (m+1)*(n+1) values
        int[][] d = new int[m + 1][n + 1];

        // Source prefixes can be transformed into an empty string by
        // dropping all characters
        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }

        // Target prefixes can be reached from empty source prefix
        // by inserting every character
        for (int j = 1; j <= n; j++) {
            d[0][j] = j;
        }

        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    cost = 0; // no operation required
                else
                    cost = 1;

                d[i][j] = minimum(
                        d[i - 1][j] + 1,        //deletion
                        d[i][j - 1] + 1,        //insertion
                        d[i - 1][j - 1] + cost  //substitution
                );
            }
        }
        return d[m][n];
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