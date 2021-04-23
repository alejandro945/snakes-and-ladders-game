package controller;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Game;
import model.Player;

public class TopScoreController {

    private GameController gameController;
    private Game game;
    private int listSize;

    public TopScoreController(GameController gc, Game game) {
        gameController = gc;
        this.game = game;
        listSize = game.getTopScoresNumb();

    }

    @FXML
    private GridPane topPlayers;

    public void printList() {
        topPlayers.getChildren().clear();
        createList(1);
        topPlayers.setStyle(labelStyling());
        boxStyle(topPlayers, 0, listSize);
    }

    public void createList(int n) {
        if (n <= listSize) {
            System.out.println("Llamado " + n);
            Player winPlayer = game.getTopScore().getNResult(game.getTopScore().getRoot(), listSize, 1, 1);
            if (winPlayer != null) {
                Label temp = new Label(winPlayer.getNickName() + " - " + winPlayer.getScore());

                topPlayers.add(temp, 0, n);
            }
            n++;

            createList(n);
        }
    }

    private String labelStyling() {
        return "-fx-font-size:15px;" + "-fx-font-weight: bold;" + "-fx-font-family: \"Ubuntu\"";
    }

    private void boxStyle(GridPane gP, int n, int i) {
        if (n < i) {
            GridPane.setFillHeight(gP.getChildren().get(n), true);
            GridPane.setFillWidth(gP.getChildren().get(n), true);
            GridPane.setHgrow(gP.getChildren().get(n), Priority.ALWAYS);
            GridPane.setVgrow(gP.getChildren().get(n), Priority.ALWAYS);
            GridPane.setHalignment(gP.getChildren().get(n), HPos.CENTER);
            GridPane.setValignment(gP.getChildren().get(n), VPos.CENTER);
            
            n++;
            boxStyle(gP, n, i);
        }
    }
}
