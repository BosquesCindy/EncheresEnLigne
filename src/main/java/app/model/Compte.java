package app.model;

import javafx.beans.property.*;

public class Compte {

    private LongProperty id;
    private StringProperty email;
    private StringProperty mdp;
    private ObjectProperty<Role> role;

    public Compte () {
        this.id = new SimpleLongProperty();
        this.email = new SimpleStringProperty();
        this.mdp = new SimpleStringProperty();
        this.role = new SimpleObjectProperty<>();
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getMdp() {
        return mdp.get();
    }

    public StringProperty mdpProperty() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp.set(mdp);
    }

    public Role getRole() {
        return role.get();
    }

    public ObjectProperty<Role> roleProperty() {
        return role;
    }

    public void setRole(Role role) {
        this.role.set(role);
    }
}
