package app.controller.popup;

import app.controller.IndexController;
import app.dao.ArticleDao;
import app.dao.CategorieDao;
import app.dao.MembreDao;
import app.model.Article;
import app.model.Categorie;
import app.model.Membre;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreerArticleController implements Initializable {

    private IndexController indexController;
    private Stage stage;

    @FXML
    public Button btnAnnuler;

    @FXML
    public Button btnSubmit;

    @FXML
    public Text errorCategorie;

    @FXML
    public Text errorDateCloture;

    @FXML
    public Text errorDescription;

    @FXML
    public Text errorFraisPort;

    @FXML
    public Text errorPrixDepart;

    @FXML
    public Text errorPrixImmediat;

    @FXML
    public Text errorPrixReserve;

    @FXML
    public Text errorRegion;

    @FXML
    public Text errorTitre;

    @FXML
    public ComboBox txtCategorie;

    @FXML
    public DatePicker txtDateCloture;

    @FXML
    public TextField txtDescription;

    @FXML
    public TextField txtPrixDepart;

    @FXML
    public TextField txtFraisPort;

    @FXML
    public TextField txtPrixImmediat;

    @FXML
    public TextField txtPrixReserve;

    @FXML
    public TextField txtRegionLivraison;


    @FXML
    public TextField txtTitre;


    public ObservableList dataCategorie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCategories();
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onSubmit(ActionEvent event) {

        HashMap<Text,String> erreurs=new HashMap<>();

        errorTitre.setText(null);
        errorDescription.setText(null);
        errorCategorie.setText(null);
        errorPrixDepart.setText(null);
        errorFraisPort.setText(null);
        errorDateCloture.setText(null);
        errorPrixReserve.setText(null);
        errorPrixImmediat.setText(null);
        errorRegion.setText(null);

        if (txtTitre.getText().isEmpty()) erreurs.put(errorTitre, "Champs vide");
        if (txtDescription.getText().isEmpty()) erreurs.put(errorDescription, "Champs vide");
        if (txtCategorie.getValue() == null) erreurs.put(errorCategorie, "Champs vide");
        if (txtDateCloture.getValue() == null) erreurs.put(errorDateCloture, "Champs vide");
        if (txtPrixDepart.getText().isEmpty()) erreurs.put(errorPrixDepart, "Champs vide");
//        if (txtPrixReserve.getText().isEmpty()) erreurs.put(errorPrixReserve, "Champs vide");
//        if (txtPrixImmediat.getText().isEmpty()) erreurs.put(errorPrixImmediat, "Champs vide");
        if (txtFraisPort.getText().isEmpty()) erreurs.put(errorFraisPort, "Champs vide");
        if (txtDateCloture.getValue()==null) erreurs.put(errorDateCloture, "Champs vide");
        if (txtCategorie.getSelectionModel().isEmpty()) erreurs.put(errorCategorie, "champs vide");
        erreurs.forEach(Text::setText);

        if (erreurs.isEmpty()){

            // Membre
            MembreDao membreDao = new MembreDao();
            Membre membre = null;
            membre = membreDao.findByCompte(this.indexController.getCompte());

            // Categorie
            Categorie categorie = (Categorie) txtCategorie.getSelectionModel().getSelectedItem();

            // Article
            Article article = new Article();
            article.setTitre(txtTitre.getText());
            article.setDescription(txtDescription.getText());
            article.setDateCloture(Date.valueOf(txtDateCloture.getValue()));
            article.setPrixDepart(Float.parseFloat(txtPrixDepart.getText()));
            article.setRegionLivraison(txtRegionLivraison.getText());
            article.setFraisPort(Float.parseFloat(txtFraisPort.getText()));
            article.setVendeur(membre);
            article.setCategorie(categorie);

            // ArticleDao
            ArticleDao articleDao=new ArticleDao();
            articleDao.create(article);


            try {
                this.indexController.redirect("articles");
                this.stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
            if (membre.isMembrePlus()) {
                if (!txtPrixReserve.getText().isEmpty()
            }
            if (!txtPrixReserve.getText().isEmpty()&&membre.getMembre_plus()==1){
                article.setPrixReserve(Float.parseFloat(txtPrixReserve.getText()));
            }else{
                article.setPrixReserve(0);
            }
            if (!txtPrixImmediat.getText().isEmpty()&&membre.getMembre_plus()==1){
                article.setPrixReserve(Float.parseFloat(txtPrixImmediat.getText()));
            }else{
                article.setPrixAchatImmediat(0);
            }
            */

        }

    }

    public void annuler(ActionEvent actionEvent) {
        this.stage.close();
    }


    private  void loadCategories() {
        ObservableList<Categorie> observableList = FXCollections.observableArrayList();
        CategorieDao categorieDao = new CategorieDao();
        categorieDao.findAll().forEach(categorie -> {
            observableList.add(categorie);
        });
        txtCategorie.setItems(observableList);
    }


}


