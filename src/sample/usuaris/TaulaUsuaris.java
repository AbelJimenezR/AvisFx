package sample.usuaris;

import javafx.beans.property.SimpleStringProperty;

public class TaulaUsuaris {
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty nom = new SimpleStringProperty("");
    private final SimpleStringProperty pass = new SimpleStringProperty("");
    private final SimpleStringProperty tipus = new SimpleStringProperty("");

    public void setId(String id) {
        this.id.set(id);
    }

    public TaulaUsuaris(String i, String n, String p, String t) {
        id.set(i);
        nom.set(n);
        pass.set(p);
        tipus.set(t);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getTipus() {
        return tipus.get();
    }

    public void setTipus(String t) {
        tipus.set(t);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String v) {
        nom.set(v);
    }

    public String getPass() {
        return pass.get();
    }

    public void setPass(String v) {
        pass.set(v);
    }

}
