package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import model.*;
import controller.*;

public class Main extends Application {

    public Game game;
    public GameController gameController;

    public Main() {
        game = new Game();
        gameController = new GameController(game);
    }

    public static void main(String[] args) {

        MatrixGrid mg = new MatrixGrid(4,4);

        mg.showMatriz();

        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("screens/mainPane.fxml"));
        fxmlloader.setController(gameController);
        Parent root = fxmlloader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        gameController.welcomeToMenu();
    }

}
