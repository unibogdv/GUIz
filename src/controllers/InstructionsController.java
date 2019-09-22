package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class InstructionsController implements SceneSwitch {

    @FXML
    private Button goBackFromInstructions;

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "./src/main_menu.fxml");
    }

}
