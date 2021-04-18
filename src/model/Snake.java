package model;

public class Snake {

    private char snakeName;
    private Box head;
    private Box tail;
    private int r;
    private int g;
    private int b;

    public Snake(char snakeName, int r, int g, int b) {
        this.snakeName = snakeName;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public char getSnakeName() {
        return this.snakeName;
    }

    public void setSnakeName(char snakeName) {
        this.snakeName = snakeName;
    }

    public int getR() {
        return this.r;
    }

    public int getG() {
        return this.g;
    }

    public int getB() {
        return this.b;
    }

    public Box getHead() {
        return this.head;
    }

    public void setHead(Box head) {
        this.head = head;
    }

    public Box getTail() {
        return this.tail;
    }

    public void setTail(Box tail) {
        this.tail = tail;
    }

}
