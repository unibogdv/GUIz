package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;


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
    Button saveButton;
    @FXML
    ToggleGroup difficultyLevel;
    @FXML
    ToggleGroup pointsGroup;

    @FXML
    public void handleSaveSettings(ActionEvent click) throws IOException {

        try {
            String file = "./src/database/difficulty.txt";
            RadioButton selectedRadioButton = (RadioButton) difficultyLevel.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            FileWriter fileWriter = new FileWriter(file);
            //fileWriter.write("test difficulty");
            fileWriter.write(toggleGroupValue);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            String file = "./src/database/amount.txt";
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(questionsAmount.getText());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            String file = "./src/database/points.txt";
            RadioButton selectedRadioButton = (RadioButton) pointsGroup.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            FileWriter fileWriter = new FileWriter(file);

            if(toggleGroupValue.equalsIgnoreCase("si")){
                fileWriter.write("true");
            } else
                fileWriter.write("false");

            labelSaved.setText("Impostazioni salvate");
            fileWriter.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "./src/main_menu.fxml");
    }
}
