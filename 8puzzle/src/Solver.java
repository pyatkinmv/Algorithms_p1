import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.LinkedList;

public class Solver {

    private LinkedList<Board> solution;
    private boolean isSolvable;
    private int moves;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Initial board is a null");

        Comparator<SearchNode> comparator = Comparator.comparingInt(SearchNode::manhattan);

        MinPQ<SearchNode> priorityQueue = new MinPQ<>(comparator);
        MinPQ<SearchNode> twinPriorityQueue = new MinPQ<>(comparator);

        priorityQueue.insert(new SearchNode(initial, null));
        twinPriorityQueue.insert(new SearchNode(initial.twin(), null));

        solve(priorityQueue, twinPriorityQueue);
    }

    private void solve(MinPQ<SearchNode> priorityQueue, MinPQ<SearchNode> twinPriorityQueue) {

        while (!priorityQueue.min().getBoard().isGoal() && !twinPriorityQueue.min().getBoard().isGoal()) {
            SearchNode node = priorityQueue.delMin();
            Iterable<Board> neighbors = node.getBoard().neighbors();

            for (Board neighbor : neighbors) {
                if (node.getPredecessor() == null || !neighbor.equals(node.getPredecessor().getBoard())) {
                    priorityQueue.insert(new SearchNode(neighbor, node));
                }
            }

            SearchNode twin = twinPriorityQueue.delMin();
            Iterable<Board> twinBoards = twin.getBoard().neighbors();

            for (Board twinBoard : twinBoards) {
                if (twin.getPredecessor() == null || !twinBoard.equals(twin.getPredecessor().getBoard())) {
                    twinPriorityQueue.insert(new SearchNode(twinBoard, twin));
                }
            }
        }

        if (priorityQueue.min().getBoard().isGoal()) {
            isSolvable = true;
            solution = new LinkedList<>();
            SearchNode current = priorityQueue.delMin();
            moves = current.getNumOfMoves();

            while (current != null) {
                solution.push(current.getBoard());
                current = current.predecessor;
            }
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return isSolvable ? moves : -1;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private class SearchNode {
        private final Board board;
        private final SearchNode predecessor;
        private final int manhattan;
        private final int numOfMoves;
        private final int hamming;

        SearchNode(Board board, SearchNode predecessor) {
            this.board = board;
            this.predecessor = predecessor;
            this.numOfMoves = predecessor == null ? 0 : predecessor.getNumOfMoves() + 1;
            this.manhattan = board.manhattan() + numOfMoves;
            this.hamming = board.hamming() + numOfMoves;
        }

        Board getBoard() {
            return board;
        }

        public SearchNode getPredecessor() {
            return predecessor;
        }

        int getNumOfMoves() {
            return numOfMoves;
        }

        int hamming() {
            return hamming;
        }

        int manhattan() {
            return manhattan;
        }

    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}