package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.*;

public class MenuController {
    
    private GameController gameController;

    public MenuController(GameController gc, Game game) {
        gameController = gc;
    }

    @FXML
    void play(ActionEvent event) {
        try {
            gameController.setBoard();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void topScores(ActionEvent event) {
        try {
            gameController.topScores();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }
}
