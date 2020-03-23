package sample.espais;

import sample.Conexion;
import sample.usuaris.Usuari;
import sample.usuaris.UsuariCoordinador;
import sample.usuaris.UsuariGestor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public interface IEspaiDao {

    public boolean crearEspai();

    public void crearPlanta(int idEspai);

    public void creaHabitacio(ArrayList<Habitacio> habitacions, int idPlanta, int idEspai);

    public void eliminaEspai(int id);

}
