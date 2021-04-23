package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
                int gridSpace = (game.getColumns() * game.getRows()/2);

                if (Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]) > gridSpace - 2) {
                    gameController.alert(AlertType.WARNING, "Warning",
                            "The matriz out of boundaries, max number of ladders and snakes is "
                                    + ((gridSpace) - 2));

                } else {
                    game.setSnakes(Integer.parseInt(parts[0]));
                    game.setLadders(Integer.parseInt(parts[1]));
                    gameController.setPlayers();
                }

            } catch (IOException e) {
                e.printStackTrace();
                gameController.alert(AlertType.ERROR, "Error", "Fail to load next screen");
            } catch (NumberFormatException nfe) {
                gameController.alert(AlertType.ERROR, "Error", "Please insert only numbers");
            } catch (ArrayIndexOutOfBoundsException aibe){
                gameController.alert(AlertType.ERROR, "Error", "Please insert the numbers with a space between");
            }

        } else {
            gameController.alert(AlertType.WARNING, "Warning", "Please complete the field");
        }

    }

}
