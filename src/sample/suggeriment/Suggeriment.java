package sample.suggeriment;

import java.util.Date;

public class Suggeriment {
	
	//atributs
	private int idEspai;
	private int idPlanta;
	private int idHabitacio;
	private String nomIaio;
	private int idIaio;
	private Date dataEntrada;
	private Date dataSortida;
		
	//constructors
	public Suggeriment() {}
	
	public Suggeriment(int idEspai, int idPlanta, int idHabitacio, String nomIaio, int idIaio, Date dataEntrada, Date dataSortida) {
		this.idEspai=idEspai;
		this.idPlanta=idPlanta;
		this.idHabitacio=idHabitacio;
		this.nomIaio=nomIaio;
		this.idIaio=idIaio;
		this.dataEntrada=dataEntrada;
		this.dataSortida=dataSortida;
	}

	//setters
	public void setIdEspai(int idEspai) {
		this.idEspai = idEspai;
	}

	public void setIdPlanta(int idPlanta) {
		this.idPlanta = idPlanta;
	}

	public void setIdHabitacio(int idHabitacio) {
		this.idHabitacio = idHabitacio;
	}

	public void setIdIaio(int idIaio) {
		this.idIaio = idIaio;
	}

	public void setNomIaio(String nomIaio) {
		this.nomIaio = nomIaio;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setDataSortida(Date dataSortida) {
		this.dataSortida = dataSortida;
	}

	//getters
	public int getIdEspai() {
		return idEspai;
	}

	public int getIdPlanta() {
		return idPlanta;
	}

	public int getIdHabitacio() {
		return idHabitacio;
	}

	public int getIdIaio() {
		return idIaio;
	}

	public String getNomIaio() {
		return nomIaio;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public Date getDataSortida() {
		return dataSortida;
	}

	//toString
	@Override
	public String toString() {
		return "Historial [idEspai=" + idEspai + ", idPlanta="
				+ idPlanta + ", idHabitacio=" + idHabitacio + ", nomIaio=" + nomIaio + ", idIaio=" + idIaio
				+ ", dataEntrada=" + dataEntrada + ", dataSortida=" + dataSortida + "]";
	}
		
}
