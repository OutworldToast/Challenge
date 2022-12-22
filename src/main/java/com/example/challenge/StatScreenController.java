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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

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
    private void onClickIcon(MouseEvent event) throws IOException {//go to user screen
        //HelloApplication.changeScreenMouse(event, "user-screen.fxml");
    }

    @FXML
    private void initialize(){
        if (!LoginController.getGebruikerID().equals(-1)){
            usernamelabel.setText(LoginController.getNaam());
        }
    }
    @FXML
    private void OnTemperatuurClick(ActionEvent event) {
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
