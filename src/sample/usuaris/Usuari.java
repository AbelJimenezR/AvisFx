package sample.usuaris;

public class Usuari {

    private String nom;
    private String password;
    private int id;

    public Usuari(int id, String nom, String password) {
        this.nom = nom;
        this.password = password;
        this.id = id;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
