package test.misc;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a two-dimensional array, if any element within the array is zero, make its whole row and column zero.
 */
public class ColumnsRowsZeros {

    public static void main(String[] args) {
        int[][] matrix = {
                {5, 4, 3, 9},
                {2, 0, 7, 6},
                {1, 3, 4, 0},
                {9, 8, 3, 4}
        };
        makeZeros(matrix);

        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void makeZeros(int[][] matrix) {
        // Degenerate case
        if (matrix.length == 0) {
            return;
        }

        // Creating the sets for zero rows and columns
        Set<Integer> zeroRows = new HashSet<Integer>();
        Set<Integer> zeroCols = new HashSet<Integer>();

        // Setting the number of rows and columns
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Nested for loop to iterate over each element
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // If a zero is found, insert the indices to the sets
                if (matrix[i][j] == 0) {
                    // A set can only have unqiue values, so we can
                    // insert the indices wihtout worrying about duplicates
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        // Setting the rows to zero with the indices from zeroRows
        for (int r : zeroRows) {
            for (int c = 0; c < cols; ++c) {
                matrix[r][c] = 0;
            }
        }

        // Setting the columns to zero with the indices from zeroCols
        for (int c : zeroCols) {
            for (int r = 0; r < rows; ++r) {
                matrix[r][c] = 0;
            }
        }
    }
}
