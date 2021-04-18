package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
    void next(MouseEvent event) {
        if (!colsRows.getText().isEmpty()) {
            try {
                String[] parts = colsRows.getText().split(" ");
                game.setColumns(Integer.parseInt(parts[0]));
                game.setRows(Integer.parseInt(parts[1]));
                gameController.setSnakes(); 
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException nfe) {
                //Alerta ingresar números
            }
        }

    }

}
