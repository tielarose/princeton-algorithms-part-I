/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private SearchNode lastNode;
    private boolean solvable;
    private int minMoves = 0;

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

        public Board getBoard() {
            Board temp = board;
            return temp;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrevNode() {
            return prevNode;
        }

        public int getPriority() {
            return priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Input is null.");

        int moves = 0;
        int twinMoves = 0;

        Queue<Board> neighbors = new Queue<Board>();
        Queue<Board> twinNeighbors = new Queue<Board>();

        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>();

        pq.insert(new SearchNode(initial, 0, null));
        twinPQ.insert(new SearchNode(initial.twin(), 0, null));

        boolean solved = false;
        boolean twinSolved = false;
        SearchNode currNode;


        while (!solved && !twinSolved) {
            currNode = pq.delMin();
            SearchNode predecessor = currNode.getPrevNode();
            Board temp = currNode.getBoard();
            solved = temp.isGoal();

            SearchNode currTwin = twinPQ.delMin();
            SearchNode twinPred = currTwin.getPrevNode();
            Board twinTemp = currTwin.getBoard();
            twinSolved = twinTemp.isGoal();

            for (Board tempBoard : temp.neighbors())
                neighbors.enqueue(tempBoard);

            for (Board tempBoard : twinTemp.neighbors())
                twinNeighbors.enqueue(tempBoard);

            while (neighbors.size() > 0) {
                Board board = neighbors.dequeue();
                int move = currNode.getMoves();
                move++;
                if (predecessor != null && predecessor.getBoard().equals(board))
                    continue;

                SearchNode neighborNode = new SearchNode(board, move, currNode);
                // System.out.println("Priorities " + neighborNode.getPriority());
                pq.insert(neighborNode);
            }

            while (twinNeighbors.size() > 0) {
                Board board = twinNeighbors.dequeue();
                int twinMove = currNode.getMoves();
                twinMove++;
                if (twinPred != null && twinPred.getBoard().equals(board))
                    continue;

                SearchNode neighborNode = new SearchNode(board, twinMove, currTwin);
                twinPQ.insert(neighborNode);
            }

            moves = currNode.getMoves() + 1;
            twinMoves = currTwin.getMoves() + 1;
            lastNode = currNode;

        }

        solvable = !twinSolved;
        minMoves = moves - 1;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable())
            return -1;
        return minMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> solutionStack = new Stack<Board>();
        SearchNode lastNode = this.lastNode;
        if (this.isSolvable()) {
            while (lastNode.getPrevNode() != null) {
                solutionStack.push(lastNode.getBoard());
                lastNode = lastNode.getPrevNode();
            }
            solutionStack.push(lastNode.getBoard());
            return solutionStack;
        }
        return null;
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