/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class Board {
    private int n;
    private int[][] currBoard;

    // col of a tile in the correct place
    private int col(int tile) {
        if (tile % n == 0)
            return n;
        return tile % n;
    }

    // row of a tile in the correct place
    private int row(int tile) {
        return (int) Math.ceil((double) tile / (double) n);
    }


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {

        // check that tiles isn't null
        if (tiles == null) throw new IllegalArgumentException("Tiles is null.");

        // check that the board is square
        if (tiles.length != tiles[0].length)
            throw new IllegalArgumentException("Board is not square.");

        // set board dimension
        n = tiles.length;

        // create the current board from the input tiles
        currBoard = new int[n][n];
        int endVal = 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                currBoard[i][j] = tiles[i][j];
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (j < n - 1)
                    s.append(currBoard[i][j] + " ");
                else if (i < n - 1)
                    s.append(currBoard[i][j] + "\n");
                else s.append(currBoard[i][j]);
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        int ans = 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (currBoard[i][j] == 0) continue;
                if (currBoard[i][j] != ans) hamming++;
                ans++;
            }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                // if the tile is the empty space, continue
                if (currBoard[i][j] == 0) continue;

                // if the tile is in the correct place, continue;
                int corrTile = (i * n) + (j + 1);
                if (currBoard[i][j] == corrTile) continue;

                // otherwise, compare the location of the current tile to
                // the correct location, add to manhattan value
                int rowDiff = Math.abs(row(currBoard[i][j]) - (i + 1));
                int colDiff = Math.abs(col(currBoard[i][j]) - (j + 1));
                manhattan += (rowDiff + colDiff);
            }

        return manhattan;
    }

    // // is this board the goal board?
    // public boolean isGoal()
    //
    // // does this board equal y?
    // public boolean equals(Object y)
    //
    // // all neighboring boards
    // public Iterable<Board> neighbors()
    //
    // // a board that is obtained by exchanging any pair of tiles
    // public Board twin()

    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        // System.out.println(n);
        int[][] blocks = new int[n][n];
        // for (int i = 0; i < n; i++)
        //     System.out.println(Arrays.toString(blocks[i]));
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(blocks[i]));
        Board example = new Board(blocks);
        // System.out.println(example.dimension());
        // System.out.println(example.toString());
        // System.out.println(example.hamming());
        System.out.println(example.manhattan());
    }
}
