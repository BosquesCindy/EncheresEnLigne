package app.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;

import java.sql.Date;

public class Enchere {

    protected LongProperty id;
    protected FloatProperty montant;
    protected ObjectProperty<Date> date;
    protected ObjectProperty<Article> article;
    protected ObjectProperty<Membre> membre;

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public float getMontant() {
        return montant.get();
    }

    public FloatProperty montantProperty() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant.set(montant);
    }

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public Article getArticle() {
        return article.get();
    }

    public ObjectProperty<Article> articleProperty() {
        return article;
    }

    public void setArticle(Article article) {
        this.article.set(article);
    }

    public Membre getMembre() {
        return membre.get();
    }

    public ObjectProperty<Membre> membreProperty() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre.set(membre);
    }
}
