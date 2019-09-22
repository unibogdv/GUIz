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

public interface PopUpWindow {

    default void windowPopUp(ActionEvent click, String resource) throws IOException {

        //Gets the current window.
        Button source = (Button)click.getSource();
        Scene currentScene = source.getScene();
        Stage window = (Stage)currentScene.getWindow();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);

        //Need to load a new 'fxml' file.
        URL url = new File(resource).toURI().toURL();
        Parent root = FXMLLoader.load(url);

        //Sets the scene for that file.
        Scene newScene = new Scene(root);

        //PRINT OUT FOR TESTING
        System.out.println("The scene has been switched to " + resource);

        //Change to another scene
        window.setScene(newScene);
        window.showAndWait(); // The windows will stay on top
    }
}
