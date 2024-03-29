package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.*;

public class SetBoardController {

    private GameController gameController;
    private Game game;

    public SetBoardController(GameController gc, Game game) {
        gameController = gc;
        this.game = game;
    }

    @FXML
    private TextField colsRows;

    @FXML
    public void next(MouseEvent event) {
        if (!colsRows.getText().isEmpty()) {
            try {
                String[] parts = colsRows.getText().split(" ");
                int maxGrid = 60;
                if ((Integer.parseInt(parts[0])) * (Integer.parseInt(parts[1])) > maxGrid) {
                    gameController.alert(AlertType.WARNING, "Warning",
                            "The matriz out of boundaries, create a matriz smaller than 60 cells");

                } else if (Integer.parseInt(parts[0]) <= 1 && Integer.parseInt(parts[1]) <= 1) {
                    gameController.alert(AlertType.WARNING, "Warning",
                            "The matriz out of boundaries, create a board bigger than 1x1");

                } else {
                    game.setColumns(Integer.parseInt(parts[0]));
                    game.setRows(Integer.parseInt(parts[1]));
                    gameController.setSnakes();
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
