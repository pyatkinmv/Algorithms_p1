import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private static final double C = 1.96;
    private double mean = -1;
    private double stddev = -1;
    private final double[] x;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
        x = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            x[i] = (double) p.numberOfOpenSites()/(n*n);
        }
    } // perform trials independent experiments on an n-by-n grid
    public double mean() {
        if (mean == -1.) mean = StdStats.mean(x);
        return mean;
    }                      // sample mean of percolation threshold
    public double stddev() {
        if (stddev == -1.) stddev = StdStats.stddev(x);
        return stddev;
    }                      // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean() - (C*stddev())/Math.sqrt(x.length);
    }                  // low  endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (C*stddev())/Math.sqrt(x.length);
    } // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        Stopwatch stopwatch;
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        stopwatch = new Stopwatch();
        PercolationStats ps = new PercolationStats(n, trials);
        double time = stopwatch.elapsedTime();
        System.out.println("mean = " + ps.mean() +"\n" + "stddev = "
                + ps.stddev()
                + " \n 95% confidence interval = ["
                +ps.confidenceLo()
                + "," + ps.confidenceHi() + "]");
        System.out.println(time);
    } // test client (described below)
}