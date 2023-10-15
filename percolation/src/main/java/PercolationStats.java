import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] p;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException(String.format("N %s or trials %s is out of range", n, trials));
        }
        this.p = new double[trials];
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(p);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(p);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - stddev();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + stddev();
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        for (int i = 0; i < trials; i++) {
            stats.doTrial(i, n);
        }
        System.out.printf("mean = %s\n", stats.mean());
        System.out.printf("stddev = %s\n", stats.stddev());
        System.out.printf("95%% confidence interval = [%s, %s]", stats.confidenceLo(), stats.confidenceHi());
    }

    private void doTrial(int i, int n) {
        Percolation perc = new Percolation(n);
        while (!perc.percolates()) {
            perc.open(StdRandom.uniformInt(n) + 1, StdRandom.uniformInt(n) + 1);
        }
        p[i] = (double) perc.numberOfOpenSites() / (n * n);
    }
}
