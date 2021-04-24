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
    // binary-Tree
    private Player left;
    private Player right;
    private Player topNext;

    public Player(String tokenGame, int laststep) {
        this.tokenGame = tokenGame;
        movements = 0;
        completed = false;
        position = 1;
        this.laststep = laststep;
        r = new Random();
        left = null;
        right = null;
        topNext = null;
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
        this.nextInBox = nextInBox;
    }

    public String getPlayersInBox(Player first, String msg) {
        if (first.getNextInBox() != null) {
            msg += " " + first.getNextInBox().getTokenGame();
            return getPlayersInBox(first.getNextInBox(), msg);
        } else {
            return msg;
        }
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
        int result = position + number;
        isWinner(result);
        movements++;
        this.setNextInBox(null);
    }

    public void isWinner(int result) {
        if (result == laststep) {
            completed = true;
            position += number;
        } else if (result <= laststep) {
            position += number;
        }
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
        isWinner(position);
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
    // ----------------------Binary-Tree--------------------------

    public Player getLeft() {
        return this.left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public Player getRight() {
        return this.right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public void insertNode(Player newPNode) {

    }

    public Player getTopNext() {
        return topNext;
    }

    public void setTopNext(Player topNext) {
        this.topNext = topNext;
    }

    
}
