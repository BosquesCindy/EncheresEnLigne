module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires javafaker;


    opens app to javafx.fxml;
    exports app;
    exports app.controller;
    exports app.model;
    opens app.controller to javafx.fxml;
}
