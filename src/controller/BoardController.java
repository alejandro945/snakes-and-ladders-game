package controller;

import model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

public class BoardController {

    private GameController gameController;
    private Game game;

    public BoardController(GameController gc, Game game) {
        gameController = gc;
        this.game = game;
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
    void launchDice(ActionEvent event) {

    }
}
