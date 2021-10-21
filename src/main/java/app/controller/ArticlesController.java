package app.controller;

import app.Main;
import app.controller.popup.DetailArticleController;
import app.dao.ArticleDao;
import app.dao.CategorieDao;
import app.model.Article;
import app.model.Categorie;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class ArticlesController implements Initializable {

    public TableView tvTousArticle;
    public TableView tvMesArticle;
    public TableColumn<Article, String> tc1Titre;
    public TableColumn<Article, String> tc1Vendeur;
    public TableColumn<Article, String> tc1Categorie;
    public TableColumn<Article, Timestamp> tc1DateCreation;
    public TableColumn<Article, String> tc1BtnDetail;
    public ComboBox cbxCategorie;
    public JFXButton btnRechercher;
    public Tab tabTousArticles;
    public Tab tabMesArticles;
    public TabPane tabPanelArticle;
    public JFXButton btnCreerArticle;
    private ObservableList<Article> dataArticle;
    private ObservableList<Categorie> dataCategorie;
    private IndexController indexController;
    private HashMap<Button, Article> hashMapBtnArticle;

    private void manageArticle(){
        if (indexController.getCompte() == null) {
            tabPanelArticle.getTabs().remove(tabMesArticles);
        }
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //manageArticle();
        dataCategorie = FXCollections.observableArrayList();

        CategorieDao categorieDao = new CategorieDao();
        categorieDao.findAll().forEach(categorie -> {

            dataCategorie.add(categorie);
        });

        // load Articles | Tous
        ArticleDao articleDao = new ArticleDao();
        loadTableViewTousArticle(articleDao.findAll());

        // load Article | Mes articles

        cbxCategorie.setItems(dataCategorie);
    }


    public void handleClicks(ActionEvent actionEvent) {

        if (btnRechercher.equals(actionEvent.getSource())) {
            rechercher();
        } else if (btnCreerArticle.equals(actionEvent.getSource())){
            this.indexController.showPopop("article-creer");
        }
    }

    private void rechercher() {
        if (!cbxCategorie.getSelectionModel().isEmpty()){
            Categorie categorie = (Categorie) cbxCategorie.getSelectionModel().getSelectedItem();
            ArticleDao articleDao = new ArticleDao();
            ArrayList<Article> articles = articleDao.findByCategorie(categorie.getId());
            loadTableViewTousArticle(articles);
        }
    }

    private void loadTableViewTousArticle(ArrayList<Article> articles) {
        hashMapBtnArticle = new HashMap<>();

        dataArticle = FXCollections.observableArrayList();
        articles.forEach(article -> {
            article.getButton().setOnAction(this::handleButtonAction);
            dataArticle.add(article);
            hashMapBtnArticle.put(article.getButton(), article);
        });

        tc1Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tc1DateCreation.setCellValueFactory(new PropertyValueFactory<>("dateHeureCreation"));
        tc1Categorie.setCellValueFactory(data -> data.getValue().getCategorie().libelleProperty());
        tc1Vendeur.setCellValueFactory(data -> data.getValue().getVendeur().nomProperty());
        tc1BtnDetail.setCellValueFactory(new PropertyValueFactory<Article, String>("button"));
        tvTousArticle.setItems(dataArticle);

    }

    private void handleButtonAction(ActionEvent actionEvent){
        try {
            Article article = hashMapBtnArticle.get(actionEvent.getSource());
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/popup/article-detail.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            DetailArticleController detailArticleController = (DetailArticleController) loader.getController();
            detailArticleController.setIndexController(this.indexController);
            detailArticleController.setStage(stage);
            detailArticleController.setArticle(article);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showDetail(Article article){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/popup/article-detail.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
