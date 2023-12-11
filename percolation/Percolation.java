// import java.util.Arrays;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int VIRTUAL_TOP = 0;
    private boolean[][] grid;
    private final int virtualBottom;
    private final int size;
    private final WeightedQuickUnionUF qf;
    private final WeightedQuickUnionUF qf2;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        virtualBottom = n * n + 1;
        qf = new WeightedQuickUnionUF(virtualBottom + 1);
        qf2 = new WeightedQuickUnionUF(virtualBottom);
        grid = new boolean[n][n];
        openSites = 0;
    }

    // throw an error if the specified row or column is out of range
    // per specification, rows and columns range from 1 to n
    private boolean isInRange(int row, int col) {
        return !(row <= 0 || row > size || col <= 0 || col > size);
    }

    // take a row and col number and return the node number
    private int findNodeNum(int row, int col) {
        return size * (row - 1) + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
        if (grid[row - 1][col - 1]) return;

        grid[row - 1][col - 1] = true;
        ++openSites;

        int node = findNodeNum(row, col);
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // if the node is in the top row, connect it to VIRTUAL_TOP
        if (row == 1) {
            qf.union(node, VIRTUAL_TOP);
            qf2.union(node, VIRTUAL_TOP);
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

            if (!isInRange(newRow, newCol) || !isOpen(newRow, newCol)) {
                continue;
            }

            int neighborNode = findNodeNum(newRow, newCol);

            qf.union(node, neighborNode);
            qf2.union(node, neighborNode);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
        int node = findNodeNum(row, col);
        return qf.find(VIRTUAL_TOP) == qf.find(node) && qf2.find(VIRTUAL_TOP) == qf2.find(node);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.find(VIRTUAL_TOP) == qf.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        // System.out.println("*****************");
        // Percolation sampleObj = new Percolation(3);
        // **************
        // print the initial grid
        // for (boolean[] arr : sampleObj.grid) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // **************
        // check isInRange(row, col)
        // System.out.println(sampleObj.isInRange(1, 2));
        // System.out.println(sampleObj.isInRange(6, 4));
        // **************
        // check findNodeNum(row, col)
        // System.out.println(sampleObj.findNodeNum(1, 1) == 1);
        // System.out.println(sampleObj.findNodeNum(2, 3) == 6);
        // System.out.println(sampleObj.findNodeNum(3, 3) == 9);
        // **************
        // test open()
        // sampleObj.open(1, 1);
        // System.out.println("Number of open sites is: " + (sampleObj.openSites));
        // sampleObj.open(1, 1);
        // System.out.println("Number of open sites is now: " + (sampleObj.openSites));
        // sampleObj.open(2, 2);
        // sampleObj.open(1, 2);
        // sampleObj.open(3, 3);
        // for (boolean[] arr : sampleObj.grid) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // System.out.println(sampleObj.isFull(2, 2));
        // System.out.println(sampleObj.isFull(3, 3));
        // **************
        // test percolates()
        // sampleObj.open(3, 2);
        // System.out.println(sampleObj.percolates());
        // System.out.println("~~~~~~~~~~~~~~~~~");
    }
}
