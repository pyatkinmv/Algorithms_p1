package ru.pyatkinmv;

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
        return this;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null || !(y instanceof Board)) return false;

        Board that = (Board) y;

        return Arrays.deepEquals(blocks, that.blocks);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int row = -1;
        int col = -1;

        List<Board> boards = new ArrayList<>();

        if (row > 0) {
            boards.add(swap(row - 1, col));
        }

        if (row < n - 1) {
            boards.add(swap(row + 1, col));
        }

        if (col > 0) {
            boards.add(swap(row, col - 1));
        }

        if (col < n - 1) {
            boards.add(swap(row, col + 1));
        }

        return boards;
    }

    private Board swap(int newRow, int newCol) {
        blocks[emptyCol][emptyCol] = blocks[newRow][newCol];
        blocks[newRow][newCol] = 0;
        Board board = new Board(blocks);
        blocks[newRow][newCol] = blocks[emptyRow][emptyCol];
        blocks[emptyRow][emptyCol] = 0;

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

        int[][] arr = {
                {1, 8, 3},
                {4, 0, 2},
                {7, 6 ,5}
        };
        Board board = new Board(arr);

        System.out.println(board.manhattan());
        System.out.println(board.hamming());
        System.out.println(board.toString());

    }
}