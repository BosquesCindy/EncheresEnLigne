package app.controller;

import app.Main;
import app.controller.popup.*;
import app.dao.MembreDao;
import app.model.Compte;
import app.model.Membre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IndexController implements Initializable {

    public Label lblUser;
    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnArticles;

    @FXML
    private Button btnConnexion;

    @FXML
    private Button btnInscription;

    @FXML
    private Button btnMembres;

    @FXML
    private Button btnDeconnexion;

    @FXML
    private Label exit;

    @FXML
    private StackPane contentArea;

    private Compte compte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exit.setOnMouseClicked(event -> System.exit(0));

        try {
            manageCompte();
            redirect("accueil");
        } catch (IOException e) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void manageCompte(){
        // Visiteur
        if (this.compte == null) {
            btnConnexion.setVisible(true);
            btnInscription.setVisible(true);
            btnMembres.setVisible(false);
            btnDeconnexion.setVisible(false);
        } else {
            btnMembres.setVisible(true);
            btnDeconnexion.setVisible(true);
            btnConnexion.setVisible(false);
            btnInscription.setVisible(false);
            // Utilisateur
            switch (compte.getRole()) {
                case MEMBRE :
                    MembreDao membreDao = new MembreDao();
                    Membre membre = membreDao.findByCompte(compte);
                    lblUser.setText(membre.getPrenom() + " " + membre.getNom());
                    System.out.println("MEMBRE");
                    break;
                case SERVICE_COMMERCIAL:
                    lblUser.setText(compte.getRole().toString());
                    System.out.println("SERVICE_COMMERCIAL");
                    break;
                case SERVICE_JURIDIQUE:
                    lblUser.setText(compte.getRole().toString());
                    System.out.println("SERVICE_JURIDIQUE");
                    break;
                case GESTIONNAIRE:
                    lblUser.setText(compte.getRole().toString());
                    System.out.println("GESTIONNAIRE");
                    break;
                default:
                    break;
            }
        }
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public void redirect(String resource) throws IOException {

        manageCompte();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/" + resource + ".fxml"));
        Parent root = (Parent) loader.load();

        switch (resource) {
            case "accueil" :
                AccueilController accueilController = loader.getController();
                accueilController.setIndexController(this);
                break;
            case "connexion" :
                ConnexionController connexionController = loader.getController();
                connexionController.setIndexController(this);
                break;
            case "inscription" :
                InscriptionController inscriptionController = loader.getController();
                inscriptionController.setIndexController(this);
                break;
            case "articles" :
                ArticlesController articlesController = loader.getController();
                articlesController.setIndexController(this);
                break;
            case "membres" :
                MembresController membresController = loader.getController();
                membresController.setIndexController(this);
                break;
            case "statistique" :
                StatistiqueController statistiqueController = loader.getController();
                statistiqueController.setIndexController(this);
                break;
            case "rentabilite" :
                RentabiliteController rentabiliteController = loader.getController();
                rentabiliteController.setIndexController(this);
                break;
            default :
                break;
        }

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (btnAccueil.equals(actionEvent.getSource())) {
            redirect("accueil");
        } else if (btnConnexion.equals(actionEvent.getSource())) {
            redirect("connexion");
        } else if (btnInscription.equals(actionEvent.getSource())) {
            redirect("inscription");
        } else if (btnArticles.equals(actionEvent.getSource())) {
            redirect("articles");
        } else if (btnMembres.equals(actionEvent.getSource())) {
            redirect("membres");
        } else if (btnDeconnexion.equals(actionEvent.getSource())) {
            setCompte(null);
            redirect("connexion");
        }
    }

    public void showPopop(String resource){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/popup/" + resource + ".fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            switch (resource) {
                case "article-creer" :
                    CreerArticleController creerArticleController = (CreerArticleController) loader.getController();
                    creerArticleController.setIndexController(this);
                    creerArticleController.setStage(stage);
                    break;
                case "article-detail" :
                    DetailArticleController detailArticleController = (DetailArticleController) loader.getController();
                    detailArticleController.setIndexController(this);
                    detailArticleController.setStage(stage);
                    break;
                default:
                    break;
            }

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
