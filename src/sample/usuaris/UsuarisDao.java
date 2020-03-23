package sample.usuaris;

import sample.CarregaDadesDao;
import sample.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarisDao implements IUsuarisDao {
    private static Scanner sc = new Scanner(System.in);
    ArrayList<Usuari> au = CarregaDadesDao.getLlistaUsuaris();
    int id;

    @Override
    public boolean crearUsuari(String nom, String password, int rol) {
        try {
            Connection conn = Conexion.conectar();
            String query = "INSERT into usuari(nom,password,rol) values ('" + nom + "','" + password + "','" + rol + "')";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();

            query = "Select id_usuari from usuari order by id_usuari desc limit 1";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_usuari");
            }

            st.close();
            conn.close();

            if (rol == 1) {
                au.add(new UsuariCoordinador(id, nom, password));
            } else if (rol == 2) {
                au.add(new UsuariGestor(id, nom, password));
            } else {
                au.add(new Usuari(id, nom, password));
            }

        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }

        return true;
    }

    @Override
    public void actualitzarUsuari(int id, String nom, String password) {
        try {
            Connection conn = Conexion.conectar();
            String query = "UPDATE usuari set nom='" + nom + "', password='" + password + "'  where id_usuari='" + id + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            conn.close();

            for (Usuari u : au) {
                if (u.getId() == id) {
                    u.setNom(nom);
                    u.setPassword(password);
                    break;
                }
            }


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }

    @Override
    public void eliminarUsuari(int id) {
        try {
            Connection conn = Conexion.conectar();
            String query = "DELETE from usuari where id_usuari='" + id + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            conn.close();
            for (Usuari u : au) {
                if (u.getId() == id) {
                    au.remove(u);
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Got an exception! b");
            System.err.println(e.getMessage());

        }

    }

    @Override
    public void llistarUsuari() {

    }

    @Override
    public void buscarUsuari(Usuari usuari) {

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
