package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import model.*;

public class MenuController {

    private GameController gameController;

    public MenuController(GameController gc, Game game) {
        gameController = gc;
    }

    @FXML
    public void play(ActionEvent event) {
        try {
            gameController.setBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void topScores(ActionEvent event) {
        try {
            gameController.topScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void github(MouseEvent event) throws IOException {
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows"))
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "https://github.com/alejandro945/snakes-and-ladders-game ");
        else if (osName.contains("Linux"))
            Runtime.getRuntime().exec("xdg-open " + "https://github.com/alejandro945/snakes-and-ladders-game ");
        else if (osName.contains("Mac OS X"))
            Runtime.getRuntime().exec("open " + "https://github.com/alejandro945/snakes-and-ladders-game ");
    }
}
