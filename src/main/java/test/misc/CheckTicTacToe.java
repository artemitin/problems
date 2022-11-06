package test.misc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Check if the given tic-tac-toe board is valid.
 */
public class CheckTicTacToe {

    public static boolean checkWinPositions(String[] board, Character player) {
        // Check if the given player has a win position.
        // Return true if there is a win position. Else return false.
        // Check the rows
        for (String s : board) {
            if (s.charAt(0) == player && s.charAt(1) == player && s.charAt(2) == player)
                return true;
        }

        // Check the columns
        for (int i = 0; i < board.length; i++) {
            if (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player)
                return true;
        }

        // Check the diagonals
        if ((board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player)
                || (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player))
            return true;

        return false;
    }

    public static boolean validTicTacToe(String[] board) {
        // Intializing xCount and oCount
        int xCount = 0;
        int oCount = 0;

        // Iterating over the board (e.g ["XOX","O O","XOX"]) to count the
        // number of "X" and "O" on the board.
        for (String s : board) {
            // Iterating over each row: e.g "XOX"
            for (int j = 0; j < board.length; j++) {
                // Updating count of "X"
                if (s.charAt(j) == 'X')
                    xCount += 1;

                    // Updating count of "O"
                else if (s.charAt(j) == 'O')
                    oCount += 1;
            }
        }

        // Since X starts first, xCount > oCount. Players have taken their turns
        // so, xCount - oCount must not be greater than 1.
        if (oCount > xCount || xCount - oCount > 1)
            return false;

        // Checking if player O is winning.
        if (checkWinPositions(board, 'O')) {
            // Return false if player X is also in winning position
            if (checkWinPositions(board, 'X'))
                return false;

            // Return true if oCount is equal to xCount, else return false.
            return oCount == xCount;
        }

        // Checking if player X is winning and xCount != oCount + 1
        // Since player X plays the first move if player X wins, the player X's
        // count would be 1 more than player O
        return !checkWinPositions(board, 'X') || xCount == oCount + 1;
    }

    public static void printTicTacToe(String[] values) {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println("\t     |     |");
            System.out.println("\t  " + values[i].charAt(0) + "  |  " + values[i].charAt(1)
                    + "  |  " + values[i].charAt(2));
            if (i < 2)
                System.out.println("\t_____|_____|_____");
            else
                System.out.println("\t     |     |");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[][] inputBoard = {{"O  ", "   ", "   "}, {"XOX", " X ", "   "},
                {"XXX", "   ", "OOO"}, {"XOX", "O O", "XOX"}};
        int index = 0;
        for (String[] board : inputBoard) {
            System.out.print("Board #" + (++index));
            System.out.println(": Tic Tac Toe board with values [\"" + board[0] + "\", \""
                    + board[1] + "\", \"" + board[2] + "\"]\n");
            printTicTacToe(board);
            if (validTicTacToe(board))
                System.out.println("\nResult: A valid tic tac toe board.");
            else
                System.out.println("\nResult: Not a valid tic tac toe board");
            System.out.println(String.join("", Collections.nCopies(100, "-")) + "\n");
        }
    }
}
