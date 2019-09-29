package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

// Istruzioni di gioco e crediti.

public class InstructionsController implements SceneSwitch {

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "./src/main_menu.fxml");
    }
}
