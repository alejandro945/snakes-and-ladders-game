package model;

public class Box {
    private int column;
    private int row;
    private Box above;
    private Box below;
    private Box previous;
    private Box next;

    private int id;

    //public Box(int row, int column, int id) {  /// change order row col
    public Box(int column, int row, int id) {
        this.column = column;
        this.row = row;
        this.id = id;

        above = null;
        below  = null;
        previous = null;
        next = null;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Box getAbove() {
        return this.above;
    }

    public void setAbove(Box above) {
        this.above = above;
    }

    public Box getBelow() {
        return this.below;
    }

    public void setBelow(Box below) {
        this.below = below;
    }

    public Box getPrevious() {
        return this.previous;
    }

    public void setPrevious(Box previous) {
        this.previous = previous;
    }

    public Box getNext() {
        return this.next;
    }

    public void setNext(Box next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /* @Override
    public String toString(){
        return id+"";
    } */
}
