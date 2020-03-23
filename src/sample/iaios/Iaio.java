package sample.iaios;

public class Iaio {

        //atributs
        private int id;
        private String nom;
        private int edat;
        private GrauIncapacitat incapacitat;


        //constructor
       public Iaio(int id, String nom, int edat, GrauIncapacitat incapacitat){
            this.id = id;
            this.nom = nom;
            this.edat = edat;
            this.incapacitat = incapacitat;
        }

        //getters
        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public int getEdat() {
            return edat;
        }

        public GrauIncapacitat getIncapacitat() {
            return incapacitat;
        }

        //setters
        public void setId(int id) {
            this.id = id;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setEdat(int edat) {
            this.edat = edat;
        }

        public void setIncapacitat(GrauIncapacitat incapacitat) {
            this.incapacitat = incapacitat;
        }

        //toString
        @Override
        public String toString() {
            return "Avi [id=" + id + ", nom=" + nom + ", edat=" + edat + ", incapacitat=" + incapacitat + "]";
        }

    }