package app.controller;

import app.dao.CompteDao;
import app.model.Compte;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;

public class ConnexionController {

    public TextField txtEmail;
    public PasswordField txtMdp;
    public Text erreurEmail;
    public Text erreurMdp;
    public Text erreurConnexion;
    private IndexController indexController;

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    private void redirect (String resource) throws IOException {
        this.indexController.redirect(resource);
    }

    public void onConnexion(ActionEvent actionEvent) {

        HashMap<Text, String> erreurs = new HashMap<>();

        erreurEmail.setText(null);
        erreurMdp.setText(null);
        erreurConnexion.setText(null);

        if (txtEmail.getText().isEmpty()) erreurs.put(erreurEmail, "Veuillez renseigner l'email");
        if (txtMdp.getText().isEmpty()) erreurs.put(erreurMdp, "Veuillez rensigner le mot de passe");

        erreurs.forEach(Text::setText);

        if (erreurs.isEmpty()) {
            CompteDao compteDao = new CompteDao();
            Compte compte = compteDao.findByEmailPwd(txtEmail.getText(), txtMdp.getText());
            if (compte != null) {
                try {
                    this.indexController.setCompte(compte);
                    this.indexController.redirect("accueil");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                erreurConnexion.setText("Email / Mot de passe invalide");
            }
        }


    }
}
