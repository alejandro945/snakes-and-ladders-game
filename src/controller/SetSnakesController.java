package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

public class SetSnakesController {

    private GameController gameController;
    private Game game;

    public SetSnakesController(GameController gc, Game game) {
        gameController = gc;
        this.game = game;
    }

    @FXML
    private TextField snakesLadders;

    @FXML
    public void next(MouseEvent event) {
        if (!snakesLadders.getText().isEmpty()) {
            try {
                String[] parts = snakesLadders.getText().split(" ");
                game.setSnakes(Integer.parseInt(parts[0]));
                game.setLadders(Integer.parseInt(parts[1]));

                gameController.setPlayers();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException nfe) {
                // Alerta ingresar números
            }

        }

    }

}
