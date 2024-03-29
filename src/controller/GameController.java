package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.*;

public class GameController {
    @FXML
    private Pane mainPane;

    private Game game;

    public GameController(Game game) {
        this.game = game;

        serialization();
    }

    private void serialization(){
        try {
            boolean loaded = game.loadData();
            if (!loaded) {
               alert(AlertType.INFORMATION, "Welcome", "You will use a no records version");
            }
        } catch (ClassNotFoundException | IOException e) {

           alert(AlertType.ERROR, "Fail", "The data can't be loaded. The data file is corrupted");

            e.printStackTrace();
        }
    }

    // --------------------------- navigation

    public void welcomeToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/menu.fxml"));

        MenuController controller = new MenuController(this, game);

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
        controller.initializeBoard();
    }

    public void topScores() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/topScore.fxml"));

        TopScoreController controller = new TopScoreController(game);

        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
        controller.printList();
    }

    // Menu bar actions
    @FXML
    public void menu(ActionEvent event) {
        try {
            welcomeToMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void howToPlay(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/help.fxml"));
        HelpController controller = new HelpController();
        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
        controller.HowVisible();

    }

    @FXML
    public void rules(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/help.fxml"));
        HelpController controller = new HelpController();
        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
        controller.rulesVisible();
    }

    @FXML
    public void about(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screens/help.fxml"));
        HelpController controller = new HelpController();
        fxmlLoader.setController(controller);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);
        controller.aboutVisible();
    }

    // --------------------------- Alerts

    public void alert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // --------------------------------Modal---------------------------------------------
    public void modal() throws IOException {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your nickname:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            game.getCurrent().setNickName(result.get());
            alert(AlertType.INFORMATION, "Success", "Player nickname set successfully");
            welcomeToMenu();
        }
    }
}
