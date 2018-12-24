import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private int[][] blocks;
    private final int length;
    private int emptyRow;
    private int emptyCol;

    public Board(int[][] blocks) {
        length = blocks.length;
        this.blocks = new int[length][length];

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                this.blocks[i][j] = blocks[i][j];

                if (blocks[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    public int dimension() {
        return length;
    }

    public int hamming() {
        int k = 0;

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                int goalValue = (1 + j + i * length) % (length * length);
                if (blocks[i][j] != goalValue && goalValue != 0) {
                    ++k;
                    }
            }
        }

        return k;
    }

    public int manhattan() {
        int k = 0;

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                if (blocks[i][j] != 0) {
                    int row = (blocks[i][j] - 1) / length;
                    int col = (blocks[i][j] - 1) % length;
                    k += Math.abs(col - j) + Math.abs(row - i);
                }
            }
        }

        return k;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        int row = (emptyRow == 0) ? 1 : 0;
        int col = 0;
        int row2 = (emptyRow == 0) ? 1 : 0;
        int col2 = 1;

        return swap(row, col, row2, col2);
    }

    public boolean equals(Object y) {
        if (y == null || !(y.getClass() == this.getClass())) return false;
        if (y == this) return true;

        Board that = (Board) y;

        return Arrays.deepEquals(blocks, that.blocks);
    }

    public Iterable<Board> neighbors() {

        List<Board> boards = new ArrayList<>();

        if (emptyRow > 0) {
            boards.add(swap(emptyRow, emptyCol, emptyRow - 1, emptyCol));
        }

        if (emptyRow < length - 1) {
            boards.add(swap(emptyRow, emptyCol, emptyRow + 1, emptyCol));
        }

        if (emptyCol > 0) {
            boards.add(swap(emptyRow, emptyCol, emptyRow, emptyCol - 1));
        }

        if (emptyCol < length - 1) {
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

}