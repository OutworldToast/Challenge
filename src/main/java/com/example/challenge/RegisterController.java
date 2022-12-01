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
    void onClickIcon(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "hello-view.fxml");
    }

    @FXML
    void onRegisterClick(ActionEvent event) {

    }

}