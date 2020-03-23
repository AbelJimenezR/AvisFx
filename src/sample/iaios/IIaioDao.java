package sample.iaios;

import sample.usuaris.Usuari;

public interface IIaioDao {

    public boolean crearIaio(String nom, int edat, int incapacitat);
    public void actualitzarIaio(int id, String nom, int edat, int incapacitat);
    public void eliminarIaio(int id);
    public void llistaIaio();
    public void buscaIaio(Usuari usuari);

}
