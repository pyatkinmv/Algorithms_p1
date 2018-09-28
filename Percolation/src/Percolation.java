import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation { // 1 -- opened, 0 -- closed
    private boolean[][] arr;
    private final int length;
    private final int lenSqr;
    private int opened = 0;
    private final WeightedQuickUnionUF uf;
    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException();
        arr = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n*n + 2);
        length = n;
        lenSqr = length * length;
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                arr[i][j] = false;
            }
        }
    } // create n-by-n grid, with all sites blocked

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
        if (row > 0 && isOpen(row, col + 1)) uf.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        if (row == length - 1) uf.union(lenSqr + 1, xyTo1D(row, col));
        if (row < length - 1 && isOpen(row + 2, col + 1)) uf.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        if (col > 0 && isOpen(row + 1, col)) uf.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        if (col < length - 1 && isOpen(row + 1, col + 2)) uf.union(xyTo1D(row, col + 1), xyTo1D(row, col));
        if (row == 0) uf.union(lenSqr, xyTo1D(row, col));
    } // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        validate(row, col);
        row = row - 1;
        col = col - 1;
        return arr[row][col];
    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(lenSqr, xyTo1D(row - 1, col - 1))
                && isOpen(row, col);
    } // is site (row, col) full?

    public int numberOfOpenSites() {
        return opened;
    } // number of open sites

    public boolean percolates() {
        return uf.connected(lenSqr, lenSqr + 1);
    } // does the system percolate?
}