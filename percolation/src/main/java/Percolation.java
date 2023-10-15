import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int n;
    private int open;
    private final WeightedQuickUnionUF algo;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n <= 0");
        }
        grid = new boolean[n][n];
        // creates n-by-n grid, with all sites initially blocked
        int length = n * n;
        this.n = n;
        algo = new WeightedQuickUnionUF(length);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int r = row - 1;
        int c = col - 1;
        if (grid[r][c]) {
            return;
        }

        int i = toIndex(r, c);
        // connect to virtual top
        if (r == 0) {
            int iOpen = getTopColOpen();
            if (iOpen < n) {
                connect(toIndex(0, iOpen), i);
            }
        }
        // connect to virtual bottom
        if (r == n - 1) {
            int iOpen = getBottomColOpen();
            if (iOpen < n) {
                connect(toIndex(n - 1, iOpen), i);
            }
        }

        // connect up
        if (r > 0 && isOpen(row - 1, col)) {
            connect(i, toIndex(r - 1, c));
        }

        // connect down
        if (r < n - 1 && isOpen(row + 1, col)) {
            connect(i, toIndex(r + 1, c));
        }

        // connect left
        if (c > 0 && isOpen(row, col - 1)) {
            connect(i, toIndex(r, c - 1));
        }

        // connect right
        if (c < n - 1 && isOpen(row, col + 1)) {
            connect(i, toIndex(r, c + 1));
        }

        grid[r][c] = true;
        open++;
    }

    private int getBottomColOpen() {
        int iOpen = 0;
        while (iOpen < n && !grid[n - 1][iOpen]) {
            iOpen++;
        }
        return iOpen;
    }

    private int getTopColOpen() {
        int iOpen = 0;
        while (iOpen < n && !grid[0][iOpen]) {
            iOpen++;
        }
        return iOpen;
    }

    private void connect(int i1, int i2) {
        algo.union(i1, i2);
    }

    private int toIndex(int r, int c) {
        return r * n + c;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int topColOpen = getTopColOpen();
        int index = toIndex(row - 1, col - 1);
        return topColOpen < n && algo.find(topColOpen) == algo.find(index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return open;
    }

    // does the system percolate?
    public boolean percolates() {
        int topColOpen = getTopColOpen();
        int bottomColOpen = getBottomColOpen();
        if (topColOpen < n && bottomColOpen < n) {
            return algo.find(topColOpen) == algo.find(toIndex(n - 1, bottomColOpen));
        }
        return false;
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row > n || col > n) {
            throw new IllegalArgumentException(String.format("Row %s or col %s is out of range, given N=%s", row, col, n));
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
