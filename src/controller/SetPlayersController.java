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
    void randomTokens(ActionEvent event) {
        if (!players.getText().isEmpty()) {
            try {
                game.setAmountPlayers(Integer.parseInt(players.getText()));
                game.initializeGame();

                game.getGrid().showMatriz();// Print Matriz CLS

                gameController.showBoard();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NumberFormatException nfe) {
                // Alerta ingresar números
            }
        }

    }

    @FXML
    void next(MouseEvent event) {
        if (!players.getText().isEmpty()) {
            try {

                if (Integer.parseInt(players.getText()) > 10) {
                    gameController.alert(AlertType.WARNING, "Warning", "The number maximun of random player is 9");
                } else if (Integer.parseInt(players.getText()) == 0) {
                    gameController.alert(AlertType.WARNING, "Warning", "The number of players can not be 0");
                }
                game.setAmountPlayers(Integer.parseInt(players.getText()));
                game.initializeGame();

                game.getGrid().showMatriz();// Print Matriz CLS

                gameController.showBoard();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                gameController.alert( AlertType.ERROR, "Error", "Fail to load the board");
            } catch (NumberFormatException nfe) {
                gameController.alert(AlertType.ERROR, "Error", "Please insert only numbers");
            }
        } else {
            gameController.alert(AlertType.WARNING, "Warning", "Please complete the field");
        }

    }

    /*
     * @FXML void next(MouseEvent event) { if (!players.getText().isEmpty()) { try {
     * String[] parts = players.getText().split(" "); game.setChosenTokens(parts);
     * game.initializeGame();
     * 
     * game.getGrid().showMatriz();// Print Matriz CLS
     * 
     * gameController.showBoard();
     * 
     * } catch (IOException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); gameController.alert(AlertType.ERROR, "Error",
     * "Fail to load the board"); } catch (NumberFormatException nfe) {
     * gameController.alert(AlertType.ERROR, "Error", "Please insert only numbers");
     * } }
     * 
     * }
     */
}
