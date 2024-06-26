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
    private Label foutmeldinglabel;

    @FXML
    private TextField wachtwoordfield;

    @FXML
    private int teller = 0;

    private static String gebruikerID = "-1";

    public static String getGebruikerID() {
        return gebruikerID;
    }

    private static String naam = "faal1";

    public static String getNaam() {
        return naam;
    }

    public static void setNaam(String naam) {
        LoginController.naam = naam;
    }

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        teller++;
        if(checkLogin() && teller < 5) {
            teller = 0;
            HelloApplication.changeScreen(event, "Main_screen.fxml");
        } else if (teller > 5) {
            foutmeldinglabel.setText("U heeft te vaak ingelogd");
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

