module com.example.challenge {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.challenge to javafx.fxml;
    exports com.example.challenge;
}