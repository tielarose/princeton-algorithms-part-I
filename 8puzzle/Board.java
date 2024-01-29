import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class Board {
    private int n;
    private int[] board;
    private int blankInd;
    // private int[] blankPos = new int[2];
    // private int[][] currBoard;

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
        board = new int[n * n];
        int ind = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                board[ind] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    blankInd = ind;
                }
                ind++;

            }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        int col = 0;
        for (int i = 0; i < board.length; i++) {
            if (col < n - 1) {
                s.append(board[i] + " ");
                col++;
            }
            else if (col == n - 1) {
                s.append(board[i] + "\n");
                col = 0;
            }
        }
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++)
        //         if (j < n - 1)
        //             s.append(currBoard[i][j] + " ");
        //         else if (i < n - 1)
        //             s.append(currBoard[i][j] + "\n");
        //         else s.append(currBoard[i][j]);
        // }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                continue;
            }
            if (board[i] != i + 1) hamming++;
        }
        // for (int i = 0; i < n; i++)
        //     for (int j = 0; j < n; j++) {
        //         if (currBoard[i][j] == 0) {
        //             ans++;
        //             continue;
        //         }
        //         if (currBoard[i][j] != ans) hamming++;
        //         ans++;
        //     }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < board.length; i++) {
            // if the tile is the empty space, continue
            if (board[i] == 0) continue;

            // if the tile is in the correct place, continue
            if (board[i] == i + 1) continue;

            // otherwise, compare the location of the current tile to
            // the correct location, add to manhattan value
            int rowDiff = Math.abs(row(board[i]) - row(i + 1));
            int colDiff = Math.abs(col(board[i]) - col(i + 1));
            manhattan += (rowDiff + colDiff);

        }

        // for (int i = 0; i < n; i++)
        //     for (int j = 0; j < n; j++) {
        //         // if the tile is the empty space, continue
        //         if (currBoard[i][j] == 0) continue;
        //
        //         // if the tile is in the correct place, continue;
        //         int corrTile = (i * n) + (j + 1);
        //         if (currBoard[i][j] == corrTile) continue;
        //
        //         // otherwise, compare the location of the current tile to
        //         // the correct location, add to manhattan value
        //         int rowDiff = Math.abs(row(currBoard[i][j]) - (i + 1));
        //         int colDiff = Math.abs(col(currBoard[i][j]) - (j + 1));
        //         manhattan += (rowDiff + colDiff);
        //     }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board boardY = (Board) y;
        if (!Arrays.equals(this.board, boardY.board)) return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> stackNeighbors = new Stack<Board>();
        int row = row(blankInd + 1);
        int col = col(blankInd + 1);
        // int row = blankPos[0];
        // int col = blankPos[1];

        // can we switch up?
        if (row != 1) {
            int[] neighbor = board.clone();
            int temp = board[blankInd - n];
            neighbor[blankInd - n] = 0;
            neighbor[blankInd] = temp;
            Board neighborBoard = new Board(to2D(neighbor));
            // int[][] neighbor = copy(currBoard);
            // int temp = currBoard[row - 1][col];
            // neighbor[row - 1][col] = 0;
            // neighbor[row][col] = temp;
            // Board neighborBoard = new Board(neighbor);
            stackNeighbors.push(neighborBoard);
        }

        // can we switch down?
        if (row != n) {
            int[] neighbor = board.clone();
            int temp = board[blankInd + n];
            neighbor[blankInd + n] = 0;
            neighbor[blankInd] = temp;
            Board neighborBoard = new Board(to2D(neighbor));
            // int[][] neighbor = copy(currBoard);
            // int temp = currBoard[row + 1][col];
            // neighbor[row + 1][col] = 0;
            // neighbor[row][col] = temp;
            // Board neighborBoard = new Board(neighbor);
            stackNeighbors.push(neighborBoard);
        }

        // can we switch right?
        if (col != n) {
            int[] neighbor = board.clone();
            int temp = board[blankInd + 1];
            neighbor[blankInd + 1] = 0;
            neighbor[blankInd] = temp;
            Board neighborBoard = new Board(to2D(neighbor));
            // int[][] neighbor = copy(currBoard);
            // int temp = currBoard[row][col + 1];
            // neighbor[row][col + 1] = 0;
            // neighbor[row][col] = temp;
            // Board neighborBoard = new Board(neighbor);
            stackNeighbors.push(neighborBoard);
        }

        // can we switch left?
        if (col != 1) {
            int[] neighbor = board.clone();
            int temp = board[blankInd - 1];
            neighbor[blankInd - 1] = 0;
            neighbor[blankInd] = temp;
            Board neighborBoard = new Board(to2D(neighbor));
            // int[][] neighbor = copy(currBoard);
            // int temp = currBoard[row][col - 1];
            // neighbor[row][col - 1] = 0;
            // neighbor[row][col] = temp;
            // Board neighborBoard = new Board(neighbor);
            stackNeighbors.push(neighborBoard);
        }

        return stackNeighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    // the FAQ says two tiles "in the same row," and that only one twin is needed
    public Board twin() {
        int[] twin = board.clone();
        int swapInd = 0;
        if (swapInd == blankInd) swapInd++;
        // int[][] twin = copy(currBoard);
        // swap the two
        // int col = 0;
        // int row = 0;
        //
        // while (row == blankPos[0]) {
        //     row++;
        // }
        //
        // while (col == blankPos[1]) {
        //     col++;
        // }

        // try swapping left
        if (swapInd != 0 && twin[swapInd - 1] != 0) {
            int temp = twin[swapInd - 1];
            twin[swapInd - 1] = twin[swapInd];
            twin[swapInd] = temp;
        }

        // if (col - 1 >= 0 && twin[row][col - 1] != 0) {
        //     int temp = twin[row][col];
        //     twin[row][col] = twin[row][col - 1];
        //     twin[row][col - 1] = temp;
        // }

        // otherwise try swapping right
        else if (swapInd + 1 < twin.length && twin[swapInd + 1] != 0) {
            int temp = twin[swapInd + 1];
            twin[swapInd + 1] = twin[swapInd];
            twin[swapInd] = temp;
        }
        // else if (col + 1 < n && twin[row][col + 1] != 0) {
        //     int temp = twin[row][col];
        //     twin[row][col] = twin[row][col + 1];
        //     twin[row][col + 1] = temp;
        // }

        // otherwise try swapping up
        else if (swapInd - n >= 0 && twin[swapInd - n] != 0) {
            int temp = twin[swapInd - n];
            twin[swapInd - n] = twin[swapInd];
            twin[swapInd] = temp;
        }
        // else if (row - 1 >= 0 && twin[row - 1][col] != 0) {
        //     int temp = twin[row][col];
        //     twin[row][col] = twin[row - 1][col];
        //     twin[row - 1][col] = temp;
        // }

        // otherwise try swapping down
        else if (swapInd + n < twin.length && twin[swapInd + n] != 0) {
            int temp = twin[swapInd + n];
            twin[swapInd + n] = twin[swapInd];
            twin[swapInd] = temp;
        }
        // else if (row + 1 < n && twin[row + 1][col] != 0) {
        //     int temp = twin[row][col];
        //     twin[row][col] = twin[row + 1][col];
        //     twin[row + 1][col] = temp;
        // }

        Board twinBoard = new Board(to2D(twin));
        return twinBoard;
    }

    // *********************
    // helper functions
    // *********************

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

    private int[][] to2D(int[] oneDBoard) {
        int[][] twoDboard = new int[n][n];
        int col = 0;
        int row = 0;
        for (int i = 0; i < oneDBoard.length; i++) {
            twoDboard[row][col] = oneDBoard[i];
            if (col == n - 1) {
                col = 0;
                row++;
            }
            else col++;
        }
        return twoDboard;
    }

    // private int[][] copy(int[][] refBoard) {
    //     int size = refBoard[0].length;
    //     int[][] copied = new int[size][size];
    //     for (int i = 0; i < n; i++)
    //         for (int j = 0; j < n; j++)
    //             copied[i][j] = refBoard[i][j];
    //     return copied;
    // }

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
        Board twin = example.twin();
        System.out.println(twin.toString());
        // Board twin2 = example.twin();
        // System.out.println(twin2.toString());
        // System.out.println(example.dimension());
        // System.out.println(example.toString());
        // System.out.println("hamming is " + example.hamming());
        // System.out.println(example.manhattan());
        // Board example2 = new Board(blocks);
        // example2.currBoard[0][0] = 9;
        // System.out.println(example.equals(example2));
        //
        // Iterable<Board> neighbors = example.neighbors();
        // for (Board neighbor : neighbors) {
        //     System.out.println("******************");
        //     System.out.println(neighbor.toString());
        //     // System.out.println(neighbor.equals(example));
        //     // System.out.println(example.equals(neighbor));
        //     System.out.println("                  ");
        // }
    }
}
