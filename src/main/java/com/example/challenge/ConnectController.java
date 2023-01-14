package com.example.challenge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ConnectController {

    public VBox root;

    @FXML
    private ImageView icon;

    @FXML
    private ImageView logo;

    @FXML
    private Text usernamelabel;

    @FXML
    private Label message;

    @FXML
    private TextField codefield;

    @FXML
    private TextField naamfield;

    @FXML
    private void initialize(){
        usernamelabel.setText(LoginController.getNaam());
    }

    @FXML
    private void onClickButton(ActionEvent event) throws IOException {
        DatabaseConnection database = new DatabaseConnection();
        String code = codefield.getText();
        String naam = naamfield.getText();
        try {
            if (code != "" && naam != "") {
                String resultaat[][] = database.getQuery(
                        "SELECT status FROM apparaat WHERE apparaatID = '" + code + "'"
                );
                if (resultaat.length == 1) {
                    if (resultaat[0][0].equals("inactief")) {
                        database.setQuery(
                                "UPDATE apparaat SET status = 'verbonden', gebruiker = '"
                                        + LoginController.getGebruikerID() + "' WHERE apparaatID = '" + code + "'"
                        );
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

    @FXML
    private void onClickIcon(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "user-screen.fxml");
    }

    @FXML
    private void onHomeClick(ActionEvent event) throws IOException {
        HelloApplication.changeScreenMenuItem(root,"Main_Screen.fxml");
    }

    @FXML
    private void onAboutClick(ActionEvent event) throws IOException {
        HelloApplication.changeScreenMenuItem(root, "about-screen.fxml");
    }

    @FXML
    private void onFaqClick(ActionEvent event) throws IOException {
        HelloApplication.changeScreenMenuItem(root, "faq-screen.fxml");
    }

    @FXML
    private void onContactClick(ActionEvent event) throws IOException {
        HelloApplication.changeScreenMenuItem(root, "contact-screen.fxml");
    }
}