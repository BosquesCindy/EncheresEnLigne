package app.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.Timestamp;

public class Article {

    protected SimpleLongProperty id;
    protected SimpleStringProperty titre;
    protected SimpleStringProperty description;
    protected SimpleFloatProperty fraisPort;
    protected SimpleStringProperty regionLivraison;
    protected SimpleObjectProperty<Timestamp> dateHeureCreation;
    protected SimpleObjectProperty<Date> dateCloture;
    protected SimpleStringProperty modeCloture;
    protected SimpleFloatProperty montantVente;
    protected SimpleFloatProperty prixDepart;
    protected SimpleFloatProperty prixReserve;
    protected SimpleFloatProperty prixAchatImmediat;
    protected SimpleObjectProperty<Categorie> categorie;
    protected SimpleObjectProperty<Membre> vendeur;
    protected SimpleObjectProperty<Membre> acheteur;
    protected SimpleObjectProperty<OptionEnchere> option;
    private Button button;

    public Article(){
        this.id = new SimpleLongProperty();
        this.titre = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.fraisPort = new SimpleFloatProperty();
        this.regionLivraison = new SimpleStringProperty();
        this.dateHeureCreation = new SimpleObjectProperty<Timestamp>();
        this.dateCloture = new SimpleObjectProperty<>();
        this.modeCloture = new SimpleStringProperty();
        this.montantVente = new SimpleFloatProperty();
        this.prixDepart = new SimpleFloatProperty();
        this.prixReserve = new SimpleFloatProperty();
        this.prixAchatImmediat = new SimpleFloatProperty();
        this.categorie = new SimpleObjectProperty<Categorie>();
        this.vendeur = new SimpleObjectProperty<Membre>();
        this.acheteur = new SimpleObjectProperty<Membre>();
        this.option = new SimpleObjectProperty<OptionEnchere>();
        this.button = new Button("Détail");
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getTitre() {
        return titre.get();
    }

    public SimpleStringProperty titreProperty() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public float getFraisPort() {
        return fraisPort.get();
    }

    public SimpleFloatProperty fraisPortProperty() {
        return fraisPort;
    }

    public void setFraisPort(float fraisPort) {
        this.fraisPort.set(fraisPort);
    }

    public String getRegionLivraison() {
        return regionLivraison.get();
    }

    public SimpleStringProperty regionLivraisonProperty() {
        return regionLivraison;
    }

    public void setRegionLivraison(String regionLivraison) {
        this.regionLivraison.set(regionLivraison);
    }

    public Timestamp getDateHeureCreation() {
        return dateHeureCreation.get();
    }

    public SimpleObjectProperty<Timestamp> dateHeureCreationProperty() {
        return dateHeureCreation;
    }

    public void setDateHeureCreation(Timestamp dateHeureCreation) {
        this.dateHeureCreation.set(dateHeureCreation);
    }

    public Date getDateCloture() {
        return dateCloture.get();
    }

    public SimpleObjectProperty<Date> dateClotureProperty() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture.set(dateCloture);
    }

    public String getModeCloture() {
        return modeCloture.get();
    }

    public SimpleStringProperty modeClotureProperty() {
        return modeCloture;
    }

    public void setModeCloture(String modeCloture) {
        this.modeCloture.set(modeCloture);
    }

    public float getMontantVente() {
        return montantVente.get();
    }

    public SimpleFloatProperty montantVenteProperty() {
        return montantVente;
    }

    public void setMontantVente(float montantVente) {
        this.montantVente.set(montantVente);
    }

    public float getPrixDepart() {
        return prixDepart.get();
    }

    public SimpleFloatProperty prixDepartProperty() {
        return prixDepart;
    }

    public void setPrixDepart(float prixDepart) {
        this.prixDepart.set(prixDepart);
    }

    public float getPrixReserve() {
        return prixReserve.get();
    }

    public SimpleFloatProperty prixReserveProperty() {
        return prixReserve;
    }

    public void setPrixReserve(float prixReserve) {
        this.prixReserve.set(prixReserve);
    }

    public float getPrixAchatImmediat() {
        return prixAchatImmediat.get();
    }

    public SimpleFloatProperty prixAchatImmediatProperty() {
        return prixAchatImmediat;
    }

    public void setPrixAchatImmediat(float prixAchatImmediat) {
        this.prixAchatImmediat.set(prixAchatImmediat);
    }

    public Categorie getCategorie() {
        return categorie.get();
    }

    public SimpleObjectProperty<Categorie> categorieProperty() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie.set(categorie);
    }

    public Membre getVendeur() {
        return vendeur.get();
    }

    public SimpleObjectProperty<Membre> vendeurProperty() {
        return vendeur;
    }

    public void setVendeur(Membre vendeur) {
        this.vendeur.set(vendeur);
    }

    public Membre getAcheteur() {
        return acheteur.get();
    }

    public SimpleObjectProperty<Membre> acheteurProperty() {
        return acheteur;
    }

    public void setAcheteur(Membre acheteur) {
        this.acheteur.set(acheteur);
    }

    public OptionEnchere getOption() {
        return option.get();
    }

    public SimpleObjectProperty<OptionEnchere> optionProperty() {
        return option;
    }

    public void setOption(OptionEnchere option) {
        this.option.set(option);
    }




}
