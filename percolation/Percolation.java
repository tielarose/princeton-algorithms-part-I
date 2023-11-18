import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final int virtualTop = 0;
    private final int virtualBottom;
    private final int size;
    private final WeightedQuickUnionUF qf;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        virtualBottom = n * n + 1;
        qf = new WeightedQuickUnionUF(virtualBottom + 1);
        grid = new boolean[n][n];
        openSites = 0;
    }

    // throw an error if the specified row or column is out of range
    private boolean isInRange(int row, int col) {
        return !(row < 0 || row > size - 1 || col < 0 || col > size - 1);
    }

    // take a row and col number and return the node number
    private int findNodeNum(int row, int col) {
        return size * (row - 1) + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isInRange(row, col)){
            throw new IllegalArgumentException();
        }
        grid[row - 1][col - 1] = true;
        ++openSites;

        int node = findNodeNum(row, col);
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // if the node is in the top row, connect it to virutalTop
        if (row == 1) {
            qf.union(node, virtualTop);
        }

        // if the node is in the bottom row, connect it to virtualBottom
        if (row == size) {
            qf.union(node, virtualBottom);
        }

        // loop through the nodes above, below, left and right of the node
        // connect them if those nodes are open (and in range)
        for (int[] direction : directions) {
            int newCol = direction[0] + col;
            int newRow = direction[1] + row;

            if (!isInRange(newRow, newCol) || !isOpen(newRow, newCol)){
                continue;
            }

            int neighborNode = findNodeNum(newRow, newCol);
            
            qf.union(node, neighborNode);
        }
            



    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // test client (optional)
    public static void main(String[] args){}
}
