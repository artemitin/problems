package test.dynamic;

/**
 * Given a matrix containing only ones and zeros, return the count of square submatrices with all ones.
 */
public class UniquePaths {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int result = uniquePathsWithObstacles(matrix);
        System.out.println(result);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then return 0
        // as there would be no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) {
                obstacleGrid[i][0] = 1;
            } else {
                obstacleGrid[i][0] = 0;
            }
        }

        // Filling the values for the first row
        for (int j = 1; j < cols; j++) {
            if (obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1) {
                obstacleGrid[0][j] = 1;
            } else {
                obstacleGrid[0][j] = 0;
            }
        }

        // Starting from obstacleGrid[1][1], we fill up the values.
        // The number of ways of reaching obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1]
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell.
        // That is the destination.
        return obstacleGrid[rows - 1][cols - 1];
    }
}
