module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens app to javafx.fxml;
    exports app;
    exports app.controller;
    opens app.controller to javafx.fxml;
}
