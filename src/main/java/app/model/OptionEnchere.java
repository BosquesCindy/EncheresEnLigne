package app.model;

public class OptionEnchere {

    private long id;
    private String libelle;
    private float prixCatalogue;
    private float prixGold;

//    public OptionEnchere(long id, String libelle, float prixCatalogue, float prixGold) {
//        this.id = id;
//        this.libelle = libelle;
//        this.prixCatalogue = prixCatalogue;
//        this.prixGold = prixGold;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPrixCatalogue() {
        return prixCatalogue;
    }

    public void setPrixCatalogue(float prixCatalogue) {
        this.prixCatalogue = prixCatalogue;
    }

    public float getPrixGold() {
        return prixGold;
    }

    public void setPrixGold(float prixGold) {
        this.prixGold = prixGold;
    }
}
