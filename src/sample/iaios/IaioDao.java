package sample.iaios;

import sample.CarregaDadesDao;
import sample.Conexion;
import sample.Utilitat;
import sample.usuaris.IUsuarisDao;
import sample.usuaris.Usuari;
import sample.usuaris.UsuariCoordinador;
import sample.usuaris.UsuariGestor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class IaioDao implements IIaioDao {
    private static Scanner sc = new Scanner(System.in);
    ArrayList<Iaio> ai = CarregaDadesDao.getLlistaIaios();
    int id;

    @Override
    public boolean crearIaio(String nom, int edat, int incapacitat) {
        try {
            Connection conn = Conexion.conectar();
            String query = "INSERT into iaio(nom,edat,incapacitat) values ('" + nom + "','" + edat + "','" + incapacitat + "')";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();

            query = "Select id_iaio from iaio order by id_iaio desc limit 1";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_iaio");
            }

            st.close();
            conn.close();

            GrauIncapacitat gi = Utilitat.comprovaIncapacitat(incapacitat);
            ai.add(new Iaio(id, nom, edat, gi));


        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }

        return true;
    }

    @Override
    public void actualitzarIaio(int id, String nom, int edat, int incapacitat) {
        GrauIncapacitat gi = Utilitat.comprovaIncapacitat(incapacitat);
        try {
            Connection conn = Conexion.conectar();
            String query = "UPDATE iaio set nom='" + nom + "', edat='" + edat + "' , incapacitat='"+ incapacitat +"'  where id_iaio='" + id + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            conn.close();

            for (Iaio i : ai) {
                if (i.getId() == id) {
                    i.setNom(nom);
                    i.setEdat(edat);
                    i.setIncapacitat(gi);
                    break;
                }
            }


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }

    @Override
    public void eliminarIaio(int id) {

        try {
            Connection conn = Conexion.conectar();
            String query = "DELETE from iaio where id_iaio='" + id + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            conn.close();
            for (Iaio i : ai) {
                if (i.getId() == id) {
                    ai.remove(i);
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Got an exception! b");
            System.err.println(e.getMessage());

        }



    }

    @Override
    public void llistaIaio() {

    }

    @Override
    public void buscaIaio(Usuari usuari) {

        try {
            Connection conn = Conexion.conectar();
            String query = "SELECT * from usuari where id_usuari = '" + id + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int idUsuari = rs.getInt("id_usuari");
                String nom = rs.getString("nom");
                String password = rs.getString("password");
                int rol = rs.getInt("rol");
                System.out.format("%s, %s, %s, %s\n", idUsuari, nom, password, rol);

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Got an exception! c");
            System.err.println(e.getMessage());
        }

    }

}
