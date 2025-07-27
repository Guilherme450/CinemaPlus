module com.cinema {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cinema.view to javafx.fxml;
    exports com.cinema.view;
    exports com.cinema.controller;
}