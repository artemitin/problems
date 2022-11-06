package test.misc;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a 2D array where all elements in any individual row or column are sorted.
 * Find the position of a given key in such a matrix.
 * If the key is not present in the matrix, return (-1, -1)
 * (−1,−1)
 */
public class MatrixSearch {

    public static void main(String[] args) {
        int[][] matrix = {
                {2, 4, 7, 13, 15},
                {3, 5, 11, 18, 22},
                {6, 8, 16, 21, 28},
                {9, 11, 20, 25, 31}
        };
        Pair pair = searchInMatrix(matrix, 16);
        System.out.print(pair);
    }

    public static Pair searchInMatrix(int matrix[][], int value) {
        int M = matrix.length; // Number of rows
        int N = matrix[0].length; // Number of columns

        // Let's start searching from top right.

        // Setting the row pointer 'i' to 0
        // Setting the columns pointer 'j' to number of columns - 1
        // So, [i, j] will be the index of the top right element
        // at the start of the search
        int i = 0, j = N - 1;

        // We will search as long as we are within the matrix bounds
        while (i < M && j >= 0) {
            // Return the index if the value is found
            if (matrix[i][j] == value) {
                return new Pair(i, j);
            } else if (value < matrix[i][j]) {
                // If the current element is greater than the value
                // search left for a smaller value
                --j;
            } else {
                // Else, search down for a larger value
                ++i;
            }
        }

        // Return (-1, -1) if the value is not found
        return new Pair(-1, -1);
    }
}

