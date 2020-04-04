package sample.historial;

import javafx.beans.property.SimpleStringProperty;

public class TaulaHistorial {

    private final SimpleStringProperty idEspai = new SimpleStringProperty("");
    private final SimpleStringProperty idPlanta = new SimpleStringProperty("");
    private final SimpleStringProperty idHabitacio = new SimpleStringProperty("");
	private final SimpleStringProperty nomIaio = new SimpleStringProperty("");
    private final SimpleStringProperty idIaio = new SimpleStringProperty("");
    private final SimpleStringProperty dataEntrada = new SimpleStringProperty("");
    private final SimpleStringProperty dataSortida = new SimpleStringProperty("");
    
    public TaulaHistorial(String iE, String iP, String iH, String nI, String iI, String dE, String dS) {
    	idEspai.set(iE);
    	idPlanta.set(iP);
    	idHabitacio.set(iH);
    	nomIaio.set(nI);
    	idIaio.set(iI);    
    	dataEntrada.set(dE);
    	dataSortida.set(dS);
    }
	
	public String getIdEspai() {
		return idEspai.get();
	}
	
    public void setIdEspai(String idEspai) {
        this.idEspai.set(idEspai);
    }
	
	public String getIdPlanta() {
		return idPlanta.get();
	}
	
    public void setIdPlanta(String idPlanta) {
        this.idPlanta.set(idPlanta);
    }
	
	public String getIdHabitacio() {
		return idHabitacio.get();
	}
	
    public void setIdHabitacio(String idHabitacio) {
        this.idHabitacio.set(idHabitacio);
    }
	
	public String getNomIaio() {
		return nomIaio.get();
	}
	
    public void setNomIaio(String nomIaio) {
        this.nomIaio.set(nomIaio);
    }
	
	public String getIdIaio() {
		return idIaio.get();
	}
	
    public void setIdIaio(String idIaio) {
        this.idIaio.set(idIaio);
    }
	
	public String getDataEntrada() {
		return dataEntrada.get();
	}
	
    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada.set(dataEntrada);
    }
	
	public String getDataSortida() {
		return dataSortida.get();
	}
	
    public void setDataSortida(String dataSortida) {
        this.dataSortida.set(dataSortida);
    }
}
