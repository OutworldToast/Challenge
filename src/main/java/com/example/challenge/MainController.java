package com.example.challenge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

}
