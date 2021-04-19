package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HelpController {
    @FXML
    private VBox howText;

    @FXML
    private VBox aboutText;

    @FXML
    private VBox rulesText;

    public void HowVisible(){
        howText.setVisible(true);
    }

    public void aboutVisible(){
        aboutText.setVisible(true);
    }

    public void rulesVisible(){
        rulesText.setVisible(true);
    }

}
