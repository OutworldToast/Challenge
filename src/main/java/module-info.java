module com.example.challenge {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.challenge to javafx.fxml;
    exports com.example.challenge;
}