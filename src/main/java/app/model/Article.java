package app.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Article {
    protected long id;
    protected String titre;
    protected String description;
    protected float fraisPort;
    protected String regionLivraison;
    protected Timestamp dateHeureCreation;
    protected Date dateCloture;
    protected String modeCloture;
    protected float montantVente;
    protected float prixDepart;
    protected float prixReserve;
    protected float prixAchatImmediat;
    protected Categorie categorie;
    protected Membre vendeur;
    protected Membre acheteur;
    protected OptionEnchere option;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getFraisPort() {
        return fraisPort;
    }

    public void setFraisPort(float fraisPort) {
        this.fraisPort = fraisPort;
    }

    public String getRegionLivraison() {
        return regionLivraison;
    }

    public void setRegionLivraison(String regionLivraison) {
        this.regionLivraison = regionLivraison;
    }

    public Timestamp getDateHeureCreation() {
        return dateHeureCreation;
    }

    public void setDateHeureCreation(Timestamp dateHeureCreation) {
        this.dateHeureCreation = dateHeureCreation;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getModeCloture() {
        return modeCloture;
    }

    public void setModeCloture(String modeCloture) {
        this.modeCloture = modeCloture;
    }

    public float getMontantVente() {
        return montantVente;
    }

    public void setMontantVente(float montantVente) {
        this.montantVente = montantVente;
    }

    public float getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(float prixDepart) {
        this.prixDepart = prixDepart;
    }

    public float getPrixReserve() {
        return prixReserve;
    }

    public void setPrixReserve(float prixReserve) {
        this.prixReserve = prixReserve;
    }

    public float getPrixAchatImmediat() {
        return prixAchatImmediat;
    }

    public void setPrixAchatImmediat(float prixAchatImmediat) {
        this.prixAchatImmediat = prixAchatImmediat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Membre getVendeur() {
        return vendeur;
    }

    public void setVendeur(Membre vendeur) {
        this.vendeur = vendeur;
    }

    public Membre getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Membre acheteur) {
        this.acheteur = acheteur;
    }

    public OptionEnchere getOption() {
        return option;
    }

    public void setOption(OptionEnchere option) {
        this.option = option;
    }
}
