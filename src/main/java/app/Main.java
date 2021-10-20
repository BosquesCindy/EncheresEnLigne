package app;

import app.model.*;
import app.dao.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

public class Main extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("index.fxml")));

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("css/styles.css").toExternalForm());

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        // Inscription();
        //Connexion();
    }

    public static void Inscription(){
        Compte compte = new Compte();
        compte.setEmail("r.monlouis@gmail.com");
        compte.setMdp("pwd");
        compte.setRole(Role.MEMBRE);

        CompteDao compteDao = new CompteDao();
        if (!compteDao.findByEmail(compte.getEmail())) {

            compteDao.create(compte);
            Membre membre = new Membre(compte);
            membre.setNom("Monlouis");
            membre.setPrenom("Ruddy");
            membre.setDateNaissance(Date.valueOf("1997-08-05"));
            membre.setCodePostal("31300");
            membre.setAdressePostale("58 Avenue Etienne Billières");
            membre.setVille("Toulouse");
            membre.setPays("France");
            MembreDao membreDao = new MembreDao();
            membreDao.create(membre);
        } else {
            System.out.println("Le compte existe déja");
        }

    }

    public static void Connexion(){

        String email = "r.monlouis@gmail.com";
        String mdp = "pwd";
        CompteDao compteDao = new CompteDao();
        Compte compte = compteDao.findByEmailPwd(email, mdp);

        if (compte != null) {
            switch (compte.getRole()) {
                case MEMBRE :
                    System.out.println("MEMBRE");
                    break;
                case SERVICE_COMMERCIAL:
                    System.out.println("SERVICE_COMMERCIAL");
                    break;
                case SERVICE_JURIDIQUE:
                    System.out.println("SERVICE_JURIDIQUE");
                    break;
                case GESTIONNAIRE:
                    System.out.println("GESTIONNAIRE");
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Utilisateur inconnue");
        }


    }

}