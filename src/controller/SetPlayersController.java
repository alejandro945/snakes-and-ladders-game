package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.*;

public class SetPlayersController {

    private GameController gameController;
    private Game game;

    public SetPlayersController(GameController gc, Game game) {
        gameController = gc;
        this.game = game;
    }

    @FXML
    private TextField players;

    @FXML
    public void randomTokens(ActionEvent event) {
        if (!players.getText().isEmpty()) {
            try {
                game.setChosenTokens(players.getText());
                game.setAmountPlayers(players.getText().split(" ").length);
                game.initializeGame();
                gameController.showBoard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            gameController.alert(AlertType.WARNING, "Warning", "Please complete the field");
        }

    }

    @FXML
    public void next(MouseEvent event) {
        if (!players.getText().isEmpty()) {
            try {
                if (Integer.parseInt(players.getText()) > 10) {
                    gameController.alert(AlertType.WARNING, "Warning", "The number maximun of random player is 9");
                } else if (Integer.parseInt(players.getText()) == 0) {
                    gameController.alert(AlertType.WARNING, "Warning", "The number of players can not be 0");
                }
                game.setChosenTokens("");
                game.setAmountPlayers(Integer.parseInt(players.getText()));
                game.initializeGame();

                gameController.showBoard();

            } catch (IOException e) {
                e.printStackTrace();
                gameController.alert(AlertType.ERROR, "Error", "Fail to load the board");
            } catch (NumberFormatException nfe) {

                if (!players.getText().isEmpty()) {
                    try {
                        game.setChosenTokens(players.getText());
                        game.setAmountPlayers(players.getText().split(" ").length);
                        game.initializeGame();
                        gameController.showBoard();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    gameController.alert(AlertType.WARNING, "Warning", "Please complete the field");
                }

                //gameController.alert(AlertType.ERROR, "Error", "Please insert only numbers");
            } catch (ArrayIndexOutOfBoundsException aibe){
                gameController.alert(AlertType.ERROR, "Error", "Please insert the numbers with a space between");
            }
        } else {
            gameController.alert(AlertType.WARNING, "Warning", "Please complete the field");
        }

    }
}
