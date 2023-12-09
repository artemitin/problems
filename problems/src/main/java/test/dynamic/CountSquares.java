package test.dynamic;
/*1277. Count squares*/

import test.MeasureUtil;

public class CountSquares {

    public static void main(String[] args) {
        CountSquares main = new CountSquares();
        int[][] matrix = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 1},
                {1, 1, 0}
        };
        Object result = MeasureUtil.runMeasured(() -> main.countSquares(matrix));
        System.out.println(result);
    }

    public int countSquares(int[][] matrix) {
        int totalSquares = 0;
        if (matrix.length > 1 && matrix[0].length > 1) {
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[i][j] != 0) {
                        matrix[i][j] += Math.min(Math.min(matrix[i - 1][j - 1], matrix[i][j - 1]), matrix[i - 1][j]);
                    }
                }
            }
        }

        for (int[] rows : matrix) {
            for (int possible : rows) {
                totalSquares += possible;
            }
        }
        return totalSquares;
    }
}
