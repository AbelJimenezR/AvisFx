package sample.iaios;


public enum GrauIncapacitat {
    BAIX(0, 33), ALT(33, 66), MOLTALT(66, 99);

    // atributs
    private int grauMin;
    private int grauMax;

    //getters
    public int getGrauMin() {
        return grauMin;
    }

    public int getGrauMax() {
        return this.grauMax;
    }

    //setters
    public void setGrauMin(int grauMin) {
        this.grauMin = grauMin;
    }

    public void setGrauMax(int grauMax) {
        this.grauMax = grauMax;
    }

    // constructor
    private GrauIncapacitat(int grauMin, int grauMax) {
        this.grauMin = grauMin;
        this.grauMax = grauMax;
    }
}