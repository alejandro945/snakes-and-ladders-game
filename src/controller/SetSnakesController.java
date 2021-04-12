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
        if(!snakesLadders.getText().isEmpty()){
            try {
                gameController.setPlayers();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
