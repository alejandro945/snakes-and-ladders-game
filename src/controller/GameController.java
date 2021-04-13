package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import model.*;

public class GameController {
    @FXML
    private Pane mainPane;

    private Game game;
     

    public GameController(Game game) {
        this.game = game;
    }

    // --------------------------- navigation

    public void welcomeToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/menu.fxml"));

        MenuController controller =  new MenuController(this, game);

        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();

        mainPane.getChildren().setAll(menu);
    }

    public void setBoard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/config1.fxml"));

        SetBoardController controller = new SetBoardController(this, game);

        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
    }

    public void setSnakes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/config2.fxml"));

        SetSnakesController controller = new SetSnakesController(this, game);

        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
    }

    public void setPlayers() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/config3.fxml"));

        SetPlayersController controller = new SetPlayersController(this, game);

        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
    }

    public void showBoard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/boardGrid.fxml"));
        BoardController controller = new BoardController(this, game);

        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
    }

    public void topScores() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/topScore.fxml"));

        SetSnakesController controller = new SetSnakesController(this, game);

        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
    }

    //Menu bar actions
    @FXML
    public void menu(ActionEvent event) {
        try {
            welcomeToMenu();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void howToPlay(ActionEvent event) {

    }

    @FXML
    public void rules(ActionEvent event) {

    }

    @FXML
    public void about(ActionEvent event) {

    }
}
