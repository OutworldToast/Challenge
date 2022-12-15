package com.example.challenge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class StatScreenController {

    @FXML
    private Label dagverbruiklabel;

    @FXML
    private ImageView icon;

    @FXML
    private Label informatielabel;

    @FXML
    private MenuBar informatiemenu;

    @FXML
    private MenuItem itemgrondvochtigheid;

    @FXML
    private MenuItem itemluchtvochtigheid;

    @FXML
    private MenuItem itemtemperatuur;

    @FXML
    private MenuItem itemwaterverbruik;

    @FXML
    private ImageView logo;

    @FXML
    private Menu menutext;

    @FXML
    private BarChart<?, ?> usagechart;

    @FXML
    private NumberAxis verticaleaxis;

    @FXML
    private CategoryAxis horizontaleaxis;

    @FXML
    private Text usernamelabel;

    @FXML
    private Label weekverbruiklabel;

    @FXML
    private Label welcomeText;

    @FXML
    private void initialize(){
        if (!LoginController.getGebruikerID().equals(-1)){
            usernamelabel.setText(LoginController.getNaam());
        }
    }
    @FXML
    void OnTemperatuurClick(ActionEvent event) {
        menutext.setText("Temperatuur");
    }

    @FXML
    void onGrondvochtigheidClick(ActionEvent event) {
        menutext.setText("Grondvochtigheid");
    }

    @FXML
    void onLuchtvochtigheidClick(ActionEvent event) {
        menutext.setText("Luchtvochtigheid");
    }

    @FXML
    void onWaterverbruikClick(ActionEvent event) {
        menutext.setText("Waterverbruik");
    }

}
