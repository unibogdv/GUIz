package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// Work in progress, lo scopo è realizzare un'interfaccia che esegua una finestra Pop-Up sulla scena principale,
// la finestra non deve consentire l'interazione con la scena sottostante, inoltre non deve essere possibile
// chiuderla fino a quando non si effettua una scelta.

public interface PopUpWindow {

    default void windowPopUp(ActionEvent click, String resource) throws IOException {

        Button source = (Button)click.getSource();
        Scene currentScene = source.getScene();
        Stage window = (Stage)currentScene.getWindow();

        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);

        URL url = new File(resource).toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene newScene = new Scene(root);

        window.setScene(newScene);
        window.showAndWait();
    }
}
