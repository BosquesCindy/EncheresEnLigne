package app.model;

import javafx.beans.property.*;

import java.sql.Date;

public class Membre {

    private LongProperty id;
    private StringProperty nom;
    private StringProperty prenom;
    private ObjectProperty<Date> dateNaissance;
    private StringProperty adressePostale;
    private StringProperty codePostal;
    private StringProperty ville;
    private StringProperty pays;
    private BooleanProperty membrePlus;
    private ObjectProperty<Compte> compte;

    public Membre() {
        this.id = new SimpleLongProperty();
        this.nom = new SimpleStringProperty();
        this.prenom = new SimpleStringProperty();
        this.dateNaissance = new SimpleObjectProperty<Date>();
        this.adressePostale = new SimpleStringProperty();
        this.codePostal = new SimpleStringProperty();
        this.ville = new SimpleStringProperty();
        this.pays = new SimpleStringProperty();
        this.membrePlus = new SimpleBooleanProperty();
        this.compte = new SimpleObjectProperty<Compte>();
    }

    public boolean isMembrePlus() {
        return membrePlus.get();
    }

    public BooleanProperty membrePlusProperty() {
        return membrePlus;
    }

    public void setMembrePlus(boolean membrePlus) {
        this.membrePlus.set(membrePlus);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public Date getDateNaissance() {
        return dateNaissance.get();
    }

    public ObjectProperty<Date> dateNaissanceProperty() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance.set(dateNaissance);
    }

    public String getAdressePostale() {
        return adressePostale.get();
    }

    public StringProperty adressePostaleProperty() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale.set(adressePostale);
    }

    public String getCodePostal() {
        return codePostal.get();
    }

    public StringProperty codePostalProperty() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal.set(codePostal);
    }

    public String getVille() {
        return ville.get();
    }

    public StringProperty villeProperty() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    public String getPays() {
        return pays.get();
    }

    public StringProperty paysProperty() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays.set(pays);
    }

    public Compte getCompte() {
        return compte.get();
    }

    public ObjectProperty<Compte> compteProperty() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte.set(compte);
    }

    /*
    public boolean avoirPrixGold(){
        VoteDao voteDao=new VoteDao();
        ArrayList<Vote> votesVendeur=voteDao.findByTypeMembre(this,TypeMembre.VENDEUR);
        ArticleDao articleDao=new ArticleDao();
        ArrayList<Article> articlesVendeur=articleDao.findByVendeur(this);
        int countAvisPositives=0;
        int countArticlesCetteAnnee=0;
        for(int i=0;i<votesVendeur.size();i++){
            switch (votesVendeur.get(i).getAvis()){
                case TRES_BON:
                    countAvisPositives++;
                case BON:
                    countAvisPositives++;
                case MOYEN:
                    countAvisPositives++;
            }
        }
        Timestamp today = new Timestamp(System.currentTimeMillis());
        DateFormat year = new SimpleDateFormat("yyyy");
        for(int j=0;j<articlesVendeur.size();j++){
            if(year.format(articlesVendeur.get(j).getDateHeureCreation())==year.format(today)){
                countArticlesCetteAnnee++;
            }
        }
        if(countAvisPositives/votesVendeur.size()>=0.8 &&countArticlesCetteAnnee>50){
            return true;
        }
        return false;
    }

     */


}
