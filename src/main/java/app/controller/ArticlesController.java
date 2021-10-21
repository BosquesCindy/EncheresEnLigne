package app.controller;

import app.dao.ArticleDao;
import app.dao.MembreDao;
import app.model.Article;
import app.model.Membre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ArticlesController implements Initializable {

    public TableView tvTousArticle;
    public TableView tvMesArticle;
    public TableColumn<Article, String> tc1Titre;
    public TableColumn<Article, String> tc1Vendeur;
    public TableColumn<Article, String> tc1Categorie;
    public TableColumn<Article, Timestamp> tc1DateCreation;
    public TableColumn tc1BtnDetail;
    private ObservableList<Article> data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        data = FXCollections.observableArrayList();

        tc1Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tc1DateCreation.setCellValueFactory(new PropertyValueFactory<>("dateHeureCreation"));
        tc1Categorie.setCellValueFactory(data -> data.getValue().getCategorie().libelleProperty());
        tc1Vendeur.setCellValueFactory(data -> data.getValue().getVendeur().nomProperty());

        ArticleDao articleDao = new ArticleDao();
        ArrayList<Article> articles = articleDao.findAll();
        articles.forEach(m -> {
            data.add(m);
        });

        tvTousArticle.setItems(data);
    }
}
