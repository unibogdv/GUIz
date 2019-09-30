package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// Quest'interfaccia si occupa di gestire il cambio scena.

public interface SceneSwitch {

	default void switchScene(ActionEvent click, String resource) throws IOException {
		
		// Ottiene i riferimenti della scena corrente
		Button source = (Button)click.getSource();
		Scene currentScene = source.getScene();
		Stage window = (Stage)currentScene.getWindow();

		// Carica il nuovo file FXML
		URL url = new File(resource).toURI().toURL();
		Parent root = FXMLLoader.load(url);

		// Imposta la scena con i riferimenti del file FXML
		Scene newScene = new Scene(root);

		// Stampa nel terminale per verificare il funzionamento
		// System.out.println("La scena è stata cambiata in " + resource);
		
		// Mostra la nuova scena
		window.setScene(newScene);
		window.show();
	}
}
