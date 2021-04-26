package controller;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Game;
import model.Player;

public class TopScoreController {

    private Game game;
    private int listSize;

    public TopScoreController(Game game) {
        this.game = game;
        listSize = game.getTopScore().getTopScoresNumb();

        game.getTopScore().topList();
    }

    @FXML
    private GridPane topPlayers;

    public void printList() {
        topPlayers.getChildren().clear();

        GridPane newGridPane = new GridPane();

        Label temp1 = new Label("Rating");
        Label temp2 = new Label("Nickname");
        Label temp3 = new Label("Score");
        Label temp4 = new Label("Board Info");

        newGridPane.add(temp1, 0, 0);
        newGridPane.add(temp2, 1, 0);
        newGridPane.add(temp3, 2, 0);
        newGridPane.add(temp4, 3, 0);
        boxStyle(newGridPane, 0, newGridPane.getChildren().size());
        newGridPane.setStyle(labelStyling());
        newGridPane.getColumnConstraints().add(new ColumnConstraints(50));
        newGridPane.getColumnConstraints().add(new ColumnConstraints(100));
        newGridPane.getColumnConstraints().add(new ColumnConstraints(100));

        topPlayers.add(newGridPane, 0, 0);

        createList(0);

        if (!topPlayers.getChildren().isEmpty()) {

            boxStyle(topPlayers, 0, topPlayers.getChildren().size());
        }
    }

    public void createList(int n) {
        if (n < listSize) {
            Player winPlayer = game.getTopScore().getNResult(game.getTopScore().getTopRoot(), n, 0);

            GridPane newGridPane = new GridPane();

            if (winPlayer != null) {
                Label temp1 = new Label((n + 1) + ") ");
                Label temp2 = new Label(winPlayer.getNickName());
                Label temp3 = new Label(winPlayer.getScore() + "");
                Label temp4 = new Label(winPlayer.getInfo());

                newGridPane.add(temp1, 0, 0);
                newGridPane.add(temp2, 1, 0);
                newGridPane.add(temp3, 2, 0);
                newGridPane.add(temp4, 3, 0);

                if (!newGridPane.getChildren().isEmpty()) {

                    boxStyle(newGridPane, 0, newGridPane.getChildren().size());
                    newGridPane.setStyle(labelStyling());
                    newGridPane.setPrefHeight(60);
                    newGridPane.getColumnConstraints().add(new ColumnConstraints(50));
                    newGridPane.getColumnConstraints().add(new ColumnConstraints(100));
                    newGridPane.getColumnConstraints().add(new ColumnConstraints(100));

                }

                topPlayers.add(newGridPane, 0, n + 1);
            }

            n++;

            createList(n);
        }
    }

    private String labelStyling() {
        return "-fx-padding: 10;" + "-fx-border-color: gray;" + "-fx-font-size:15px;" + "-fx-font-weight: bold;"
                + "-fx-font-family: \"Ubuntu\"";
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
