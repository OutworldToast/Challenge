package com.example.challenge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RegisterController {

    @FXML
    private ImageView Logo;

    @FXML
    private Label foutmeldinglabel;

    @FXML
    private TextField bwachtwoordfield;

    @FXML
    private Label bwachtwoordlabel;

    @FXML
    private TextField emailfield;

    @FXML
    private Label emaillabel;

    @FXML
    private TextField naamfield;

    @FXML
    private Label naamlabel;

    @FXML
    private Button registerbutton;

    @FXML
    private ImageView usericon;

    @FXML
    private TextField wachtwoordfield;

    @FXML
    private Label wachtwoordlabel;

    @FXML
    private Label welcomeText;

    @FXML
    private void onClickIcon(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "hello-view.fxml");
    }

    @FXML
    private void onRegisterClick(ActionEvent event) {
        DatabaseConnection database = new DatabaseConnection();
        String bwachtwoord = bwachtwoordfield.getText();
        String wachtwoord = wachtwoordfield.getText();
        String naam = naamfield.getText();
        String email = emailfield.getText();
        if(wachtwoord.equals(bwachtwoord) && wachtwoord != "" && naam != "" && email != ""){
            try {
                String[][] resultaat = database.getQuery(
                        "SELECT gebruikerID FROM gebruiker WHERE mailadres = '" + email + "'"
                );
                if (resultaat.length == 0) {
                    database.setQuery(
                            "INSERT INTO gebruiker (naam, mailadres, wachtwoord) VALUES ('" + naam + "', '" + email + "', '" + wachtwoord + "')"
                    );

                } else {
                    foutmeldinglabel.setText("Dit mailadres is al in gebruik");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (!wachtwoord.equals(bwachtwoord)){
            foutmeldinglabel.setText("Uw wachtwoorden zijn niet gelijk");
        } else if (naam == "") {
            foutmeldinglabel.setText("Vul een naam in");
        } else if (email == "") {
            foutmeldinglabel.setText("Vul een email in");
        } else if (wachtwoord == "") {
            foutmeldinglabel.setText("Vul een wachtwoord in");
        }
    }

}