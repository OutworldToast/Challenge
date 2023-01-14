package com.example.challenge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController {

    public VBox root;

    @FXML
    private ImageView icon;

    @FXML
    private ImageView logo;

    @FXML
    private Text usernamelabel;

    @FXML
    private void initialize(){
        usernamelabel.setText(LoginController.getNaam());
    }

    @FXML
    private void onClickPlant(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "stat-screen.fxml");
    }

    @FXML
    private void onClickPlus(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "connect-scherm.fxml");
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
