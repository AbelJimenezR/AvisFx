package sample.iaios;

import javafx.beans.property.SimpleStringProperty;

public class TaulaIaios {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty nom = new SimpleStringProperty("");
    private final SimpleStringProperty edat = new SimpleStringProperty("");
    private final SimpleStringProperty incapacitat = new SimpleStringProperty("");


    public TaulaIaios(String i, String n, String e, String in) {
        id.set(i);
        nom.set(n);
        edat.set(e);
        incapacitat.set(in);
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getEdat() {
        return edat.get();
    }

    public void setEdat(String e) {
        edat.set(e);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String n) {
        nom.set(n);
    }

    public String getIncapacitat() {
        return incapacitat.get();
    }

    public void getIncapacitat(String in) {
        incapacitat.set(in);
    }

}
