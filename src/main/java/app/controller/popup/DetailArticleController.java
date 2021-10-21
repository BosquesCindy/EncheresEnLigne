package app.controller.popup;

import app.controller.ArticlesController;
import app.controller.IndexController;
import javafx.stage.Stage;

public class DetailArticleController {

    private IndexController indexController;
    private Stage stage;

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
