import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int length;
    private final int lenSqr;
    private final WeightedQuickUnionUF uf2Virtual;
    private final WeightedQuickUnionUF uf1Virtual;
    private boolean[][] arr;
    private int opened = 0;

    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException();
        length = n;
        lenSqr = length * length;
        arr = new boolean[n][n];
        uf2Virtual = new WeightedQuickUnionUF(lenSqr + 2);
        uf1Virtual = new WeightedQuickUnionUF(lenSqr + 1);

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                arr[i][j] = false;
            }
        }
    }

    private void  validate(int row, int col) {
        if (!(row > 0 && row <= length && col > 0 && col <= length)) throw new IllegalArgumentException("Out of indexes");
    }

    private int xyTo1D(int row, int col) {
        return row * length + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        row = row - 1;
        col = col - 1;
        if (arr[row][col]) return;

        arr[row][col] = true;
        opened++;

        if (row > 0 && isOpen(row, col + 1)) {
            uf2Virtual.union(xyTo1D(row - 1, col), xyTo1D(row, col));
            uf1Virtual.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        }

        if (row == 0) {
            uf2Virtual.union(lenSqr, xyTo1D(row, col));
            uf1Virtual.union(lenSqr, xyTo1D(row, col));
        }

        if (row == length - 1) {
            uf2Virtual.union(lenSqr + 1, xyTo1D(row, col));
        }

        if (row < length - 1 && isOpen(row + 2, col + 1)) {
            uf2Virtual.union(xyTo1D(row + 1, col), xyTo1D(row, col));
            uf1Virtual.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        }

        if (col > 0 && isOpen(row + 1, col)) {
            uf2Virtual.union(xyTo1D(row, col - 1), xyTo1D(row, col));
            uf1Virtual.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        }

        if (col < length - 1 && isOpen(row + 1, col + 2)) {
            uf2Virtual.union(xyTo1D(row, col + 1), xyTo1D(row, col));
            uf1Virtual.union(xyTo1D(row, col + 1), xyTo1D(row, col));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        row = row - 1;
        col = col - 1;
        return arr[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf1Virtual.connected(lenSqr, xyTo1D(row - 1, col - 1))
                && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates() {
        return uf2Virtual.connected(lenSqr, lenSqr + 1);
    }
}