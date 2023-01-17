package com.example.challenge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ConnectController extends Template {


    @FXML
    private Label message;

    @FXML
    private TextField codefield;

    @FXML
    private TextField naamfield;

    @FXML
    private void onClickButton(ActionEvent event) throws IOException {
        DatabaseConnection database = new DatabaseConnection();
        String code = codefield.getText();
        String naam = naamfield.getText();
        message.setTextFill(Color.RED);
        try {
            if (code != "" && naam != "") {
                String resultaat[][] = database.getQuery(
                        "SELECT status FROM apparaat WHERE apparaatID = '" + code + "'"
                );
                String id = LoginController.getGebruikerID();
                if (resultaat.length == 1) {
                    if (resultaat[0][0].equals("inactief")) {
                        database.setQuery(
                                "UPDATE apparaat SET status = 'verbonden', gebruiker = '"
                                        + id + "', naam = '" + naam +  "' WHERE apparaatID = '" + code + "'"
                        );
                        message.setTextFill(Color.GREEN);
                        message.setText("Uw BioPal is geregistreerd");
                    }
                    else {message.setText("De BioPal is niet beschikbaar");}
                } else {message.setText("Geen geldige code");}
            } else if (code == "") {message.setText("Vul een code in");}
            else if (naam == "") {message.setText("Vul een naam in");}
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    void start() {

    }
}