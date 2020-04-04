package sample.historial;

import javafx.beans.property.SimpleStringProperty;

public class TaulaDisponibilitat {

	 private final SimpleStringProperty idEspai = new SimpleStringProperty("");
	 private final SimpleStringProperty numPlaces = new SimpleStringProperty("");
	 
	 public TaulaDisponibilitat(String iE, String nP) {
	    	idEspai.set(iE);
	    	numPlaces.set(nP);
	 }
	 
	 public String getIdEspai() {
		return idEspai.get();
	 }
	
	 public void setIdEspai(String idEspai) {
	       this.idEspai.set(idEspai);
	 }
		
	public String getNumPlaces() {
		return numPlaces.get();
	}
		
	public void setNumPlaces(String numPlaces) {
	    this.numPlaces.set(numPlaces);
	}
}
