package model;

public class Box {
    private int column;
    private int row;
    private Box above;
    private Box below;
    private Box previous;
    private Box next;
    private Snake snake;
    private Ladder ladder;
    private Player player;
    private int id;
    private boolean state;
    private boolean idRender;

    public Box(int row, int column, int id) {
        this.column = column;
        this.row = row;
        this.id = id;
        state = false;
        idRender = true;
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

    public Snake getSnake() {
        return this.snake;
    }

    public void setSnake(Snake snake) {
        setState(true);
        this.snake = snake;
    }

    public Ladder getLadder() {
        return this.ladder;
    }

    public void setLadder(Ladder ladder) {
        setState(true);
        this.ladder = ladder;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isIdRender() {
        return this.idRender;
    }

    public boolean getIdRender() {
        return this.idRender;
    }

    public void setIdRender(boolean idRender) {
        this.idRender = idRender;
    }

}
