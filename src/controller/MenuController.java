package controller;

import java.io.IOException;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import model.*;

public class MenuController {
    @FXML
    private StackPane pnlStack;
    @FXML
    private Pane config1;
    @FXML
    private Pane config2;
    @FXML
    private Pane config3;

    @FXML
    private TextField txtBSize;
    @FXML
    private TextField txtPlayers;
    @FXML
    private TextField txtSLSizes;

    @FXML
    private Button next1;
    @FXML
    private Button next2;
    @FXML
    private Button play;

    @FXML
    private ImageView backToConfig1;
    @FXML
    private ImageView backToConfig2;
    private GameController gc;
    private Game game;

    public MenuController(GameController gc, Game game) {
        this.gc = gc;
    }

    @FXML
    void config(ActionEvent event) throws IOException {
        gc.showConfig();
    }

    @FXML
    void showLeaderboard(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void handleMouseClick(MouseEvent event) {
        if (event.getSource() == backToConfig1) {
            new FadeIn(config1).play();
            config1.toFront();
        } else if (event.getSource() == backToConfig2) {
            new FadeIn(config2).play();
            config2.toFront();
        }
    }

    @FXML
    void continue1(ActionEvent event) {
        new FadeIn(config2).play();
        config2.toFront();
    }

    @FXML
    void bSizeEvent(KeyEvent event) {
        if (event != null) {
            String render = txtBSize.getText();
            if (render.length() == 2) {
                next1.setDisable(false);
            } else {
                next1.setDisable(true);
            }
        }
    }

    @FXML
    void continue2(ActionEvent event) {
        new FadeIn(config3).play();
        config3.toFront();
    }

    @FXML
    void slEvent(KeyEvent event) {
        String render = txtSLSizes.getText();
        if (event != null) {
            if (render.length() == 2) {
                next2.setDisable(false);
            } else {
                next2.setDisable(true);
            }
        }
    }

    @FXML
    void startGame(ActionEvent event) {

    }

    @FXML
    void playerEvent(KeyEvent event) {
        if (event != null) {
            String render = txtPlayers.getText();
            if (render.length() == 0) {
                play.setDisable(false);
            } else {
                play.setDisable(true);
            }
        }
    }

}
