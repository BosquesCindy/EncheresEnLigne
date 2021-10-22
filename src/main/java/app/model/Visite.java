package app.model;
import javafx.beans.property.*;
import java.sql.Date;
import java.sql.Timestamp;

public class Visite {
    protected LongProperty id;
    protected SimpleObjectProperty<Date> date;
    protected SimpleObjectProperty<Timestamp> heure;

    public Visite() {
        this.id = new SimpleLongProperty();
        this.date = new SimpleObjectProperty<>();
        this.heure = new SimpleObjectProperty<>();
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

    public Date getDate() {
        return date.get();
    }
    public ObjectProperty<Date> dateObjectProperty() {
        return date;
    }
    public void setDate(Date date) {
        this.date.set(date);
    }

    public Timestamp getHeure() {
        return heure.get();
    }
    public ObjectProperty<Timestamp> heureObjectProperty() {
        return heure;
    }
    public void setHeure(Timestamp heure) {
        this.heure.set(heure);
    }

}

