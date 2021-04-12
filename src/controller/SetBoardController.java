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
        if(!colsRows.getText().isEmpty()){
            try {
                gameController.setSnakes();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    
}
