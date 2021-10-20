package app.controller;

import app.model.Compte;
import app.model.Membre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MembresController implements Initializable {

    @FXML
    private TableView tvMembres;

    @FXML
    private TableColumn<Membre, String> tcNom;

    @FXML
    private TableColumn<Membre, String> tcPrenom;

    public TableColumn<Compte, String> tcEmail;

    private ObservableList<Membre> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        Compte compte = new Compte();
        compte.setEmail("r.monlouis@gmail.com");

        Membre membre = new Membre();
        membre.setNom("Monlouis");
        membre.setPrenom("Ruddy");
        membre.setCompte(compte);

        data = FXCollections.observableArrayList(
            membre
        );

        tvMembres.setItems(data);
    }
}
;