package app.model;

import javafx.beans.property.*;

public class Rentabilite {

    private SimpleLongProperty id;
    private SimpleFloatProperty repartitionPrevisionnelle;
    private SimpleIntegerProperty nombreMoyenInsertion;
    private SimpleIntegerProperty nombreMoyenVisite;
    private SimpleIntegerProperty dureeMoyenneEnchere;
    private SimpleIntegerProperty prixVenteMoyen;
    private SimpleFloatProperty tauxVente;
    private SimpleIntegerProperty chargePrevisionnelle;

    public Rentabilite(){ //Semaine Annee
        this.id = new SimpleLongProperty();
        this.repartitionPrevisionnelle = new SimpleFloatProperty();
        this.nombreMoyenInsertion = new SimpleIntegerProperty();
        this.nombreMoyenVisite = new SimpleIntegerProperty();
        this.dureeMoyenneEnchere = new SimpleIntegerProperty();
        this.prixVenteMoyen = new SimpleIntegerProperty();
        this.tauxVente = new SimpleFloatProperty();
        this.chargePrevisionnelle = new SimpleIntegerProperty();
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

    public float getRepaPrev() {
        return repartitionPrevisionnelle.get();
    }
    public SimpleFloatProperty RepaPrevProperty() {
        return repartitionPrevisionnelle;
    }
    public void setRepaPrev(float repartitionPrevisionnelle) {
        this.repartitionPrevisionnelle.set(repartitionPrevisionnelle);
    }

    public int getNbMoyInsertion() {
        return nombreMoyenInsertion.get();
    }
    public SimpleIntegerProperty NbMoyInsertionProperty() {
        return nombreMoyenInsertion;
    }
    public void setNbMoyInsertion(int nombreMoyenInsertion) {
        this.nombreMoyenInsertion.set(nombreMoyenInsertion);
    }

    public int getNbMoyVisite() {
        return nombreMoyenVisite.get();
    }
    public SimpleIntegerProperty NbMoyVisiteProperty() {
        return nombreMoyenVisite;
    }
    public void setNbMoyVisite(int nombreMoyenVisite) {
        this.nombreMoyenVisite.set(nombreMoyenVisite);
    }

    public int getDureeMoyEnchere() {
        return dureeMoyenneEnchere.get();
    }
    public SimpleIntegerProperty DureeMoyEnchereProperty() {
        return dureeMoyenneEnchere;
    }
    public void setDureeMoyEnchere(int dureeMoyenneEnchere) {
        this.dureeMoyenneEnchere.set(dureeMoyenneEnchere);
    }

    public int getPrixVenteMoy() {
        return prixVenteMoyen.get();
    }
    public SimpleIntegerProperty PrixVenteMoyProperty() {
        return prixVenteMoyen;
    }
    public void setPrixVenteMoy(int prixVenteMoyen) {
        this.prixVenteMoyen.set(prixVenteMoyen);
    }

    public float getTauxVenteR() {
        return tauxVente.get();
    }
    public SimpleFloatProperty TauxVenteRProperty() {
        return tauxVente;
    }
    public void setTauxVenteR(float tauxVente) {
        this.tauxVente.set(tauxVente);
    }

    public int getChargePrev() {
        return chargePrevisionnelle.get();
    }
    public SimpleIntegerProperty ChargePrevProperty() {
        return chargePrevisionnelle;
    }
    public void setChargePrev(int chargePrevisionnelle) {
        this.chargePrevisionnelle.set(chargePrevisionnelle);
    }

}

