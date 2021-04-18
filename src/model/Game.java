package model;

import javafx.scene.paint.Color;

public class Game {
    private Player firstPlayer;
    private int amountPlayers;


    private MatrixGrid grid;
    private int rows;
    private int columns;
    private int snakes;
    private int ladders;
    


    private boolean finished;

    public Game() {
        this.finished = false;
        firstPlayer = new Player(getPlayerToken());
        
    }

    public void initializeGame(){
        grid = new MatrixGrid(columns, rows, snakes, ladders);
        createPlayers(amountPlayers, 1, firstPlayer);
    }

    public void createPlayers(int players, int render, Player firstCurrent) {
        if (render < players) {
            System.out.println(render);
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
        Box playerCurrentPos = grid.searchBox(p.getPosition());
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

    public int getAmountPlayers() {
        return amountPlayers;
    }

    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getSnakes() {
        return snakes;
    }

    public void setSnakes(int snakes) {
        this.snakes = snakes;
    }

    public int getLadders() {
        return ladders;
    }

    public void setLadders(int ladders) {
        this.ladders = ladders;
    }

    
}
