package model;

public class Game {
    private Player firstPlayer;
    private MatrixGrid grid;

    public Game(int rows, int columns, int snakes, int ladders, int amountPlayers) {
        MatrixGrid mg = new MatrixGrid(columns, rows, snakes, ladders);

    }

    public Game() {

    }
}
