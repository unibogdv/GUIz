package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

// Gestisce la schermata di login alle "Impostazioni".
// Per praticità sono stati preconfigurati sia username che password.
// Sempre per praticità si è scelto di non usare il campo "PasswordField"
// al fine di evitare che la password venisse mascherata con gli asterischi.

public class ControlPanelLogin implements SceneSwitch {

    @FXML
    Button loginButton;
    @FXML
    TextField usernameText;
    @FXML
    TextField passwordText;

    private static final String username = "admin";
    private static final String password = "password";

    @FXML
    public void handleLogin(ActionEvent click) throws IOException {
        String checkUsername = usernameText.getText();
        String checkPassword = passwordText.getText();

        if(checkUsername.equals(username) && checkPassword.equals(password)) {
            switchScene(click,"./src/control_panel_board.fxml");
        } else {
            System.out.println("Password errata");
        }
    }

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "./src/main_menu.fxml");
    }
}
