package com.example.challenge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController {

    @FXML
    private ImageView icon;

    @FXML
    private ImageView logo;

    @FXML
    private Text usernamelabel;

    @FXML
    private Label welcomeText;

    @FXML
    private void initialize(){
        if (!LoginController.getGebruikerID().equals(-1)){
            usernamelabel.setText(LoginController.getNaam());
        }
    }
    @FXML
    private void onClickIcon(MouseEvent event) throws IOException {
        HelloApplication.changeScreenMouse(event, "hello-view.fxml");
    }

}
