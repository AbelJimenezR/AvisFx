package sample.espais;

import java.util.ArrayList;

public class EspaiEntitat extends Espai {

	// atributs
	private String nomEntitat;
	private String tipusEntitat;

	// constructor
	public EspaiEntitat(int id, String adreca, boolean accessibilitat, boolean disponibilitat, int numPlantes, String nomEntitat, String tipusEntitat) {
		super(id, adreca, accessibilitat, disponibilitat, numPlantes);
		this.nomEntitat = nomEntitat;
		this.tipusEntitat = tipusEntitat;
	}

	// getters
	public String getNomEntitat() {
		return nomEntitat;
	}

	public String getTipusEntitat() {
		return tipusEntitat;
	}

	// setters
	public void setNomEntitat(String nomEntitat) {
		this.nomEntitat = nomEntitat;
	}

	public void setTipusEntitat(String tipusEntitat) {
		this.tipusEntitat = tipusEntitat;
	}

	// toString
	@Override
	public String toString() {
		return super.toString() + "EspaiEntitat [nomEntitat=" + nomEntitat + ", tipusEntitat=" + tipusEntitat + "]";
	}


}
