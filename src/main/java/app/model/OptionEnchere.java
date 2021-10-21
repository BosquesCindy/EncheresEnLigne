package app.model;

import javafx.beans.property.*;

public class OptionEnchere {

    private LongProperty id;
    private StringProperty libelle;
    private FloatProperty prixCatalogue;
    private FloatProperty prixGold;

    public OptionEnchere(){
        this.id = new SimpleLongProperty();
        this.libelle = new SimpleStringProperty();
        this.prixCatalogue = new SimpleFloatProperty();
        this.prixGold = new SimpleFloatProperty();
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

    public float getPrixCatalogue() {
        return prixCatalogue.get();
    }

    public FloatProperty prixCatalogueProperty() {
        return prixCatalogue;
    }

    public void setPrixCatalogue(float prixCatalogue) {
        this.prixCatalogue.set(prixCatalogue);
    }

    public float getPrixGold() {
        return prixGold.get();
    }

    public FloatProperty prixGoldProperty() {
        return prixGold;
    }

    public void setPrixGold(float prixGold) {
        this.prixGold.set(prixGold);
    }
}
