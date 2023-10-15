package test.misc;

/**
 * Find the perimeter of an island.
 * Given a grid representing a map where: :
 *
 * grid[i][j] = 1 represents land
 * grid[i][j] = 0 represents water
 * Each cell is a square with length = 1 and the grid is rectangular.
 *
 * Find the perimeter of the island.
 */
public class FindPerimeterIsland {

    public static void main(String[] args) {
        Integer[][] matrix = {
                {0,1,0,0,0},
                {1,1,1,1,0},
                {0,0,1,0,0},
                {1,1,1,1,1},
                {0,0,1,0,1}
        };
        int result = islandPerimeter(matrix);
        System.out.print(result);
    }

    static int islandPerimeter(Integer[][] grid) {
        int result = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                // Checking if current cell is a land cell
                if (grid[r][c] == 1) {
                    result += 4;

                    //check left boundary
                    if (r > 0 && grid[r - 1][c] == 1) {
                        result -= 2;
                    }
                    //check upper boundary
                    if (c > 0 && grid[r][c - 1] == 1) {
                        result -= 2;
                    }
                }
            }
        }
        return result;
    }
}

