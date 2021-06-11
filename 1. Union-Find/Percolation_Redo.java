import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation_Redo {
    
    private boolean[][] siteMatrix;
    private int openSites;
    private int matrixSize;
    private int matrixSizeSquare;
    private int virtualTop;
    private int virtualBottom;
    private WeightedQuickUnionUF setTop;
    private WeightedQuickUnionUF setTopAndBottom;

    public Percolation_Redo(int n){
        if (n <= 0) throw new IllegalArgumentException("n must be > 0");
        matrixSize = n;
        matrixSizeSquare = n * n;
        
        virtualTop = matrixSizeSquare;
        virtualBottom = matrixSizeSquare + 1;

        setTop = new WeightedQuickUnionUF(matrixSizeSquare + 1);
        setTopAndBottom = new WeightedQuickUnionUF(matrixSizeSquare + 2);

        openSites = 0;
        siteMatrix = new boolean[matrixSize][matrixSize];
    }

    public void open(int row, int col){
        validate(row, col);
        if (!isOpen(row, col)) return;
        
        siteMatrix[row - 1][col - 1] = true;
        openSites++;
        int index = flatIndex(row, col);
        
        if (row == 1){
            setTop.union(virtualTop, index);
            setTopAndBottom.union(virtualTop, index);
        }

        if (row == matrixSize){
            setTopAndBottom.union(virtualBottom, index);
        }

        // check up
        if (isOpen(row - 1, col)){
            setTop.union(index, flatIndex(row - 1, col));
            setTopAndBottom.union(index, flatIndex(row - 1, col));
        }
        // check left
        if (isOpen(row, col - 1)){
            setTop.union(index, flatIndex(row, col - 1));
            setTopAndBottom.union(index, flatIndex(row, col - 1));
        }
        // check right
        if (isOpen(row, col + 1)){
            setTop.union(index, flatIndex(row, col + 1));
            setTopAndBottom.union(index, flatIndex(row, col + 1));
        }
        // check down
        if (isOpen(row + 1, col)){
            setTop.union(index, flatIndex(row + 1, col));
            setTopAndBottom.union(index, flatIndex(row + 1, col));
        }
    }

    public boolean isOpen(int row, int col)
    {
        validate(row, col);
        return siteMatrix[row - 1][col - 1];
    }

    public boolean isFull(int row, int col){
        return true;
    }

    public int numberOfOpenSites(){
        return openSites;
    }

    public boolean percolates(){return true;}
    public static void main(String[] args) {
        
    }

    private boolean insideMatrix(int row, int col){
        return row >= 1 && col >= 1 && row <= matrixSize - 1 && col <= matrixSize - 1;
    }

    private void validate(int row, int col){
        if (!insideMatrix(row, col)) throw new IndexOutOfBoundsException("Index is out of bounds");
    }

    private int flatIndex(int row, int col){
        return matrixSize * (row - 1) + col - 1;
    }
}
