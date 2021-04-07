package model;

public class Player {
    private String nickName;
    private int score;
    private String tokenGame;

    public Player(String tokenGame) {
        this.tokenGame = tokenGame;
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

}
