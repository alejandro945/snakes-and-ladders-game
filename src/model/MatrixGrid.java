package model;

public class MatrixGrid {
    private Box first;
    private int numColumns;
    private int numRows;

    public MatrixGrid(int m, int n) {
        numColumns = m;
        numRows = n;
        createGrid();
    }

    public Box getFirst() {
        return this.first;
    }

    public int getNumColumns() {
        return this.numColumns;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public void createGrid() {
        first = new Box(0, 0);
        createRows(0, 0, first);
    }

    public void createRows(int i, int j, Box currentFirstRow) {
        createColumn(i, j + 1, currentFirstRow, currentFirstRow.getAbove().getNext());
        if (i + 1 < numRows) {
            Box downFirstRow = new Box(i + 1, j);
            downFirstRow.setAbove(currentFirstRow);
            currentFirstRow.setBelow(downFirstRow);
            createRows(i + 1, j, downFirstRow);
        }
    }

    public void createColumn(int i, int j, Box previous, Box rowPrev) {
        if (j < numColumns) {
            Box current = new Box(i, j);
            current.setPrevious(previous);
            previous.setNext(current);
            if (rowPrev != null) {
                current.setAbove(rowPrev);
                rowPrev.setBelow(current);
                if (rowPrev.getNext() != null) {
                    rowPrev = rowPrev.getNext();
                }
            }
            createColumn(i, j + 1, current, rowPrev);
        }
    }

}
