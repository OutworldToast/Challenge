package com.example.challenge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

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
    private String plantid;

    @FXML
    private boolean perdag = true;

    @Override
    void start() {
        getPlants();
        updateDB();
    }

    @FXML
    private String[][] grabDB(String datatype){
        DatabaseConnection DBconnection = new DatabaseConnection();
        if (perdag) {
            String[][] resultaat = DBconnection.getQuery(
                    "SELECT HOUR(moment), waarde" + "FROM " + datatype +
                            "WHERE moment > DATE_SUB(NOW(), INTERVAL 24 HOUR)" +
                            "ORDER BY moment"
            );
            return resultaat;
        } else {
            String[][] resultaat = new String[7][1];
            for(int i = 0; i < 6; i++) {
                resultaat = DBconnection.getQuery(
                        "SELECT SUM(waarde)" + "FROM " + datatype +
                                "WHERE moment > DATE_SUB(NOW(), INTERVAL 168 HOUR)" +
                                "AND apparaat = '" + plantid + "'" +
                                "ORDER BY moment"
                );
            }
            return resultaat;
        }
    }

    @FXML
    private void updateDB(){
    }

    @FXML
    private void makeGraph(String datatype){ //security: check voor incomplete data
        xAxis.setTickUnit(1);
        if(perdag){
            xAxis.setLowerBound(0);
            xAxis.setUpperBound(23);
        } else {
            xAxis.setLowerBound(1);
            xAxis.setUpperBound(7);
        }
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName(datatype);
        series1.getData().add(new XYChart.Data<>(1, 20));
        series1.getData().add(new XYChart.Data<>(2, 100));
        series1.getData().add(new XYChart.Data<>(3, 80));
        series1.getData().add(new XYChart.Data<>(4, 180));
        series1.getData().add(new XYChart.Data<>(5, 20));
        series1.getData().add(new XYChart.Data<>(6, -10));
        usagechart.getData().add(series1);
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
    }

    @FXML
    private void onPerweekclick(MouseEvent event) {
        perdag = false;
    }
}
