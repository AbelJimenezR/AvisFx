package sample.espais;

import java.util.ArrayList;

public class Planta {

	// atributs
	private int id;
	private int numHabitacions;
	private double superficie;
	private int numSales;
	private ArrayList<Habitacio> habitacions=new ArrayList<Habitacio>();

	public Planta(){
	};
	// constructor
	public Planta(int id, double superficie, int numSales, int numHabitacions) {
		this.id=id;
		this.superficie = superficie;
		this.numSales = numSales;
		this.numHabitacions = numHabitacions;


	}

	// getters

	public int getId() {
		return id;
	}

	public int getNumHabitacions() {
		return numHabitacions;
	}

	public double getSuperficie() {
		return superficie;
	}

	public int getNumSales() {
		return numSales;
	}

	public ArrayList<Habitacio> getHabitacions() {
		return habitacions;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setNumHabitacions(int numHabitacions) {
		this.numHabitacions = numHabitacions;
		for(int i=0;i<numHabitacions;i++){
			this.habitacions.add(new Habitacio());
		}
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public void setNumSales(int numSales) {
		this.numSales = numSales;
	}

	public void setHabitacions(ArrayList<Habitacio> habitacions) {
		this.habitacions = habitacions;
	}

	// toString
	@Override
	public String toString() {
		return "Planta [id=" + id + ", numHabitacions=" + numHabitacions + ", superficie=" + superficie + ", numSales=" + numSales
				+ ", habitacions=" + habitacions + "]";
	}

}