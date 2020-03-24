package sample.espais;

import javafx.beans.property.SimpleStringProperty;

public class TaulaHabitacio {

    private final SimpleStringProperty planta = new SimpleStringProperty("");
    private final SimpleStringProperty habitacio = new SimpleStringProperty("");
    private final SimpleStringProperty llits = new SimpleStringProperty("");


    public TaulaHabitacio(String p, String h, String l) {
        planta.set(p);
        habitacio.set(h);
        llits.set(l);
    }

    public String getPlanta() {
        return planta.get();
    }

    public SimpleStringProperty plantaProperty() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta.set(planta);
    }

    public String getHabitacio() {
        return habitacio.get();
    }

    public SimpleStringProperty habitacioProperty() {
        return habitacio;
    }

    public void setHabitacio(String habitacio) {
        this.habitacio.set(habitacio);
    }

    public String getLlits() {
        return llits.get();
    }

    public SimpleStringProperty llitsProperty() {
        return llits;
    }

    public void setLlits(String llits) {
        this.llits.set(llits);
    }
}
