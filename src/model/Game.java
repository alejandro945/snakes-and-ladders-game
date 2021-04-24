package model;

public class Game {
    private LeaderBoard topScore;
    private Player firstPlayer;
    private int amountPlayers;
    private String chosenTokens;
    private Player current;
    private MatrixGrid grid;
    private int rows;
    private int columns;
    private int snakes;
    private int ladders;

    private int topScoresNumb;

    private boolean finished;

    public Game() {
        topScore = new LeaderBoard();
        topScoresNumb = 0;

        testTree();

    }

    public void initializeGame() {

        this.finished = false;
        grid = new MatrixGrid(columns, rows, snakes, ladders);

        if (!chosenTokens.equals("")) {
            createPlayerGiven(0, chosenTokens, null);
        } else {
            firstPlayer = new Player(getPlayerToken(), grid.getLength());
            setPInBox(firstPlayer);
            createPlayers(amountPlayers, 1, firstPlayer);
        }
        current = firstPlayer;
    }

    private void testTree(){
        Player p1 = new Player("H", 20);
        Player p2 = new Player("H", 20);
        Player p3 = new Player("H", 20);
        Player p4 = new Player("H", 20);
        Player p5 = new Player("H", 20);
        Player p6 = new Player("H", 20);
        Player p7 = new Player("H", 20);
        Player p8 = new Player("H", 20);
        Player p9 = new Player("H", 20);
        Player p10 = new Player("H", 20);

        p1.setNickName("p1");
        p1.setScore(20);

        p2.setNickName("p2");
        p2.setScore(40);

        p3.setNickName("p3");
        p3.setScore(25);

        p4.setNickName("p4");
        p4.setScore(35);

        p5.setNickName("p5");
        p5.setScore(45);

        p6.setNickName("p6");
        p6.setScore(30);

        p10.setNickName("p10");
        p10.setScore(10);

        p7.setNickName("p7");
        p7.setScore(5);

        p8.setNickName("p8");
        p8.setScore(15);

        p9.setNickName("p9");
        p9.setScore(55);

        topScoresNumb = topScore.insertNode(p1, topScoresNumb);
        topScoresNumb = topScore.insertNode(p2, topScoresNumb); 
        topScoresNumb = topScore.insertNode(p3, topScoresNumb); 
        topScoresNumb = topScore.insertNode(p4, topScoresNumb); 
        topScoresNumb = topScore.insertNode(p5, topScoresNumb); 
        topScoresNumb = topScore.insertNode(p6, topScoresNumb);
        topScoresNumb = topScore.insertNode(p7, topScoresNumb); 
        topScoresNumb = topScore.insertNode(p8, topScoresNumb); 
        topScoresNumb = topScore.insertNode(p9, topScoresNumb); 
        topScoresNumb = topScore.insertNode(p10, topScoresNumb);  


    }

    private String tokenValidation(Player current, String randomToken, boolean found) {
        if (found == false) {
            return randomToken;
        } else {
            if (current.getNextInGame() != null && !current.getTokenGame().equals(randomToken)) {
                return tokenValidation(current.getNextInGame(), randomToken, found);
            } else if (current.getNextInGame() == null && !current.getTokenGame().equals(randomToken)) {
                return tokenValidation(current, randomToken, false);
            } else {
                return tokenValidation(firstPlayer, getPlayerToken(), found);
            }
        }
    }

    public void getNextPlayer(Player pCase) {
        if (pCase.getNextInGame() != null) {
            setCurrent(pCase.getNextInGame());
        } else {
            setCurrent(firstPlayer);
        }
    }

    public void setWinnerScore() {
        int s = current.getMovements() * grid.getLength();
        current.setScore(s);
        topScoresNumb = topScore.insertNode(current, topScoresNumb); 
        //topScore.printInorden();
    }

    public void createPlayers(int players, int render, Player firstCurrent) {
        if (render < players) {
            Player c = null;
            if (render == 1) {
                // setPInBox(firstCurrent);
            }
            c = new Player(tokenValidation(firstPlayer, getPlayerToken(), true), grid.getLength());
            setPInBox(c);
            firstCurrent.setNextInGame(c);
            createPlayers(players, render + 1, c);
        }
    }

    public void createPlayerGiven(int render, String tokens, Player actual) {
        String[] temp = tokens.split(" ");
        if (render < amountPlayers) {
            if (actual == null) {
                firstPlayer = new Player(temp[render], grid.getLength());
                setPInBox(firstPlayer);
                createPlayerGiven(render + 1, tokens, firstPlayer);
            } else {
                Player p = new Player(tokenValidation(actual, temp[render], true), grid.getLength());
                setPInBox(p);
                actual.setNextInGame(p);
                createPlayerGiven(render + 1, tokens, p);
            }
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

    public String getChosenTokens() {
        return this.chosenTokens;
    }

    public void setChosenTokens(String chosenTokens) {
        this.chosenTokens = chosenTokens;
    }

    public int getTopScoresNumb() {
        return topScoresNumb;
    }

    public void setTopScoresNumb(int topScoresNumb) {
        this.topScoresNumb = topScoresNumb;
    }

    public LeaderBoard getTopScore() {
        return topScore;
    }

    public void setTopScore(LeaderBoard topScore) {
        this.topScore = topScore;
    }  
    
}
