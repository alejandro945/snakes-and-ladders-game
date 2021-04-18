package model;

import java.util.Random;

public class Player {
    private Random r;
    private int number, laststep, position;
    private boolean completed;
    private String nickName;
    private int score;
    private int movements;
    private String tokenGame;
    private Player nextInGame;
    private Player nextInBox;
    private int posInBox = 0;

    public Player(String tokenGame) {
        this.tokenGame = tokenGame;
        movements = 0;
        completed = false;
        position = 1;
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

    public Player getNextInGame() {
        return this.nextInGame;
    }

    public void setNextInGame(Player nextInGame) {
        this.nextInGame = nextInGame;
    }

    public Player getNextInBox() {
        return this.nextInBox;
    }

    public void setNextInBox(Player nextInBox) {
        setPosInBox(posInBox++);
        this.nextInBox = nextInBox;
    }

    public String getTokenGame() {
        return this.tokenGame;
    }

    public void setTokenGame(String tokenGame) {
        this.tokenGame = tokenGame;
    }

    public int getMovements() {
        return this.movements;
    }

    public void rollDice() {
        number = (1 + r.nextInt(6));
        position += number;
        movements++;
    }

    public void reset() {
        movements = 0;
        setLaststep(0);
        reachedEnd(false);
    }

    public int getDiceNumber() {
        return number;
    }

    public int getLaststep() {
        return laststep;
    }

    public void setLaststep(int laststep) {
        this.laststep = laststep;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public int getPosInBox() {
        return this.posInBox;
    }

    public void setPosInBox(int posInBox) {
        this.posInBox = posInBox;
    }

}
