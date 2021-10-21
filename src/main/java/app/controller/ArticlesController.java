package app.controller;

import app.Main;
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
    /*
    private void creerArticle(){
        this.indexController.showPopop("article-creer");
    }

    private void encherirArticle(){
        this.indexController.showPopop("article-encherir");
    }
*/
    private void showPopop(String resource){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("view/article-creer.fxml")));
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/" + resource + ".fxml"));
            //Parent root = (Parent) loader.load();
            /*
            switch (resource) {
                case "article-creer" -> ((CreerArticleController) loader.getController()).setArticlesController(this);
                case "article-encherir" -> ((EncherirArticleController) loader.getController()).setArticlesController(this);
            }

             */
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
        dataArticle = FXCollections.observableArrayList();
        articles.forEach(m -> {
            dataArticle.add(m);
        });
        tc1Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tc1DateCreation.setCellValueFactory(new PropertyValueFactory<>("dateHeureCreation"));
        tc1Categorie.setCellValueFactory(data -> data.getValue().getCategorie().libelleProperty());
        tc1Vendeur.setCellValueFactory(data -> data.getValue().getVendeur().nomProperty());
        tc1BtnDetail.setCellValueFactory(new PropertyValueFactory<Article, String>("button"));
        tvTousArticle.setItems(dataArticle);
    }


}
