package app.model;

public class Categorie {
    private long id;
    private String libelle;

    public Categorie(long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

}
