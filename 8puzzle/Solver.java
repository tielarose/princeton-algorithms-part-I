/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private MinPQ<SearchNode> pq;
    private MinPQ<SearchNode> twinPQ;

    private Board initialBoard;
    private Board twinBoard;
    private int boardSize;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private int priority;
        private SearchNode prevNode;

        public SearchNode(Board board, int moves, SearchNode prevNode) {
            this.board = board;
            this.moves = moves;
            priority = moves + board.manhattan();
            this.prevNode = prevNode;
        }

        public int compareTo(SearchNode that) {
            return (this.priority - that.priority);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Input is null.");

        initialBoard = initial;
        boardSize = initial.dimension();
        pq = new MinPQ<SearchNode>();
        twinPQ = new MinPQ<SearchNode>();
        SearchNode minNode;
        SearchNode twinMinNode;

        pq.insert(new SearchNode(initialBoard, 0, null));
        twinPQ.insert(new SearchNode(initialBoard.twin(), 0, null));
        while (!pq.min().board.isGoal() && !twinPQ.min().board.isGoal()) {
            minNode = pq.min();
            twinMinNode = twinPQ.min();
            pq.delMin();
            twinPQ.delMin();
            for (Board neighbor : minNode.board.neighbors())
                if (minNode.moves == 0)
                    pq.insert(new SearchNode(neighbor, minNode.moves + 1, minNode));
                else if (!neighbor.equals(minNode.prevNode.board))
                    pq.insert(new SearchNode(neighbor, minNode.moves + 1, minNode));

            for (Board neighbor : twinMinNode.board.neighbors())
                if (twinMinNode.moves == 0)
                    pq.insert(new SearchNode(neighbor, twinMinNode.moves + 1, twinMinNode));
                else if (!neighbor.equals(twinMinNode.prevNode.board))
                    pq.insert(new SearchNode(neighbor, twinMinNode.moves + 1, twinMinNode));
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (pq.min().board.isGoal()) return true;
        if (twinPQ.min().board.isGoal()) return false;
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return pq.min().moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> solutionStack = new Stack<Board>();
        SearchNode current = pq.min();
        while (current.prevNode != null) {
            solutionStack.push(current.board);
            current = current.prevNode;
        }
        solutionStack.push(initialBoard);
        return solutionStack;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}