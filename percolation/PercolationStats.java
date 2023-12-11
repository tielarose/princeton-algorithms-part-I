import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] thresholds;
    private int totalTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("both arguments must be greater than 0");
        }
        thresholds = new double[trials];
        totalTrials = trials;
        for (int trialNum = 0; trialNum < trials; trialNum++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                perc.open(row, col);
            }
            double percolationThreshold = (double) perc.numberOfOpenSites() / (n * n);
            thresholds[trialNum] = percolationThreshold;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(totalTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(totalTrials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats sampleObj = new PercolationStats(n, trials);
        System.out.println(String.format("mean                     = %s", sampleObj.mean()));
        System.out.println(String.format("stddev                   = %s", sampleObj.stddev()));
        System.out.println(
                String.format("95%% confidence interval = [%s, %s]", sampleObj.confidenceLo(),
                              sampleObj.confidenceHi()));
    }

}