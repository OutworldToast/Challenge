package com.example.challenge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class StatScreenController extends Template {

    @FXML
    private Menu menutextplant;

    @FXML
    private Menu menutext;

    @FXML
    private BarChart<?, ?> usagechart;

    @FXML
    private NumberAxis verticaleaxis;

    @FXML
    private CategoryAxis horizontaleaxis;

    @FXML
    private String plantid;

    @Override
    void start() {
        getPlants();
    }


    @FXML
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

    @FXML
    private void onTemperatuurClick(ActionEvent event) {
        menutext.setText("Temperatuur");
    }

    @FXML
    private void onGrondvochtigheidClick(ActionEvent event) {
        menutext.setText("Grondvochtigheid");
    }

    @FXML
    private void onLuchtvochtigheidClick(ActionEvent event) {
        menutext.setText("Luchtvochtigheid");
    }

    @FXML
    private void onWaterverbruikClick(ActionEvent event) {
        menutext.setText("Waterverbruik");
    }

}
