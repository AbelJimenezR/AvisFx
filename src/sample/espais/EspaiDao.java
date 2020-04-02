package sample.espais;

import sample.CarregaDadesDao;
import sample.Conexion;
import sample.usuaris.Usuari;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EspaiDao implements IEspaiDao {
    ArrayList<Espai> ae = CarregaDadesDao.getLlistaEspais();

    private int id;
    private String nom;
    private String tipusEntitat;
    private int numPlantes;
    private int accessibilitat;
    private int disponibilitat;
    private String adreca;
    private int numPlaces;
    private ArrayList<Planta> llistaPlantes;

    public EspaiDao(){};

    public EspaiDao(EspaiEntitat espai) {
        this.id = espai.getId();
        this.nom = espai.getNomEntitat();
        this.numPlantes = espai.getNumPlantes();
        if (espai.getAccessibilitat()) {
            this.accessibilitat = 1;
        } else {
            this.accessibilitat = 0;
        }
        if (espai.getDisponibilitat()) {
            this.disponibilitat = 1;
        } else {
            this.disponibilitat = 0;
        }
        this.adreca = espai.getAdreca();
        this.tipusEntitat = espai.getTipusEntitat();
        this.numPlaces = espai.getNumPlaces();
        this.llistaPlantes = espai.getPlantes();
    }

    public EspaiDao(EspaiPropietari espai) {
        this.id = espai.getId();
        this.nom = espai.getNomPropietari();
        this.numPlantes = espai.getNumPlantes();
        if (espai.getAccessibilitat()) {
            this.accessibilitat = 1;
        } else {
            this.accessibilitat = 0;
        }
        if (espai.getDisponibilitat()) {
            this.disponibilitat = 1;
        } else {
            this.disponibilitat = 0;
        }
        this.adreca = espai.getAdreca();
        this.numPlaces = espai.getNumPlaces();
        this.llistaPlantes = espai.getPlantes();


    }



    @Override
    public boolean crearEspai() {

        try {
            Connection conn = Conexion.conectar();
            String query = "INSERT into espai(nom,num_plantes,adreca,accessibilitat,num_Places,disponibilitat,tipus_Entitat)" +
                    " values ('" + nom + "','" + numPlantes + "','" + adreca + "', '" + accessibilitat + "', '" + numPlaces + "','" + disponibilitat + "','" + tipusEntitat + "')";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();

            id = ultimId();
           /*query = "Select id_espai from espai order by id_espai desc limit 1";
           st = conn.createStatement();
           ResultSet rs = st.executeQuery(query);
           while (rs.next()) {
               id = rs.getInt("id_espai");
           }*/

        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }


        crearPlanta(id);
        return true;
    }

    @Override
    public void crearPlanta(int idEspai) {
        for (Planta p : llistaPlantes) {
            int idPlanta = p.getId();
            Double superficie = p.getSuperficie();
            int numHabitacions = p.getNumHabitacions();
            int numSales = p.getNumSales();

            try {
                Connection conn = Conexion.conectar();
                String query = "INSERT into planta(id_planta,superficie,num_sales,num_habitacions,id_espai)" +
                        " values ('" + idPlanta + "','" + superficie + "','" + numSales + "','" + numHabitacions + "', '" + idEspai + "')";
                Statement st = conn.createStatement();
                st.execute(query);
                st.close();

                creaHabitacio(p.getHabitacions(), idPlanta, idEspai);

            } catch (Exception e) {
                System.err.println("Got an exception! b");
                System.err.println(e.getMessage());
            }

        }

    }

    @Override
    public void creaHabitacio(ArrayList<Habitacio> habitacions, int idPlanta, int idEspai) {
        int numLlits;
        int id;
        for (Habitacio h : habitacions) {
            numLlits = h.getNumLlits();
            id = h.getId();


            try {
                Connection conn = Conexion.conectar();
                String query = "INSERT into habitacio(id_habitacio,num_llits,id_planta,id_espai)" +
                        " values ('" + id + "','" + numLlits + "','" + idPlanta + "','" + idEspai + "')";
                Statement st = conn.createStatement();
                st.execute(query);
                st.close();

            } catch (Exception e) {
                System.err.println("Got an exception! c");
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void eliminaEspai(int id){
        try {
            Connection conn = Conexion.conectar();
            String query = "DELETE from espai where id_espai='" + id + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            conn.close();
            for (Espai e : ae) {
                if (e.getId() == id) {
                    ae.remove(e);
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Got an exception! b");
            System.err.println(e.getMessage());

        }

    }
    public static int ultimId() {
        int ultimId = 0;
        Connection conn = Conexion.conectar();
        try {
            String query = "Select id_espai from espai order by id_espai desc limit 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ultimId = rs.getInt("id_espai");
            }
            st.execute(query);
            st.close();

        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }
        return ultimId;
    }

    @Override
    public void actualitzaPlanta(Planta p) {
        try {
          /*  Connection conn = Conexion.conectar();
            String query = "UPDATE planta set superficie='" + p.getSuperficie() + "', num_sales='" + p.getNumSales() + "', num_habitacions= '"+ p.getNumHabitacions()
                    +"'  where id_planta='"+ p.getId() + "' and id_espai='"+ +"'";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            conn.close();*/

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }
    /*

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
*/
}
