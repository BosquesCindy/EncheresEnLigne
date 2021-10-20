package app.model;

public class ServiceJuridique {

    private long id;
    private Compte compte;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
