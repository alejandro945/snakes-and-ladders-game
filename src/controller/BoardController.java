package controller;

import model.*;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
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

        Box initialBox = game.getGrid().searchBox(1);

        createBoard(initialBox, initialBox.getId());

        boxStyle(grid, 0, gridSize);
        playerToLaunch.setText("Turn for Player: " + game.getFirstPlayer().getTokenGame());

/*         Image i = new Image(getClass().getResourceAsStream("/ui/assets/img/dice-1.png")); // Revisar porque no carga la imagen        
        diceImage.setImage(i);     */    
    }

    @FXML
    void launchDice(ActionEvent event) {
        game.roll();
        diceImg(game.getCurrent().getDiceNumber());
        setBoard();
        game.getNextPlayer(game.getCurrent());
        if (!game.getFinished()) {
            playerToLaunch.setText("Turn for Player: " + game.getCurrent().getTokenGame());
        }
    }

    private void diceImg(int diceRender) {
        Image i = null;
        System.out.println(diceRender);
        switch (diceRender) {
        case 1:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/1.png"));
            diceImage.setImage(i);
            break;
        case 2:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/2.jpg"));
            diceImage.setImage(i);
            break;
        case 3:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/3.jpg"));
            diceImage.setImage(i);
            break;
        case 4:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/4.png"));
            diceImage.setImage(i);
            break;
        case 5:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/5.png"));
            diceImage.setImage(i);
            break;
        case 6:
            i = new Image(getClass().getResourceAsStream("/ui/assets/img/6.png"));
            diceImage.setImage(i);
            break;
        }
    }

    private GridPane createBoard(Box box, int n) {
        if (n <= gridSize) { // + 1
            GridPane temp = createBoxes(n, box);

            grid.add(temp, box.getColumn(), box.getRow());
            n++;
            box = game.getGrid().searchBox(n);

            return createBoard(box, n);
        }
        return grid;
    }

    private void setBoard() {
        grid.getChildren().clear();
        Box initialBox = game.getGrid().searchBox(1);

        createBoard(initialBox, initialBox.getId());

        boxStyle(grid, 0, gridSize);
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
                    + box.getSnake().getB() + ")" + ";-fx-border-color: gray");
        } else if (!isEmpty(box.getLadder())) {
            boxInfill.setStyle("-fx-background-color: rgb(" + box.getLadder().getR() + "," + box.getLadder().getG()
                    + "," + box.getLadder().getB() + ")" + ";-fx-border-color: gray");
        } else {
            boxInfill.setStyle("-fx-border-color: gray");
        }

        labelStyling(snake);
        labelStyling(ladder);
        labelStyling(players);
        labelStyling(id);

        boxInfill.add(snake, 2, 2);
        boxInfill.add(players, 1, 1);
        boxInfill.add(id, 0, 2);
        boxInfill.add(ladder, 2, 2);
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

    private void labelStyling(Label label){
        label.setStyle("-fx-font-family: Ubuntu" );
        label.setStyle("fx-font-weight: bold" );
        label.setStyle("-fx-font-size:12px" );
    
    }

    private boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }
}
