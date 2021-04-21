package model;

public class Game {
    private Player firstPlayer;
    private int amountPlayers;
    private String[] chosenTokens; ///
    private Player current;
    private MatrixGrid grid;
    private int rows;
    private int columns;
    private int snakes;
    private int ladders;

    private Symbol firstToken;

    private boolean finished;

    public Game() {

    }

    public void initializeGame() {
        this.finished = false;
        grid = new MatrixGrid(columns, rows, snakes, ladders);

        createTokensList();

        if (amountPlayers == 0) {
            // firstPlayer = new Player(chosenTokens[0], grid.getLength());
            // createPlayers(chosenTokens.length, 1, firstPlayer, 9);
        } else {
            // Symbol token = randomSymbol(8);
            // token.getBefore().setNext(token.getNext());
            // token.getNext().setBefore(token.getBefore());

            firstPlayer = new Player(getPlayerToken(), grid.getLength());
            createPlayers(amountPlayers, 1, firstPlayer, 7, 1);
        }
        current = firstPlayer;

    }

    private void createTokensList() {
        firstToken = new Symbol("!");
        Symbol t2 = new Symbol("0");
        Symbol t3 = new Symbol("X");
        Symbol t4 = new Symbol("#");
        Symbol t5 = new Symbol("$");
        Symbol t6 = new Symbol("&");
        Symbol t7 = new Symbol("/");
        Symbol t8 = new Symbol("+");
        Symbol t9 = new Symbol("*");

        firstToken.setNext(t2);
        t2.setNext(t3);
        t3.setNext(t4);
        t4.setNext(t5);
        t5.setNext(t6);
        t6.setNext(t7);
        t7.setNext(t8);
        t8.setNext(t9);
        t9.setNext(firstToken);

        t9.setBefore(t8);
        t8.setBefore(t7);
        t7.setBefore(t6);
        t6.setBefore(t5);
        t5.setBefore(t4);
        t4.setBefore(t3);
        t3.setBefore(t2);
        t2.setBefore(firstToken);
        firstToken.setBefore(t9);
    }

    private Symbol getTokenByNum(int pos, int i, Symbol token) {
        if (i == pos) {
            return token;
        } else {
            token = token.getNext();
            i++;
            getTokenByNum(pos, i, token);
            return null;
        }

    }

    private Symbol randomSymbol(int quantity) {
        int redux = (int) (Math.random() * (quantity) + 0);
        return getTokenByNum(redux, 0, firstToken);
    }

    private boolean tokenValidation(Player player, Player other, int n, boolean ok, int created) {

        if (n == created) {
            return true;
        } else {
            if (!player.getTokenGame().equals(other.getTokenGame())) {
                other = other.getNextInGame();
                n++;
                tokenValidation(player, other, n, ok, created);
            } else {
                ok = false;
                
            }
        }
        return ok;
    }

    private void tokenIteration(Player player, int created) {

        if (!tokenValidation(player, firstPlayer, 0, false, created)) {
            System.out.println(tokenValidation(player, firstPlayer, 0, false, created));
            player.setTokenGame(getPlayerToken());
            tokenIteration(player, created);
        }
    }

    public void getNextPlayer(Player pCase) {
        if (pCase.getNextInGame() != null) {
            setCurrent(pCase.getNextInGame());
        } else {
            setCurrent(firstPlayer);
        }
    }

    public void setScore() {
        int s = current.getMovements() * grid.getLength();
        current.setScore(s);
    }

    public void createPlayers(int players, int render, Player firstCurrent, int spots, int created) {
        if (render < players) {
            Player c = null;
            if (amountPlayers == 0) {
                if (render == 1) {
                    setPInBox(firstCurrent);
                }
                // c = new Player(chosenTokens[render], grid.getLength());

                // Symbol token = randomSymbol(spots);
                // token.getBefore().setNext(token.getNext());
                // token.getNext().setBefore(token.getBefore());

                // c = new Player(token.getValue(), grid.getLength());
                c = new Player(getPlayerToken(), grid.getLength());
                tokenIteration(c, created);
                created++;
                spots--;
                setPInBox(c);
            } else {
                if (render == 1) {
                    setPInBox(firstCurrent);
                }
                // c = new Player(getPlayerToken(), grid.getLength());

                // Symbol token = randomSymbol(spots);
                // token.getBefore().setNext(token.getNext());
                // token.getNext().setBefore(token.getBefore());
                // c = new Player(token.getValue(), grid.getLength());

                c = new Player(getPlayerToken(), grid.getLength());
                tokenIteration(c, created);
                created++;

                spots--;
                setPInBox(c);
            }
            firstCurrent.setNextInGame(c);
            createPlayers(players, render + 1, c, spots, created);
        }
    }

    /*
     * public void createPlayerGiven(int i, String tokens, Player actual, int n) {
     * String[] temp = tokens.split(" "); Player c = actual;
     * 
     * if (i < n) {
     * 
     * c.setTokenGame(temp[i]); setPInBox(c); c = c.getNextInGame(); }
     * 
     * createPlayerGiven(i + 1, tokens, c, ); }
     */

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

    private void setpreviousBox(Box prev) {
        if (current.getNextInBox() != null) {
            prev.setPlayer(current.getNextInBox());
        } else {
            prev.setPlayer(null);
        }
    }

    public void roll() {
        Box prevBox1 = grid.searchBox(current.getPosition());
        Box prev = grid.validateBox(prevBox1);
        setpreviousBox(prev);
        current.rollDice();
        Box prevBox2 = grid.searchBox(current.getPosition());
        Box playerCurrentPos = grid.validateBox(prevBox2);
        if (playerCurrentPos.getPlayer() == null) {
            playerCurrentPos.setPlayer(current);
            current.setPosition(playerCurrentPos.getId());
        } else {
            setPlayersInBox(current, playerCurrentPos.getPlayer());
            current.setPosition(playerCurrentPos.getId());
        }
        if (current.hasReachedEnd()) {
            setFinished(true);
        }
    }

    public void setPlayersInBox(Player p, Player firstInCase) {
        if (firstInCase.getNextInBox() != null) {
            setPlayersInBox(p, firstInCase.getNextInBox());
        } else {
            firstInCase.setNextInBox(p);
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

}
