import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] sites;
    private int siteSize;
    private int siteSquare;
    private WeightedQuickUnionUF wqfTopBottom;
    private WeightedQuickUnionUF wqfTop;
    private int virtualBottom;
    private int virtualTop;
    private int noOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");
        siteSize = n;
        siteSquare = n * n;
        noOpenSites = 0;

        // initialize all blocked sites
        sites = new boolean[siteSize][siteSize];

        wqfTopBottom = new WeightedQuickUnionUF(siteSquare + 2);
        wqfTop = new WeightedQuickUnionUF(siteSquare + 1);
        
        // declare virtualTop and virtualBottom
        virtualTop = siteSquare;
        virtualBottom = siteSquare + 1;
    }

    private void validate(int row, int col) {
        if (!insideSite(row, col)){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    private boolean insideSite(int row, int col){
        return row >= 1 && col >= 1 && row <= siteSize && col <= siteSize;
    }

    private int flatIndex(int row, int col){
        return siteSize * (row - 1) + col - 1;
    }

    public boolean isOpen(int row, int col){
        validate(row, col);
        return sites[row - 1][col - 1];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validate(row, col);
        int index = flatIndex(row, col);

        // the site is not opened
        if (isOpen(row, col)) return;

        sites[row - 1][col - 1] = true;
        noOpenSites++;

        if (row == 1){
            wqfTop.union(virtualTop, index);
            wqfTopBottom.union(virtualTop, index);
        }

        if (row == siteSize){
            wqfTopBottom.union(virtualBottom, index);
        }
        // check right
        if (insideSite(row, col + 1) && isOpen(row, col + 1)){
            wqfTop.union(index, flatIndex(row, col + 1));
            wqfTopBottom.union(index, flatIndex(row, col + 1));
        }

        // check left
        if (insideSite(row, col - 1) && isOpen(row, col - 1)){
            wqfTop.union(index, flatIndex(row, col - 1));
            wqfTopBottom.union(index, flatIndex(row, col - 1));
        }

        // check up
        if (insideSite(row - 1, col) && isOpen(row - 1, col)){
            wqfTop.union(index, flatIndex(row - 1, col));
            wqfTopBottom.union(index, flatIndex(row - 1, col));
        }

        // check down
        if (insideSite(row + 1, col) && isOpen(row + 1, col)){
            wqfTop.union(index, flatIndex(row + 1, col));
            wqfTopBottom.union(index, flatIndex(row + 1, col));
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row, col);
        return wqfTop.find(virtualTop) == wqfTop.find(flatIndex(row, col));
    }

    public int numberOfOpenSites(){
        return noOpenSites;
    }

    public boolean percolates(){
        return wqfTopBottom.find(virtualTop) == wqfTopBottom.find(virtualBottom);
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);

        Percolation p = new Percolation(size);
        int noRowCol = args.length - 1;
        int pairIndex = 1;
        while (noRowCol > 0) {
            int row = Integer.parseInt(args[pairIndex++]);
            int col = Integer.parseInt(args[pairIndex++]);
            System.out.println("Adding row: " + row + " col: " + col);
            p.open(row, col);
            if (p.percolates()){
                System.out.println("The System is percolated");
                break;
            }
            noRowCol -= 2;
        }
        if (!p.percolates()) {
            System.out.println("Does not percolate");
        }
    }
}