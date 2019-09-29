package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

// Controller della schermata principale con i tasti di Avvio, Istruzioni, Impostazioni, Uscita.

public class MenuController implements SceneSwitch {

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

    public void handleExit() {
        Platform.exit();
    }
}
