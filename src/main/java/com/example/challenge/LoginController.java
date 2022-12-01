package com.example.challenge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailfield;

    @FXML
    private Label emaillabel;

    @FXML
    private Label foutmeldinglabel;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private TextField wachtwoordfield;

    @FXML
    private Label wachtwoordlabel;

    @FXML
    private Label welcomeText;

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        if(checkLogin()) {
            HelloApplication.changeScreen(event, "stat-screen.fxml");
        }
        else{
            foutmeldinglabel.setText("Incorrecte Gegevens");
        }
    }

    private boolean checkLogin(){
        if(wachtwoordfield.getText().equals("test") && emailfield.getText().equals("test@test.nl")){
            return true;
        }
        return false;
    }

    @FXML
    void onRegisterClick(ActionEvent event) throws IOException {
        HelloApplication.changeScreen(event, "register.fxml");
    }

}

