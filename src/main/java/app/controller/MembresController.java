package app.controller;

import app.dao.MembreDao;
import app.model.Membre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MembresController implements Initializable {

    public TableColumn<Membre, String> tcNom;
    public TableColumn<Membre, String> tcPrenom;
    public TableColumn<Membre, String> tcEmail;
    public TableView tvMembres;
    private ObservableList<Membre> dataMembre;
    private IndexController indexController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcEmail.setCellValueFactory(data -> data.getValue().getCompte().emailProperty());

        dataMembre = FXCollections.observableArrayList();
        MembreDao membreDao = new MembreDao();
        ArrayList<Membre> membres = membreDao.findAll();
        membres.forEach(m -> {
            dataMembre.add(m);
        });

        tvMembres.setItems(dataMembre);

    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }
}
;