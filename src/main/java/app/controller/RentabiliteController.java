package app.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

import java.time.*;
import javafx.scene.control.*;
import javafx.util.*;

public class RentabiliteController implements Initializable {

    public DatePicker datePicker;
    private IndexController indexController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDatePicker();
    }

    public IndexController getIndexController() {
        return indexController;
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    private void setDatePicker() {
        //datePicker = new DatePicker(LocalDate.now());
        datePicker.setValue(LocalDate.now());
        StringConverter<LocalDate> converter = datePicker.getConverter();
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return converter.toString(object);
            }

            @Override
            public LocalDate fromString(String string) {
                LocalDate date = converter.fromString(string);
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY
                        || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    return datePicker.getValue();
                } else {
                    return date;
                }
            }
        });

        datePicker.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.getDayOfWeek() == DayOfWeek.SATURDAY
                        || date.getDayOfWeek() == DayOfWeek.SUNDAY);
            }
        });

        datePicker.valueProperty().addListener((obs, ov, nv) -> {
            if (nv.getDayOfWeek() == DayOfWeek.SATURDAY || nv.getDayOfWeek() == DayOfWeek.SUNDAY) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Select Appointment");
                alert.setHeaderText(null);
                alert.setContentText("Business Hours are Mon - Fri, 8am - 5pm");
                alert.showAndWait();
            }
        });
    }
}

