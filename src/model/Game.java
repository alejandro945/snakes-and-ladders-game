package model;

public class Game {
    private Player firstPlayer;
    private int amountPlayers;
    private String[] chosenTokens;
    private Player current;
    private MatrixGrid grid;
    private int rows;
    private int columns;
    private int snakes;
    private int ladders;

    private boolean finished;

    public Game() {

    }

    public void initializeGame() {
        this.finished = false;
        grid = new MatrixGrid(columns, rows, snakes, ladders);
        if (amountPlayers == 0) {
            firstPlayer = new Player(chosenTokens[0]);
            createPlayers(chosenTokens.length, 1, firstPlayer);
        } else {
            firstPlayer = new Player(getPlayerToken());
            createPlayers(amountPlayers, 1, firstPlayer);
        }
        current = firstPlayer;

    }

    public void getNextPlayer(Player pCase) {
        if (pCase.getNextInGame() != null) {
            setCurrent(pCase.getNextInGame());
        } else {
            setCurrent(firstPlayer);
        }
    }

    public void createPlayers(int players, int render, Player firstCurrent) {
        if (render < players) {
            Player c = null;
            if (amountPlayers == 0) {
                if (render == 1) {
                    setPInBox(firstCurrent);
                }
                c = new Player(chosenTokens[render]);
                setPInBox(c);
            } else {
                if (render == 1) {
                    setPInBox(firstCurrent);
                }
                c = new Player(getPlayerToken());
                setPInBox(c);
            }
            firstCurrent.setNextInGame(c);
            createPlayers(players, render + 1, c);
        }
    }

    private void setPInBox(Player p) {
        Box playerCurrentPos = grid.searchBox(p.getPosition());
        if (playerCurrentPos.getPlayer() == null) {
            playerCurrentPos.setPlayer(p);
        } else {
            setPlayersInBox(p, playerCurrentPos.getPlayer());
        }
    }

    public String getPlayerToken() {
        int redux = (int) (Math.random() * (9) + 1);
        String token = "";
        switch (redux) {
        case 1:
            token = "!";
            break;
        case 2:
            token = "O";
            break;
        case 3:
            token = "X";
            break;
        case 4:
            token = "#";
            break;
        case 5:
            token = "$";
            break;
        case 6:
            token = "&";
            break;
        case 7:
            token = "/";
            break;
        case 8:
            token = "+";
            break;
        case 9:
            token = "*";
            break;
        }
        return token;
    }

    public void roll() {
        Box prev = grid.boxConditions(current.getPosition(), grid.getFirst());
        if (current.getNextInBox() != null) {
            prev.setPlayer(current.getNextInBox());
        } else {
            prev.setPlayer(null);
        }
        current.rollDice();
        System.out.println(current.getPosition());
        Box playerCurrentPos = grid.boxConditions(current.getPosition(), grid.getFirst());
        if (playerCurrentPos.getPlayer() == null) {
            playerCurrentPos.setPlayer(current);
        } else {
            setPlayersInBox(current, playerCurrentPos.getPlayer());
        }

    }

    public void setPlayersInBox(Player p, Player firstInCase) {
        if (firstInCase.getNextInBox() != null) {
            setPlayersInBox(p, firstInCase.getNextInBox());
        } else {
            firstInCase.setNextInBox(p);
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

    public int getAmountPlayers() {
        return amountPlayers;
    }

    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    public Player getCurrent() {
        return this.current;
    }

    public void setCurrent(Player current) {
        this.current = current;
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

    public String[] getChosenTokens() {
        return this.chosenTokens;
    }

    public void setChosenTokens(String[] chosenTokens) {
        this.chosenTokens = chosenTokens;
    }

}
