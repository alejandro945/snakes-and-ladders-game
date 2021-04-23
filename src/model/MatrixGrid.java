package model;

public class MatrixGrid {
    private Box first;
    private int numColumns;
    private int numRows;
    private int length;

    public MatrixGrid(int m, int n, int s, int l) {
        numColumns = m;
        numRows = n;
        length = m * n;

        createGrid();
        createLadders(l, 0, 1);
        createSnakes(s, 0, 'A');
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
            int id = 0;
            if (numRows % 2 == 0) {
                id = (i % 2 == 0) ? previous.getId() - 1 : previous.getId() + 1;
            } else {
                id = (i % 2 == 0) ? previous.getId() + 1 : previous.getId() - 1;
            }
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

    public int getColor() {
        return (int) (Math.random() * (190) + 130);
    }

    public void createSnakes(int s, int render, char snakeName) {
        int id1 = (int) (Math.random() * (length) + 1);
        int id2 = (int) (Math.random() * (length) + 1);
        if (render < s) {
            if (id1 != length && id1 - id2 > numColumns) {
                Box snakeHead = searchBox(id1, first);
                Box snakeTail = searchBox(id2, first);
                Snake newSnake = new Snake(snakeName, getColor(), getColor(), getColor());
                if (snakeHead != null & snakeTail != null) {
                    snakeHead.setSnake(newSnake);
                    snakeTail.setSnake(newSnake);
                    newSnake.setHead(snakeHead);
                    newSnake.setTail(snakeTail);
                    char newName = (char) (snakeName + 1);
                    createSnakes(s, render + 1, newName);
                } else {
                    createSnakes(s, render, snakeName);
                }
            } else {
                createSnakes(s, render, snakeName);
            }

        }

    }

    public void createLadders(int l, int render, int ladderNumber) {
        int id1 = (int) (Math.random() * (length) + 1);
        int id2 = (int) (Math.random() * (length) + 1);
        if (render < l) {
            if (id2 != 1 && id1 - id2 > numColumns) {
                Box ladderHead = searchBox(id1, first);
                Box ladderTail = searchBox(id2, first);
                Ladder newLadder = new Ladder(ladderNumber, getColor(), getColor(), getColor());
                if (ladderHead != null & ladderTail != null) {
                    ladderHead.setLadder(newLadder);
                    ladderTail.setLadder(newLadder);
                    newLadder.setHead(ladderHead);
                    newLadder.setTail(ladderTail);
                    int newLadderNumber = (ladderNumber + 1);
                    createLadders(l, render + 1, newLadderNumber);
                } else {
                    createLadders(l, render, ladderNumber);
                }
            } else {
                createLadders(l, render, ladderNumber);
            }
        }
    }

    private Box searchBox(int id, Box current) {
        if (current.getId() == id && !current.getState()) {
            return current;
        } else if (current.getRow() % 2 == 0 && current.getNext() != null) {
            return searchBox(id, current.getNext());
        } else if (current.getRow() % 2 != 0 && current.getPrevious() != null) {
            return searchBox(id, current.getPrevious());
        } else if (current.getBelow() != null) {
            return searchBox(id, current.getBelow());
        } else {
            return null;
        }
    }

    private Box searchBoxOut(int id, Box current) {
        if (current.getId() == id) {
            return current;
        } else if (current.getRow() % 2 == 0 && current.getNext() != null) {
            return searchBoxOut(id, current.getNext());
        } else if (current.getRow() % 2 != 0 && current.getPrevious() != null) {
            return searchBoxOut(id, current.getPrevious());
        } else if (current.getBelow() != null) {
            return searchBoxOut(id, current.getBelow());
        } else {
            return null;
        }
    }

    public Box validateBox(Box current) {
        if (current.getSnake() != null) {
            return sCondition(current, current.getId());
        } else if (current.getLadder() != null) {
            return lCondition(current, current.getId());
        } else {
            return current;
        }
    }

    public Box sCondition(Box current, int id) {
        if (current.getSnake().getHead().getId() == id) {
            return current.getSnake().getTail();
        } else {
            return current;
        }
    }

    public Box lCondition(Box current, int id) {
        if (current.getLadder().getTail().getId() == id) {
            return current.getLadder().getHead();
        } else {
            return current;
        }
    }

    public Box searchBox(int id) {
        return searchBoxOut(id, first);
    }

}
