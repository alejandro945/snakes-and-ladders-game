package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.*;

public class GameController {
    @FXML
    private Pane mainPane;
    @FXML
    private ImageView exit;
    private MenuController menuController;

    public GameController(Game game) {
        menuController = new MenuController(game);
    }

    public void welcomeToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/menu.fxml"));
        fxmlLoader.setController(menuController);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
    }

    @FXML
    void exitGame(MouseEvent event) {
        if (event.getSource() == exit) {
            System.exit(0);
        }
    }
}
