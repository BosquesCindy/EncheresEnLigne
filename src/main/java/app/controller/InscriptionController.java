package app.controller;

import app.Main;
import app.dao.*;
import app.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

public class InscriptionController {

    private IndexController indexController;

    public TextField txtPrenom;
    public TextField txtEmail;
    public DatePicker txtDateNaissance;
    public TextField txtCodePostal;
    public TextArea txtAdressePostale;
    public TextField txtVille;
    public TextField txtPays;
    public PasswordField txtMdp;
    public PasswordField txtMdpConfirm;
    public Text errorNom;
    public Text errorPrenom;
    public Text errorEmail;
    public Text errorDateNaissance;
    public Text errorVille;
    public Text errorCodePostal;
    public Text errorAdressePostale;
    public Text errorPays;
    public Text errorMdp;
    public Text errorCompte;
    public AnchorPane anchorPaneInscription;
    public TextField txtNom;
    private Stage stage;

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    private void redirect (String resource) throws IOException {
        this.indexController.redirect(resource);
    }

    public void onSubmit(ActionEvent actionEvent) {

        HashMap<Text, String> erreurs = new HashMap<>();

        errorNom.setText(null);
        errorPrenom.setText(null);
        errorEmail.setText(null);
        errorDateNaissance.setText(null);
        errorVille.setText(null);
        errorCodePostal.setText(null);
        errorAdressePostale.setText(null);
        errorPays.setText(null);
        errorVille.setText(null);
        errorMdp.setText(null);
        errorCompte.setText(null);

        if (txtNom.getText().isEmpty()) erreurs.put(errorNom, "Champs vide");
        if (txtPrenom.getText().isEmpty()) erreurs.put(errorPrenom, "Champs vide");
        if (txtEmail.getText().isEmpty()) erreurs.put(errorEmail, "Champs vide");
        if (txtDateNaissance.getValue() == null) erreurs.put(errorDateNaissance, "Champs vide");
        if (txtVille.getText().isEmpty()) erreurs.put(errorVille, "Champs vide");
        if (txtCodePostal.getText().isEmpty()) erreurs.put(errorCodePostal, "Champs vide");
        if (txtAdressePostale.getText().isEmpty()) erreurs.put(errorAdressePostale, "Champs vide");
        if (txtVille.getText().isEmpty()) erreurs.put(errorVille, "Champs vide");
        if (txtPays.getText().isEmpty()) erreurs.put(errorPays, "Champs vide");
        if (txtMdp.getText().isEmpty() || txtMdpConfirm.getText().isEmpty()) erreurs.put(errorMdp, "Champs vide");
        else if (!txtMdp.getText().equals(txtMdpConfirm.getText())) erreurs.put(errorMdp, "Champs non identique");

        erreurs.forEach(Text::setText);

        if (erreurs.isEmpty()) {

            Compte compte = new Compte();
            compte.setEmail(txtEmail.getText());
            compte.setMdp(txtMdp.getText());
            compte.setRole(Role.MEMBRE);

            CompteDao compteDao = new CompteDao();
            if (!compteDao.findByEmail(compte.getEmail())) {
                compteDao.create(compte);
                Membre membre = new Membre(compte);
                membre.setNom(txtNom.getText());
                membre.setPrenom(txtPrenom.getText());
                membre.setEmail(txtEmail.getText());
                membre.setDateNaissance(Date.valueOf(txtDateNaissance.getValue()));
                membre.setCodePostal(txtCodePostal.getText());
                membre.setAdressePostale(txtAdressePostale.getText());
                membre.setVille(txtVille.getText());
                membre.setPays(txtPays.getText());
                MembreDao membreDao = new MembreDao();
                membreDao.create(membre);

                try {
                    this.indexController.redirect("connexion");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*
                try {

                    Node node = (Node) actionEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("index.fxml")));
                    stage.setScene(scene);
                    stage.show();


                    Node node = (Node) actionEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("index.fxml"));
                    Parent root = (Parent) loader.load();

                    IndexController indexController = loader.getController();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                stage = (Stage) scenePane.getScene().getWindow();
                System.out.println("Inscription validée");
                stage.close();

                 */

            } else {
                errorCompte.setText("Le compte existe déja");
            }
        }


    }

    public void btnInscriptionOnAction(ActionEvent actionEvent) {
    }

    public void btnConnexionOnAction(ActionEvent actionEvent) {

    }
}
