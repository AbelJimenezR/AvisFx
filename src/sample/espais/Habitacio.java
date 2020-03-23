package sample.espais;

public class Habitacio {

	// atribut
	private int id;
	private int numLlits;


	// constructor
	public Habitacio(){};
	public Habitacio(int numLlits) {
		this.numLlits = numLlits;
	}

	// getter
	public int getId() { return id; }
	public int getNumLlits() {
		return numLlits;
	}

	// setter
	public void setId(int id){ this.id=id; }
	public void setNumLlits(int numLlits) {
		this.numLlits = numLlits;
	}

	// toString
	@Override
	public String toString() {
		return "Habitacio [id=" +id + ", numLlits=" + numLlits + "]";
	}

}
