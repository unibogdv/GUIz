package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController implements SceneSwitch {

    @FXML
    private Button play;
    @FXML
    private Button instructions;
    @FXML
    private Button controlPanel;
    @FXML
    private Button exit;

    @FXML
    public void handlePlay(ActionEvent click) throws IOException {
        switchScene(click, "./src/game.fxml");
    }

    public void handleInstructions(ActionEvent click) throws IOException {
        switchScene(click, "./src/instructions.fxml");
    }

    public void handleControlPanel(ActionEvent click) throws IOException {
        switchScene(click, "./src/control_panel_login.fxml");
    }

    public void handleExit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
