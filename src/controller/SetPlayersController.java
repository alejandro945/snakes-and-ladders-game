package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
    void next(MouseEvent event) {
        if (!players.getText().isEmpty()) {
            try {
                game.setAmountPlayers(Integer.parseInt(players.getText()));
                game.initializeGame();

                game.getGrid().showMatriz();

                gameController.showBoard(); // Print Matriz CLS

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NumberFormatException nfe) {
                // Alerta ingresar números
            }
        }

    }
}
