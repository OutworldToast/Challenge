package com.example.challenge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import java.io.File;
import java.util.Scanner;

public class StatScreenController extends Template {


    @FXML
    private Menu menutextplant;

    @FXML
    private Menu menutext;

    @FXML
    private LineChart usagechart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private String plantid = "0";

    @FXML
    private boolean perdag = true;

    @Override
    void start() {
        getPlants();
//        updateDB();
    }

    private void updateDB(){
        DatabaseConnection db = new DatabaseConnection();
        String datatype = "";
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String moment = dateformat.format(timestamp);
        String [] array = {"200", "300", "20"};
//        String[] array = new String[3];
        String id = "4";
        for (int i = 0; i < 3; i++) {
            if (i == 0){datatype = "toevoer";}
            else if (i == 1) {datatype = "grondvochtigheid";}
            else if (i == 2) {datatype = "temperatuur";}
            db.setQuery("INSERT INTO " + datatype + "(waarde, moment, apparaat)" +
            "VALUES ('" + array[i] + "','" + moment +  "','" + id + "')"
            );
        }

        //wverbruik
        //vochtigheid
        //temp
    }

    @FXML
    private String[][] grabDB(String datatype){
        DatabaseConnection DBconnection = new DatabaseConnection();
        LocalDate datum = LocalDate.now();
        if (perdag) {
            String[][] resultaat = DBconnection.getQuery(
                    "SELECT DISTINCT moment, waarde FROM " + datatype +
                            " WHERE moment LIKE '" + datum + "%' AND apparaat = '" + plantid + "'"
            );
            return resultaat;
        } else {
            String[][] resultaat = new String[7][1];
            for (int i = 0; i <= 6; i++) {
                if (datatype.equals("toevoer")) {
                    String[][] resultaat2 = DBconnection.getQuery(
                            "SELECT SUM(waarde) FROM " + datatype +
                                    " WHERE moment LIKE '" + datum + "%' AND apparaat = '" + plantid + "'"
                    );
                    if (resultaat2.length > 0) {
                        resultaat[i][0] = resultaat2[0][0];
                    }
                } else {
                    String[][] resultaat2 = DBconnection.getQuery(
                            "SELECT AVG(waarde) FROM " + datatype +
                                    " WHERE moment LIKE '" + datum + "%' AND apparaat = '" + plantid + "'"
                    );
                    if (resultaat2.length > 0) {
                        resultaat[i][0] = resultaat2[0][0];
                    }
                }
                datum = datum.minusDays(1);
            }
            return resultaat;
        }
    }

    @FXML
    private void makeGraph(String datatype){ //security: check voor incomplete data
        usagechart.getData().clear();
        if (!plantid.equals("0")) {
            String[][] resultaat = grabDB(datatype);
            XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
            series1.setName(datatype);
            if (perdag) {
                for (int i = 0; i < resultaat.length; i++) {
                    series1.getData().add(new XYChart.Data<>(i, Integer.valueOf(resultaat[i][1])));
                }
                usagechart.getData().add(series1);
            } else {
                for (int i = 0; i < resultaat.length; i++) {
                    System.out.println(resultaat[resultaat.length-i-1][0]);
                    int data = (int) Double.parseDouble(resultaat[resultaat.length-i-1][0]);
                    System.out.println(data);
                    series1.getData().add(new XYChart.Data<>(i, data));
                }
                usagechart.getData().add(series1);
            }
        } else {//geef warning
//            XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
//            series1.setName(datatype);
//            series1.getData().add(new XYChart.Data<>(1, 20));
//            series1.getData().add(new XYChart.Data<>(2, 100));
//            series1.getData().add(new XYChart.Data<>(3, 80));
//            series1.getData().add(new XYChart.Data<>(4, 180));
//            series1.getData().add(new XYChart.Data<>(5, 20));
//            series1.getData().add(new XYChart.Data<>(6, -10));
//            usagechart.getData().add(series1);
        }


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
        makeGraph("temperatuur");
    }

    @FXML
    private void onGrondvochtigheidClick(ActionEvent event) {
        menutext.setText("Grondvochtigheid");
        makeGraph("grondvochtigheid");
    }

    @FXML
    private void onLuchtvochtigheidClick(ActionEvent event) {
        menutext.setText("Luchtvochtigheid");
        makeGraph("luchtvochtigheid");
    }

    @FXML
    private void onWaterverbruikClick(ActionEvent event) {
        menutext.setText("Waterverbruik");
        makeGraph("toevoer");
    }

    @FXML
    private void onPerdagclick(MouseEvent event) {
        perdag = true;
        xAxis.setLabel("Tijd in uren");
    }

    @FXML
    private void onPerweekclick(MouseEvent event) {
        perdag = false;
        xAxis.setLabel("Tijd in dagen");
    }
}
