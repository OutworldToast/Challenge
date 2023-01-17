package com.example.challenge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController extends Template {

    @FXML
    private void onClickPlant(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "stat-screen.fxml");
    }

    @FXML
    private void onClickPlus(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "connect-scherm.fxml");
    }

    @Override
    void start() {

    }
}
