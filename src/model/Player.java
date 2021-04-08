package model;

import java.util.Random;

public class Player {
    // MButton controller;
    // Board board;
    int posRow;
    private int posColumn;
    private Random r;
    int number, laststep = 0;
    private boolean completed;
    private int turn;
    private String nickName;
    private int score;
    private String tokenGame;

    public Player(String tokenGame) {
        this.tokenGame = tokenGame;
        posRow = 0;
        posColumn = 0;
        turn = 0;
        completed = false;
        r = new Random();
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTokenGame() {
        return this.tokenGame;
    }

    public void setTokenGame(String tokenGame) {
        this.tokenGame = tokenGame;
    }

    // public Player(Board b) {
    // this.board = b;
    // }

    // public void setController(MButton bt) {
    // this.controller = bt;
    // }

    public void rollDice() {
        number = (1 + r.nextInt(6));
        turn++;
    }

    public void reset() {
        turn = 0;
        setLaststep(0);
        reachedEnd(false);
    }

    public int getDiceNumber() {
        return number;
    }

    public int getRow() {
        return posRow;
    }

    public void setRow(int posRow) {
        this.posRow = posRow;
    }

    public int getColumn() {
        return posColumn;
    }

    public void setColumn(int posColumn) {
        this.posColumn = posColumn;
    }

    public int getLaststep() {
        return laststep;
    }

    public void setLaststep(int laststep) {
        this.laststep = laststep;
    }

    public int getTurn() {
        return turn;
    }

    /**
     *
     * @return true If The Player Has Finished Successfully,false If Not
     */
    public boolean hasReachedEnd() {
        return completed;
    }

    /**
     *
     * @param a specifies If The Player Has Reached End
     */
    public void reachedEnd(boolean a) {
        completed = a;
    }

}
