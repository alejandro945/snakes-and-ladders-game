package controller;

import model.*;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;

public class BoardController {

    private GameController gameController;
    private Game game;
    private int gridSize;

    public BoardController(GameController gc, Game game) {
        gameController = gc;
        this.game = game;
        gridSize = game.getGrid().getLength();
    }

    @FXML
    private Label resume;

    @FXML
    private Label playerToLaunch;

    @FXML
    private ImageView diceImage;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void launchDice(ActionEvent event) {

    }

    public void initializeBoard() {

        Box initialBox = game.getGrid().searchBox(1);

        createBoard(initialBox, initialBox.getId());

        boxStyle(grid, 0, gridSize);

    }

    private GridPane createBoard(Box box, int n) {
        if (n <= gridSize) { // + 1
            GridPane temp = createBoxes(n, box);

            grid.add(temp, box.getColumn(), box.getRow());

            n++;
            box = game.getGrid().searchBox(n);
            // System.out.println("Caja sig " + box.getId());

            return createBoard(box, n);
        }
        return grid;
    }

    private GridPane createBoxes(int n, Box box) {

        Label snake = (isEmpty(box.getSnake()))? new Label("") : new Label(box.getSnake() + "");
        Label ladder = (isEmpty(box.getLadder()))? new Label("") : new Label(box.getLadder() + "");
        Label players = (isEmpty(box.getPlayer()))? new Label("") : new Label(box.getPlayer() + "");
        Label id = (isEmpty(box.getId()))? new Label("") : new Label(box.getId() + "");        

        GridPane boxInfill = new GridPane();

        boxInfill.add(snake, 2, 2);
        boxInfill.add(players, 1, 1);
        boxInfill.add(id, 0, 2);
        boxInfill.add(ladder, 2, 2);

        boxStyle(boxInfill, 0, 4);

        return boxInfill;
    }

    private void boxStyle(GridPane gP, int n, int i) {

        if (n < i) {
            GridPane.setFillHeight(gP.getChildren().get(n) , true);
            GridPane.setFillWidth(gP.getChildren().get(n) , true);
            GridPane.setHgrow(gP.getChildren().get(n), Priority.ALWAYS);
            GridPane.setVgrow(gP.getChildren().get(n), Priority.ALWAYS);
            GridPane.setHalignment(gP.getChildren().get(n), HPos.CENTER);
            GridPane.setValignment(gP.getChildren().get(n), VPos.CENTER);
            n++;
            boxStyle(gP, n, i);
        }
    }

    private boolean isEmpty(Object object){
        if(object == null){
            return true;
        } else {
            return false;
        }
    }
}
