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
    private Button registerbutton;

    @FXML
    private TextField wachtwoordfield;

    @FXML
    private Label wachtwoordlabel;

    @FXML
    private Label welcomeText;

    private static String gebruikerID = "-1";

    public static String getGebruikerID() {
        return gebruikerID;
    }

    private static String naam = "faal1";

    public static String getNaam() {
        return naam;
    }

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        if(checkLogin()) {
            HelloApplication.changeScreen(event, "stat-screen.fxml");
        }
    }

    private boolean checkLogin() {
        DatabaseConnection database = new DatabaseConnection();
        String wachtwoord = wachtwoordfield.getText();
        String email = emailfield.getText();
        String[][] resultaat = database.getQuery(
                "SELECT wachtwoord FROM gebruiker WHERE mailadres = '" + email + "'"
        );
        try {
            if (resultaat.length == 1) {
                if (resultaat[0][0].equals(wachtwoord)) {
                    setData(email);
                    return true;
                } else {
                    foutmeldinglabel.setText("Uw wachtwoord is incorrect");
                }
            } else if (resultaat.length == 0) {
                foutmeldinglabel.setText("Uw mailadres is incorrect");
            } else {
                errorMail();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    private void setData(String email) {
        DatabaseConnection database = new DatabaseConnection();
        String resultaat[][] = database.getQuery(
                "SELECT gebruikerID, naam FROM gebruiker WHERE mailadres = '" + email + "'"
        );
        try {
            if (resultaat.length == 1) {
                gebruikerID = resultaat[0][0];
                naam = resultaat[0][1];
            } else {errorMail();}
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void errorMail() {

    }

    @FXML
    private void onRegisterClick(ActionEvent event) throws IOException {
        HelloApplication.changeScreen(event, "register.fxml");
    }

}

