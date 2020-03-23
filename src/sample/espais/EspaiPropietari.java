package sample.espais;

import java.util.ArrayList;

public class EspaiPropietari extends Espai {

	// atribut
	private String nomPropietari;

	// constructors

	public EspaiPropietari(int id, String adreca, boolean accessibilitat, boolean disponibilitat, int numPlantes, String nomPropietari) {
		super(id, adreca, accessibilitat, disponibilitat, numPlantes);
		this.nomPropietari = nomPropietari;
	}

	// getter
	public String getNomPropietari() {
		return nomPropietari;
	}

	// setter
	public void setNomPropietari(String nomPropietari) {
		this.nomPropietari = nomPropietari;
	}

	// toString
	@Override
	public String toString() {
		return super.toString() + "EspaiPropietari [nomPropietari=" + nomPropietari + "]";
	}

}
