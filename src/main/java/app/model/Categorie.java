package app.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categorie {

    private LongProperty id;
    private StringProperty libelle;

    public Categorie(){
        this.id = new SimpleLongProperty();
        this.libelle = new SimpleStringProperty();
    }

    public Categorie(long id, String libelle) {
        this.id = new SimpleLongProperty(id);
        this.libelle = new SimpleStringProperty(libelle);
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

    public String getLibelle() {
        return libelle.get();
    }

    public StringProperty libelleProperty() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle.set(libelle);
    }

    @Override
    public String toString() {
        return this.libelle.get();
    }
}
