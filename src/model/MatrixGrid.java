package model;

import java.util.Arrays;

public class MatrixGrid {
    private Box first;
    private int numColumns;
    private int numRows;
    private int length;

    public MatrixGrid(int m, int n) {
        numColumns = m;
        numRows = n;
        length = m * n;
        createGrid();
    }

    public Box getFirst() {
        return this.first;
    }

    public int getNumColumns() {
        return this.numColumns;
    }

    public int getLength() {
        return this.length;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public void createGrid() {
        int id = (numRows % 2 == 0) ? length : (length) - numColumns + 1;
        first = new Box(0, 0, id);
        createRows(0, 0, first);
    }

    public void createRows(int i, int j, Box currentFirstRow) {
        createColumn(i, j, currentFirstRow, currentFirstRow.getAbove());
        i++;
        if (i < numRows) {
            int size = numColumns * (numRows - i);
            int id = ((numRows - i) % 2 == 0) ? size : (size - numColumns + 1);
            Box downFirstRow = new Box(i, 0, id);
            downFirstRow.setAbove(currentFirstRow);
            currentFirstRow.setBelow(downFirstRow);
            createRows(i, 0, downFirstRow);
        }
    }

    public void createColumn(int i, int j, Box previous, Box above) {
        j++;
        if (j < numColumns) {
            int id = (i % 2 == 0) ? previous.getId() - 1 : previous.getId() + 1;
            Box current = new Box(i, j, id);
            current.setPrevious(previous);
            previous.setNext(current);
            if (above != null) {
                current.setAbove(above.getNext());
                above.getNext().setBelow(current);
                above = above.getNext();
            }
            createColumn(i, j, current, above);
        }
    }

    public void showMatriz() {
        Box actual = first;
        int[][] mat = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            Box primeroDeLinea = actual;
            for (int j = 0; j < numColumns; j++) {
                if (actual != null) {
                    mat[i][j] = actual.getId();
                    if (actual.getAbove() != null) {
                        System.out.println(actual.getAbove().getId());
                    }

                    actual = actual.getNext();
                } else {
                    mat[i][j] = -1;
                }
            }
            actual = primeroDeLinea.getBelow();
        }

        for (int[] is : mat) {
            System.out.println(Arrays.toString(is));
        }

    }

}
