import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;

public class PercolationStats {
    
    private int siteSize;
    private int siteSquare;
    private int noOpenSites;
    private int trialCount;
    private double[] trialResults;

    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Invalid arguments");
        siteSize = n;
        siteSquare = n * n;
        noOpenSites = 0;
        trialCount = trials;
        trialResults = new double[trialCount];

        for (int t = 0; t < trialCount; t++){
            Percolation p = new Percolation(siteSize);
            while (!p.percolates()){
                int row = StdRandom.uniform(1, siteSize + 1);
                int col = StdRandom.uniform(1, siteSize + 1);
                p.open(row, col);
            }
            noOpenSites = p.numberOfOpenSites();
            trialResults[t] = (double) noOpenSites / siteSquare;
        }
    }

    public double mean(){
        return StdStats.mean(trialResults);
    }

    public double stddev(){
        return StdStats.stddev(trialResults);
    }

    public double confidenceLo(){
        return mean() - (1.96 * stddev() / Math.sqrt(trialCount));
    }

    public double confidenceHi(){
        return mean() + (1.96 * stddev() / Math.sqrt(trialCount));
    }

    public static void main(String[] args) {
        int siteSize = 10;
        int trialCount = 10;
        if (args.length >= 2){
            siteSize = Integer.parseInt(args[0]);
            trialCount = Integer.parseInt(args[1]);
        }
        PercolationStats ps = new PercolationStats(siteSize, trialCount);
        double[] confidence = {ps.confidenceHi(), ps.confidenceLo()};
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + confidence[0] + " " + confidence[1] + "]");
    }
}
