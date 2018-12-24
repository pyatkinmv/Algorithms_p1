import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class Solver {
    private MinPQ<SearchNode> priorityQueue;
    private MinPQ<SearchNode> twinPriorityQueue;
    private LinkedList<Board> solution;
    private boolean isSolvable;
    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Initial board is null");

        isSolvable = false;
        moves = -1;

        priorityQueue = new MinPQ<>();
        twinPriorityQueue = new MinPQ<>();

        SearchNode predecessor = null;
        SearchNode searchNode = new SearchNode(initial, predecessor, 0);

        SearchNode twinPredecessor = null;
        SearchNode twinSearchNode = new SearchNode(initial.twin(), twinPredecessor, 0);

        priorityQueue.insert(searchNode);
        twinPriorityQueue.insert(twinSearchNode);

        while (!searchNode.getBoard().isGoal() && !twinSearchNode.getBoard().isGoal()) {

            searchNode = priorityQueue.delMin();
            predecessor = searchNode.predecessor;
            Iterable<Board> boards = searchNode.getBoard().neighbors();

            for (Board board : boards) {
                if (predecessor == null || !board.equals(predecessor.getBoard())) {
                    priorityQueue.insert(new SearchNode(board, searchNode, searchNode.getNumOfMoves() + 1));
                }
            }

            twinSearchNode = twinPriorityQueue.delMin();
            twinPredecessor = twinSearchNode.predecessor;
            Iterable<Board> twinBoards = twinSearchNode.getBoard().neighbors();

            for (Board twinBoard : twinBoards) {
                if (twinPredecessor == null || !twinBoard.equals(twinPredecessor.getBoard())) {
                    twinPriorityQueue.insert(
                            new SearchNode(twinBoard, twinSearchNode, twinSearchNode.getNumOfMoves() + 1));
                }
            }
        }

        if (searchNode.getBoard().isGoal()) {
            isSolvable = true;
            solution = new LinkedList<>();
            moves = searchNode.getNumOfMoves();

            while (searchNode != null) {
                solution.push(searchNode.getBoard());
                searchNode = searchNode.predecessor;
            }
        }
    }

    private SearchNode reverse(SearchNode head) {
        SearchNode current = head;

        SearchNode descendant = null;
        SearchNode predecessor;

        while (current != null) {
            predecessor = current.predecessor;
            current.predecessor = descendant;
            descendant = current;
            current = predecessor;
        }

        return descendant;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }
//    public static void main(String[] args) // solve a slider puzzle (given below)


    private class SearchNode implements Comparable<SearchNode> {
        Board board;
        SearchNode predecessor;

        int numOfMoves;

        SearchNode(Board board, SearchNode predecessor, int numOfMoves) {
            this.board = board;
            this.predecessor = predecessor;
            this.numOfMoves = numOfMoves;
        }

        int hamming() {
            return board.hamming() + numOfMoves;
        }

        int manhattan() {
            return board.manhattan() + numOfMoves;
        }

        int getNumOfMoves() {
            return numOfMoves;
        }

        public Board getBoard() {
            return board;
        }

        @Override
        public int compareTo(SearchNode that) {
            return manhattan() < that.manhattan() ? -1 : 1;
        }
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

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