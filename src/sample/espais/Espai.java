package sample.espais;

import java.util.ArrayList;

public abstract class Espai {

	// atributs
	private int id;
	private int numPlantes;
	private String adreca;
	private boolean accessibilitat;
	private int numPlaces;
	private boolean disponibilitat;
	private ArrayList<Planta> plantes = new ArrayList<>();

	// constructor
	public Espai(int id, String adreca, boolean accessibilitat, boolean disponibilitat, int numPlantes) {
		this.id = id;
		this.numPlantes = plantes.size();
		this.adreca = adreca;
		this.accessibilitat = accessibilitat;
		this.disponibilitat = disponibilitat;
		this.numPlantes=numPlantes;
		for(int a=0;a<numPlantes;a++){
			plantes.add(new Planta());
		}



	}

	// getters
	public int getId() {
		return id;
	}

	public int getNumPlantes() {
		return numPlantes;
	}

	public String getAdreca() {
		return adreca;
	}

	public boolean getAccessibilitat() {
		return accessibilitat;
	}

	public int getNumPlaces() {
		return numPlaces;
	}

	public boolean getDisponibilitat() {
		return disponibilitat;
	}

	public ArrayList<Planta> getPlantes() {
		return plantes;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setNumPlantes() {
		this.numPlantes = this.plantes.size();
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	public void setAccessibilitat(boolean accessibilitat) {
		this.accessibilitat = accessibilitat;
	}

	public void setDisponibilitat(boolean disponibilitat) {
		this.disponibilitat = disponibilitat;
	}

	public void setPlantes(ArrayList<Planta> plantes) {
		this.plantes = plantes;
	}

	public void setNumPlaces(){

		for (int i = 0; i < plantes.size(); i++) {
			for (int j = 0; j < plantes.get(i).getHabitacions().size(); j++) {
				this.numPlaces = this.numPlaces + plantes.get(i).getHabitacions().get(j).getNumLlits();
			}
		}
	}

	// toString
	@Override
	public String toString() {
		return "Espai [id=" + id + ", numPlantes=" + numPlantes + ", adreca=" + adreca + ", accessibilitat="
				+ accessibilitat + ", numPlaces=" + numPlaces + ", disponibilitat=" + disponibilitat + "] " + "Plantes " + plantes ;
	}

}
