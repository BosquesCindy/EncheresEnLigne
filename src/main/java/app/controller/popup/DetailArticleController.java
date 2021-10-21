package app.controller.popup;

import app.controller.ArticlesController;
import app.controller.IndexController;
import app.model.Article;
import javafx.stage.Stage;

public class DetailArticleController {

    private IndexController indexController;
    private Stage stage;
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

}
