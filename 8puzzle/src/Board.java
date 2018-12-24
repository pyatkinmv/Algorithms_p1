import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)

    private int[][] blocks;
    private int n;
    private int emptyRow;
    private int emptyCol;

    public Board(int[][] blocks) {
        n = blocks.length;
        this.blocks = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                this.blocks[i][j] = blocks[i][j];

                if (blocks[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        int k = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int goalValue = (1 + j + i * n) % (n * n);
                if (blocks[i][j] != goalValue && goalValue != 0) {
                    ++k;
                    }
            }
        }

        return k;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int k = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (blocks[i][j] != 0){
                    int row = (blocks[i][j] - 1) / n;
                    int col = (blocks[i][j] - 1) % n;
                    k += Math.abs(col - j) + Math.abs(row - i);
                }
            }
        }

        return k;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int row = (emptyRow == 0) ? 1 : 0;
        int col = 0;
        int row2 = (emptyCol == 0) ? 1 : 0;
        int col2 = 1;

        return swap(row, col, row2, col2);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) return false;

        Board that = (Board) y;

        return Arrays.deepEquals(blocks, that.blocks);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        List<Board> boards = new ArrayList<>();

        if (emptyRow > 0) {
            boards.add(swap(emptyRow, emptyCol, emptyRow - 1, emptyCol));
        }

        if (emptyRow < n - 1) {
            boards.add(swap(emptyRow, emptyCol, emptyRow + 1, emptyCol));
        }

        if (emptyCol > 0) {
            boards.add(swap(emptyRow, emptyCol, emptyRow, emptyCol - 1));
        }

        if (emptyCol < n - 1) {
            boards.add(swap(emptyRow, emptyCol, emptyRow, emptyCol + 1));
        }

        return boards;
    }

    private Board swap(int oldRow, int oldCol, int newRow, int newCol) {
        int temp = blocks[oldRow][oldCol];
        blocks[oldRow][oldCol] = blocks[newRow][newCol];
        blocks[newRow][newCol] = temp;
        Board board = new Board(blocks);
        blocks[newRow][newCol] = blocks[oldRow][oldCol];
        blocks[oldRow][oldCol] = temp;
        return board;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = blocks.length;
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        System.out.println(initial);
        System.out.println();
        System.out.println(initial.twin());

    }

}