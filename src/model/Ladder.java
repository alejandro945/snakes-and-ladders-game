package model;

public class Ladder {
    private int ladderNumber;
    private Box head;
    private Box tail;
    private int r;
    private int g;
    private int b;

    public Ladder(int ladderNumber, int r, int g, int b) {
        this.ladderNumber = ladderNumber;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getLadderNumber() {
        return this.ladderNumber;
    }

    public void setLadderNumber(int ladderNumber) {
        this.ladderNumber = ladderNumber;
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
