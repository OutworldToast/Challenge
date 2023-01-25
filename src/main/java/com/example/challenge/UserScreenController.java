package com.example.challenge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UserScreenController extends Template{

    @FXML
    private Menu menutextplant;

    @FXML
    private Menu menutext;

    @FXML
    private String plantid;

    @FXML
    private TextField naamfield;

    @FXML
    private TextField emailfield;

    @FXML
    private TextField locatiefield;

    @FXML
    private TextField oudfield;

    @FXML
    private TextField nieuwfield;

    @FXML
    private TextField bnieuwfield;

    @FXML
    private Text errormessage;

    @Override
    void start() {
        getPlants();
    }

    @FXML
    void updateData(String datatype, String data) {
        DatabaseConnection database = new DatabaseConnection();
        String id = LoginController.getGebruikerID();
        try {
            database.setQuery("UPDATE gebruiker SET " + datatype + "= '" + data + "' WHERE gebruikerID = '" + id + "'");
        } catch (Exception e) {
            System.out.println(e);
        }
        LoginController.setNaam(data);
    }

    @FXML
    void leegMessage(String datatype) {
        errormessage.setText("Voer een " + datatype + " in");
        errormessage.setFill(Color.RED);
    }

    @FXML
    void geslaagdMessage(){
        errormessage.setText("Gelukt!");
        errormessage.setFill(Color.GREEN);
    }

    @FXML
    void veranderEmail(ActionEvent event) {
        String email = emailfield.getText();
        if (!email.isEmpty()) {
            updateData("mailadres", email);
        } else {leegMessage("email");}
    }

    @FXML
    void veranderLocatie(ActionEvent event) {
        String locatie = locatiefield.getText();
        if (!locatie.isEmpty()) {
            updateData("locatie", locatie);
        } else {leegMessage("locatie");}
    }

    @FXML
    void veranderNaam(ActionEvent event) {
        String naam = naamfield.getText();
        if (!naam.isEmpty()) {
            updateData("naam", naam);
        } else {leegMessage("naam");}
    }

    @FXML
    void veranderWachtwoord(ActionEvent event) {

    }

    private void getPlants(){
        DatabaseConnection database = new DatabaseConnection();
        String ID = LoginController.getGebruikerID();
        String [][] resultaat1 = database.getQuery(
                "SELECT count(*) FROM apparaat WHERE gebruiker ='" + ID + "'"
        );
        try {
            if (resultaat1.length > 0) {
                int aantal = Integer.parseInt(resultaat1[0][0]);
                String [][] resultaat2 = database.getQuery(
                        "SELECT naam, apparaatID FROM apparaat WHERE gebruiker ='" + ID + "'"
                );
                for (int i = 0; i < aantal; i++) {
                    String naam = resultaat2[i][0];
                    String id = resultaat2[i][1];
                    MenuItem item = new MenuItem(naam);
                    item.setOnAction(event -> {
                        menutextplant.setText(naam);
                        plantid = id;
                    });
                    menutextplant.getItems().addAll(item);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}