package test.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class NQueens {
    // This solution uses a stack to store the solution.
    // Stack will hold only the column values and one solution
    // will be stored in the stack at a time.
    static boolean isValidMove(int proposedRow, int proposedCol, List<Integer> solution) {
        // we need to check with all queens in the current solution
        for (int i = 0; i < proposedRow; ++i) {
            int oldRow = i;
            int oldCol = solution.get(i);

            int diagonalOffset = proposedRow - oldRow;
            if (oldCol == proposedCol || oldCol == proposedCol - diagonalOffset
                    || oldCol == proposedCol + diagonalOffset) {
                return false;
            }
        }
        return true;
    }

    // This solution uses stack to store the solution.
    // Stack will hold only the column values and one solution
    // will be stored in the stack at a time.
    static int solveNQueens(int n) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> solution = new ArrayList<>(Collections.nCopies(n, -1));
        Stack<Integer> solStack = new Stack<>();

        int row = 0;
        int col = 0;

        while (row < n) {
            // For the current state of the solution, check if a queen can be placed in any
            // column of this row
            while (col < n) {
                if (isValidMove(row, col, solution)) {
                    // If this is a safe position for a queen (a valid move), save
                    // it to the current solution on the stack...
                    solStack.push(col);
                    solution.set(row, col);
                    row++;
                    col = 0;
                    // ... and move on to checking the next row (breaking out of the inner loop)
                    break;
                }
                col++;
            }

            // If we have checked all the columns
            if (col == n) {
                // If we are working on a solution
                if (!solStack.empty()) {
                    // Backtracking, as current row does not offer a safe spot given the previous
                    // move
                    // So, get set up to check the previous row with the next column
                    col = solStack.peek() + 1;
                    solStack.pop();
                    row--;
                } else {
                    // If we have backtracked all the way and found this to be a dead-end,
                    // break out of the inner loop as no more solutions exist
                    break;
                }
            }

            // If we have found a safe spot for a queen in each of the rows
            if (row == n) {
                // Add the solution into results
                results.add(new ArrayList<>(solution));

                // Backtrack to find the next solution
                row--;
                col = solStack.peek() + 1;
                solStack.pop();
            }
        }
        return results.size();
    }

    public static void main(String[] args) {
        int[] n = {4, 5, 6, 7, 8};

        for (int i = 0; i < n.length; i++) {
            int res = solveNQueens(n[i]);
            System.out.println((i + 1) + ".  Total solutions count for " + n[i] + " queens on the chessboard ("
                    + n[i] + "x" + n[i] + "): " + res);
            System.out.println(
                    "------------------------------------------------------------------------------------------------\n");
        }
    }
}
