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

    private Game game;
    private int listSize;

    public TopScoreController(Game game) {
        this.game = game;
        listSize = game.getTopScoresNumb();
        //game.getTopScore().printInorden();
        //System.out.println(game.getTopScore().getTopRoot().getNickName());

        //System.out.println(listSize);
        game.getTopScore().topList();
        //game.getTopScore().printLinearToplist();
        //System.out.println("finaliza el toplist");

    }

    @FXML
    private GridPane topPlayers;

    public void printList() {
        topPlayers.getChildren().clear();
        createList(0);

        if (!topPlayers.getChildren().isEmpty()) {
            topPlayers.setStyle(labelStyling());
            boxStyle(topPlayers, 0, topPlayers.getChildren().size());
            topPlayers.setHgap(20);
        }

    }

    public void createList(int n) {
        if (n < listSize) {
            //System.out.println("\n" + "Llamado " + n);

            Player winPlayer = game.getTopScore().getNResult(game.getTopScore().getTopRoot(), n, 0);
            /* if( winPlayer != null){
                System.out.println(winPlayer.getScore());
            }else{
                System.out.println("NUlo" + n);
            } */
            
            if (winPlayer != null) {
                Label temp = new Label( winPlayer.getNickName() + " - " + winPlayer.getScore());
                Label temp2 = new Label((n+1) + ") ");

                topPlayers.add(temp, 1, n);
                topPlayers.add(temp2, 0, n);
            }

            n++;

            createList(n);
        }
    }

    private String labelStyling() {
        return "-fx-border-color: gray;" +"-fx-font-size:15px;" + "-fx-font-weight: bold;" + "-fx-font-family: \"Ubuntu\"";
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
