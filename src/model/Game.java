package model;

import javafx.scene.paint.Color;

public class Game {
    private Player firstPlayer;
    private MatrixGrid grid;
    private boolean finished;

    public Game(int rows, int columns, int snakes, int ladders, int amountPlayers) {
        this.grid = new MatrixGrid(columns, rows, snakes, ladders);
        this.finished = false;
        firstPlayer = new Player(getPlayerToken());
        createPlayers(amountPlayers, 1, firstPlayer);
    }

    public void createPlayers(int players, int render, Player firstCurrent) {
        if (render < players) {
            Box playerCurrentPos = grid.searchPlayerBox(firstCurrent.getPosition(), grid.getFirst());
            setPlayersInBox(firstCurrent, playerCurrentPos);
            Player current = new Player(getPlayerToken());
            firstCurrent.setNextInGame(current);
            createPlayers(players, render + 1, current);
        }
    }

    public String getPlayerToken() {
        Color c = Color.color(Math.random(), Math.random(), Math.random());
        return c.toString();
    }

    public void roll(Player p) {
        p.rollDice();
        System.out.println(p.getPosition());
        Box playerCurrentPos = grid.searchPlayerBox(p.getPosition(), grid.getFirst());
        setPlayersInBox(p, playerCurrentPos);
    }

    public void setPlayersInBox(Player p, Box b) {
        if (b.getPlayer() == null) {
            b.setPlayer(p);
        } else if (b.getPlayer().getNextInBox() == null) {
            b.getPlayer().setNextInBox(p);
        } else if (b.getPlayer().getNextInBox() != null) {
            setPlayersInBox(b.getPlayer().getNextInBox(), b);
        }

    }

    public void deletePlayerInBox(int count, int pos, Player pCurrent) {
        if (pCurrent != null) {
            if (count < pos) {
                Player newCurrent = pCurrent;
                deletePlayerInBox(count + 1, pos, newCurrent);
            }
        }
    }

    public Game() {

    }

    public MatrixGrid getGrid() {
        return this.grid;
    }

    public void setGrid(MatrixGrid grid) {
        this.grid = grid;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Player getFirstPlayer() {
        return this.firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

}
