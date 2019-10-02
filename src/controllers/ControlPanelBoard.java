package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.FileWriter;
import java.io.IOException;

// Gestisce il salvataggio delle "Impostazioni" una delle parti più importanti dell'intero programma.
// Tutte le scelte fatte dall'utente nella GUI vengono salvate in differenti file di testo i cui dati
// verranno letti ed elaborati dal GameController.

public class ControlPanelBoard implements SceneSwitch{

    @FXML
    TextField questionsAmount;
    @FXML
    RadioButton easyRadio;
    @FXML
    RadioButton mediumRadio;
    @FXML
    RadioButton hardRadio;
    @FXML
    RadioButton mixRadio;
    @FXML
    RadioButton yesRadio;
    @FXML
    RadioButton noRadio;
    @FXML
    Label labelSaved;
    @FXML
    Label labelError;
    @FXML
    ToggleGroup difficultyLevel;
    @FXML
    ToggleGroup pointsGroup;

    @FXML
    public void handleSaveSettings() throws IOException {

        // Salva il livello di difficoltà selezionato.
        try {
            String file = "src/database/difficulty.txt";
            RadioButton selectedRadioButton = (RadioButton) difficultyLevel.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(toggleGroupValue);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        // Salva il numero di domande a cui rispondere.
        // Verifica la validità dei valori inseriti e lo segnala all'utente.
        try {
            String file = "src/database/amount.txt";
            FileWriter fileWriter = new FileWriter(file);
            if (Integer.parseInt(questionsAmount.getText()) > 42
                    || Integer.parseInt(questionsAmount.getText()) <= 0) {
                labelError.setText("Quantità non valida, setto\ni parametri di default.");
                fileWriter.write("10");
            } else if (Integer.parseInt(questionsAmount.getText()) % 2 != 0) {
                labelError.setText("E' preferibile l'uso dei pari,\nun pò di fair play!");
                fileWriter.write(questionsAmount.getText());
            } else {
                labelError.setText("OK!");
                fileWriter.write(questionsAmount.getText());
            }
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        // Salva la modalità di gioco che assegna punti bonus
        // in base alla difficoltà della domanda.
        try {
            String file = "src/database/points.txt";
            RadioButton selectedRadioButton = (RadioButton) pointsGroup.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            FileWriter fileWriter = new FileWriter(file);

        // La scelta del "si" porta il salvataggio come "true", il "no" del "false".
            if(toggleGroupValue.equalsIgnoreCase("si")){
                fileWriter.write("true");
            } else
                fileWriter.write("false");
                labelSaved.setText("Impostazioni salvate");
                fileWriter.close();
            } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "src/main_menu.fxml");
    }
}
