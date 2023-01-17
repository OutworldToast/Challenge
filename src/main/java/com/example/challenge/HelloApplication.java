package com.example.challenge;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 560);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void changeScreen(ActionEvent actionEvent, String location) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(location));
        Scene scene = new Scene(fxmlLoader.load(), 300, 560);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScreenMouse(MouseEvent mouseEvent, String location) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(location));
        Scene scene = new Scene(fxmlLoader.load(), 300, 560);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScreenMenuItem( Node node,String location) throws IOException {
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(location));
        Scene scene = new Scene(fxmlLoader.load(), 300, 560);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}