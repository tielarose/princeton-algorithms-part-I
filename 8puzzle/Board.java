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

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {

        // check that tiles isn't null
        if (tiles == null) throw new IllegalArgumentException("Tiles is null.");

        // check that the board is square
        if (tiles.length != tiles[0].length)
            throw new IllegalArgumentException("Board is not square.");

        n = tiles.length;
        currBoard = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                currBoard[i][j] = tiles[i][j];
    }

    // string representation of this board
    // public String toString() {
        
    // }

    // board dimension n
    public int dimension() {
        return n;
    }

    // // number of tiles out of place
    // public int hamming()
    //
    // // sum of Manhattan distances between tiles and goal
    // public int manhattan()
    //
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
        System.out.println(n);
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(blocks[i]));
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(blocks[i]));
        Board example = new Board(blocks);
        System.out.println(example.dimension());
    }
}
