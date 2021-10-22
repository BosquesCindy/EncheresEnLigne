package app.controller.popup;

import app.controller.ArticlesController;
import app.controller.IndexController;
import app.dao.EnchereDao;
import app.dao.MembreDao;
import app.model.Article;
import app.model.Enchere;
import app.model.Membre;
import app.model.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetailArticleController implements Initializable {

    @FXML
    public Button btnAnnulerEnchere;

    @FXML
    public Button btnAvis;

    @FXML
    public Button btnEnchere;

    @FXML
    public Button btnRetirer;

    @FXML
    public Button btnSignaler;

    @FXML
    public Text pas;

    @FXML
    public TextField nouvellePas;

    @FXML
    public Label nomArticle;

    @FXML
    public Text prixDepart;

    @FXML
    public Text DerniereEncheres;

    @FXML
    public TextArea descriptionArticle;

    @FXML
    public Pane paneEnchere;

    public TableView tvUnArticle;
    public TableColumn<Enchere, Long> tcNumero;
    public TableColumn<Enchere, String> tcMembre;
    public TableColumn<Enchere, Float> tcMontant;
    public TableColumn<Enchere, Date> tcDate;
    private ObservableList<Enchere> dataEnchere;

    private IndexController indexController;
    private Stage stage;
    private Article article;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    public DetailArticleController(){
        System.out.println("hello");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void controlAccess() {

    }

    public void initStage(){
        nomArticle.setText(article.getTitre());
        descriptionArticle.setText(article.getDescription());
        pas.setText(String.valueOf(article.getPrixDepart()*0.1));

        EnchereDao enchereDao = new EnchereDao();
        if(enchereDao.findDernierEnchere(article)==null){
            DerniereEncheres.setText(String.valueOf(article.getPrixDepart()));
        }else{
            DerniereEncheres.setText(String.valueOf(enchereDao.findDernierEnchere(article).getMontant()));
        }
        /*
        btnAnnulerEnchere.setVisible(false);
        paneEnchere.setVisible(true);
        btnAvis.setVisible(false);
        btnAnnulerEnchere.setVisible(false);
        btnSignaler.setVisible(false);
        */
        /*
        MembreDao md=new MembreDao();
        if (md.findByCompte(this.indexController.getCompte()).equals(article.getVendeur())){
            btnAnnulerEnchere.setVisible(true);
            paneEnchere.setVisible(false);
            btnAvis.setVisible(true);
        } else if (md.findByCompte(this.indexController.getCompte()).equals(article.getAcheteur())){
            btnAvis.setVisible(true);
        }

        if(new Date(System.currentTimeMillis()).getTime()>article.getDateCloture().getTime()){
            btnEnchere.setVisible(false);
        }else{
            btnAvis.setVisible(false);
        }

        */
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcMembre.setCellValueFactory(dataEnchere -> dataEnchere.getValue().getMembre().nomProperty());
        tcMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        dataEnchere = FXCollections.observableArrayList();
        ArrayList<Enchere> encheres = enchereDao.findByArticle(article);
        if (!encheres.isEmpty()) encheres.forEach(en -> {
            dataEnchere.add(en);
        });

        tvUnArticle.setItems(dataEnchere);

        btnSignaler.setVisible(false);
        if (this.indexController.getCompte().getRole().equals(Role.SERVICE_JURIDIQUE)){
            btnSignaler.setVisible(true);
        }

        btnRetirer.setVisible(false);
        if (this.indexController.getCompte().getRole().equals(Role.GESTIONNAIRE)){
            btnRetirer.setVisible(true);
        }


    }


    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    @FXML
    public void annulerEnchere(ActionEvent event) {
    }

    @FXML
    public void emettreAvis(ActionEvent event) {
        this.indexController.showPopop("article-avis");
    }

    @FXML
    public void encherir(ActionEvent event) {

        EnchereDao enchereDao = new EnchereDao();
        MembreDao membreDao = new MembreDao();
        Membre membre = membreDao.findByCompte(this.indexController.getCompte());
        Enchere enchere = new Enchere();
        enchere.setArticle(article);
        enchere.setMembre(membre);
        enchere.setMontant(Float.parseFloat(nouvellePas.getText()));
        enchere.setDate(new Date(System.currentTimeMillis()));
        enchereDao.create(enchere);
        initStage();
        /*
        if(nouvellePas.getText().isEmpty()){
            en.setMontant(Float.parseFloat(DerniereEncheres.getText()));
        }else{
            en.setMontant(Float.parseFloat(DerniereEncheres.getText())+Float.parseFloat(nouvellePas.getText()));
        }
        enDao.create(en);

         */
    }

    @FXML
    public void retirerArticle(ActionEvent event) {
        this.indexController.showPopop("article-retirer");
    }

    @FXML
    public void signalerArticle(ActionEvent event) {
        this.indexController.showPopop("article-signaler");
    }

}
