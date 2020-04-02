package sample.espais;

import javafx.beans.property.SimpleStringProperty;

public class TaulaPlanta {

    private final SimpleStringProperty planta = new SimpleStringProperty("");
    private final SimpleStringProperty superficie = new SimpleStringProperty("");
    private final SimpleStringProperty sales = new SimpleStringProperty("");
    private final SimpleStringProperty habitacions = new SimpleStringProperty("");


    public TaulaPlanta(String p, String s, String sa, String h) {
        planta.set(p);
        superficie.set(s);
        sales.set(sa);
        habitacions.set(h);

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

    public String getSuperficie() {
        return superficie.get();
    }

    public SimpleStringProperty superficieProperty() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie.set(superficie);
    }

    public String getSales() {
        return sales.get();
    }

    public SimpleStringProperty salesProperty() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales.set(sales);
    }

    public String getHabitacions() {
        return habitacions.get();
    }

    public SimpleStringProperty habitacionsProperty() {
        return habitacions;
    }

    public void setHabitacions(String habitacions) {
        this.habitacions.set(habitacions);
    }
}
