package sample.espais;

import javafx.beans.property.SimpleStringProperty;

public class TaulaEspais {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty nom = new SimpleStringProperty("");
    private final SimpleStringProperty tipus = new SimpleStringProperty("");
    private final SimpleStringProperty plantes = new SimpleStringProperty("");
    private final SimpleStringProperty adreca = new SimpleStringProperty("");
    private final SimpleStringProperty accessibilitat = new SimpleStringProperty("");
    private final SimpleStringProperty places = new SimpleStringProperty("");
    private final SimpleStringProperty disponibilitat = new SimpleStringProperty("");


    public TaulaEspais(String i, String n, String t, String p,String a,String acces,String pl,String d) {
        id.set(i);
        nom.set(n);
        tipus.set(t);
        plantes.set(p);
        adreca.set(a);
        accessibilitat.set(acces);
        places.set(pl);
        disponibilitat.set(d);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getTipus() {
        return tipus.get();
    }

    public SimpleStringProperty tipusProperty() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus.set(tipus);
    }

    public String getPlantes() {
        return plantes.get();
    }

    public SimpleStringProperty plantesProperty() {
        return plantes;
    }

    public void setPlantes(String plantes) {
        this.plantes.set(plantes);
    }

    public String getAdreca() {
        return adreca.get();
    }

    public SimpleStringProperty adrecaProperty() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca.set(adreca);
    }

    public String getAccessibilitat() {
        return accessibilitat.get();
    }

    public SimpleStringProperty accessibilitatProperty() {
        return accessibilitat;
    }

    public void setAccessibilitat(String accessibilitat) {
        this.accessibilitat.set(accessibilitat);
    }

    public String getPlaces() {
        return places.get();
    }

    public SimpleStringProperty placesProperty() {
        return places;
    }

    public void setPlaces(String places) {
        this.places.set(places);
    }

    public String getDisponibilitat() {
        return disponibilitat.get();
    }

    public SimpleStringProperty disponibilitatProperty() {
        return disponibilitat;
    }

    public void setDisponibilitat(String disponibilitat) {
        this.disponibilitat.set(disponibilitat);
    }
}
