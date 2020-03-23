package sample.usuaris;

public interface IUsuarisDao {

    public boolean crearUsuari(String nom,String password, int rol);
    public void actualitzarUsuari(int id, String nom, String pass);
    public void eliminarUsuari(int id);
    public void llistarUsuari();
    public void buscarUsuari(Usuari usuari);

}
