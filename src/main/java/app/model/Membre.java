package app.model;

import app.dao.ArticleDao;
import app.dao.VoteDao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Membre {

    private long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adressePostale;
    private String codePostal;
    private String ville;
    private String pays;
    private Compte compte;

    public Membre() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Membre(Compte compte) {
        this.compte = compte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

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
}
