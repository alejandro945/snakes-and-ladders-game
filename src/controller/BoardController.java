package controller;

import model.*;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.event.ActionEvent;

public class BoardController {

    private GameController gameController;
    private Game game;
    private int gridSize;

    public BoardController(GameController gc, Game game) {
        gameController = gc;
        this.game = game;
        gridSize = game.getGrid().getLength();

    }

    @FXML
    private Label resume;

    @FXML
    private Label playerToLaunch;

    @FXML
    private ImageView diceImage;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane anchorPane;

    public void initializeBoard() {
        setBoard();
        playerToLaunch.setText("Turn for Player: " + game.getFirstPlayer().getTokenGame());
    }

    @FXML
    void launchDice(ActionEvent event) {
        game.roll();
        diceImg(game.getCurrent().getDiceNumber());
        setBoard();
        if (!game.getFinished()) {
            game.getNextPlayer(game.getCurrent());
            playerToLaunch.setText("Turn for Player: " + game.getCurrent().getTokenGame());
        } else {
            gameController.alert(AlertType.WARNING, "Warning", "The player: " + game.getCurrent().getTokenGame()
                    + " have reached end with " + game.getCurrent().getMovements() + " movements");
            gameController.modal();
            game.setScore();
        }
    }

    private void setBoard() {
        grid.getChildren().clear();
        Box initialBox = game.getGrid().searchBox(1);
        createBoard(initialBox, initialBox.getId());
        boxStyle(grid, 0, gridSize);
    }

    private void diceImg(int diceRender) {
        Image i = null;
        switch (diceRender) {
        case 1:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/Dices-01.png"));
            diceImage.setImage(i);
            break;
        case 2:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/Dices-03.png"));
            diceImage.setImage(i);
            break;
        case 3:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/Dices-04.png"));
            diceImage.setImage(i);
            break;
        case 4:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/Dices-05.png"));
            diceImage.setImage(i);
            break;
        case 5:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/Dices-06.png"));
            diceImage.setImage(i);
            break;
        case 6:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/Dices-07.png"));
            diceImage.setImage(i);
            break;
        }
    }

    private GridPane createBoard(Box box, int n) {
        if (n <= gridSize) {
            GridPane temp = createBoxes(n, box);

            grid.add(temp, box.getColumn(), box.getRow());
            n++;
            box = game.getGrid().searchBox(n);

            return createBoard(box, n);
        }
        return grid;
    }

    private GridPane createBoxes(int n, Box box) {
        Label snake = (isEmpty(box.getSnake())) ? new Label("") : new Label(box.getSnake().getSnakeName() + "");
        Label ladder = (isEmpty(box.getLadder())) ? new Label("") : new Label(box.getLadder().getLadderNumber() + "");
        Label players = (isEmpty(box.getPlayer())) ? new Label("")
                : new Label(box.getPlayer().getPlayersInBox(box.getPlayer(), box.getPlayer().getTokenGame()));
        Label id = (isEmpty(box)) ? new Label("") : new Label(box.getId() + "");

        GridPane boxInfill = new GridPane();

        if (!isEmpty(box.getSnake())) {
            boxInfill.setStyle("-fx-background-color: rgb(" + box.getSnake().getR() + "," + box.getSnake().getG() + ","
                    + box.getSnake().getB() + ")" + ";-fx-border-color: gray;" + labelStyling());
        } else if (!isEmpty(box.getLadder())) {
            boxInfill.setStyle("-fx-background-color: rgb(" + box.getLadder().getR() + "," + box.getLadder().getG()
                    + "," + box.getLadder().getB() + ")" + ";-fx-border-color: gray;" + labelStyling());
        } else {
            boxInfill.setStyle("-fx-border-color: gray;" + labelStyling());
        }

        boxInfill.add(snake, 2, 2);
        boxInfill.add(players, 1, 1);
        boxInfill.add(id, 0, 2);
        boxInfill.add(ladder, 2, 0);
        boxStyle(boxInfill, 0, 4);

        return boxInfill;
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

    private String labelStyling() {
        return ("-fx-font-size:15px;") + ("-fx-font-weight: bold;") + "-fx-font-family: \"Ubuntu\"";
    }

    private boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }
}
