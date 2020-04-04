package sample.espais;

import sample.CarregaDadesDao;
import sample.Conexion;
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
        int idEspai = ultimId()+1;
        try {
            Connection conn = Conexion.conectar();
            String query = "INSERT into espai(id_espai,nom,num_plantes,adreca,accessibilitat,num_Places,disponibilitat,tipus_Entitat)" +
                    " values ('" + idEspai + "','" + nom + "','" + numPlantes + "','" + adreca + "', '" + accessibilitat + "', '" + numPlaces + "','" + disponibilitat + "','" + tipusEntitat + "')";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();

            //id = ultimId();


        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }


        crearPlanta(idEspai);
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
    public void actualitzaPlanta(Espai ep) {
            int id_espai=ep.getId();
            ArrayList<Planta> llPanta = ep.getPlantes();
            for(Planta p: llPanta) {
               int id_planta =p.getId();
                double superficie = p.getSuperficie();
                int num_sales= p.getNumSales();
                int num_hab= p.getNumHabitacions();
                try {
                    Connection conn = Conexion.conectar();
                    String query = "INSERT into planta(id_planta,superficie,num_sales,num_habitacions,id_espai) " +
                            "values ('"+id_planta+"','"+superficie+"','"+num_sales+"','"+num_hab+"','"+id_espai+"')";
                    Statement st = conn.createStatement();
                    st.execute(query);
                    st.close();
                    conn.close();
                    actualitzaHabitacio(ep,p);
                } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }

    }

    @Override
    public void actualitzaHabitacio(Espai es, Planta p){
        int id_espai=es.getId();
        int id_planta=p.getId();

        ArrayList<Habitacio> llHab = p.getHabitacions();
        for( Habitacio h : llHab){
            int id_hab= h.getId();
            int num_llits= h.getNumLlits();
            h.getNumLlits();
            try {
                Connection conn = Conexion.conectar();
                String query = "INSERT into habitacio(id_habitacio,num_llits,id_planta,id_espai) " +
                        "values ('"+id_hab+"','"+num_llits+"','"+id_planta+"','"+id_espai+"')";
                Statement st = conn.createStatement();
                st.execute(query);
                st.close();
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }



        }

    }

    @Override
    public void actualitzaEspai(Espai ep) {
            String nom=null;
            String tipusEntitat=null;
            int id= ep.getId();
            int numPlaces= ep.getNumPlaces();
            int acces = (ep.getAccessibilitat()) ? 1 : 0;
            String adreca =ep.getAdreca();
            int numPlantes = ep.getNumPlantes();
            int disponibilitat=1;

            if(ep.getClass().getSimpleName().equals("EspaiEntitat")){
                EspaiEntitat ee = (EspaiEntitat) ep;
                nom= ee.getNomEntitat();
                tipusEntitat=ee.getTipusEntitat();
            }else{
                EspaiPropietari epr = (EspaiPropietari) ep;
                nom= epr.getNomPropietari();
            }


            try {
                Connection conn = Conexion.conectar();
                String query = "DELETE FROM espai where id_espai= '"+id+"'";
                Statement st = conn.createStatement();
                st.execute(query);
                st.close();
                conn.close();

            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }

        try {
            Connection conn = Conexion.conectar();
            String query = "INSERT into espai(id_espai,nom,num_plantes,adreca,accessibilitat,num_Places,disponibilitat,tipus_entitat)" +
                    " values ('" + id + "','" + nom + "','" + numPlantes + "','" + adreca + "', '" + acces + "', '" + numPlaces + "','" + disponibilitat + "' ,'"+ tipusEntitat +"' )";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            actualitzaPlanta(ep);


        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }


    }

    public void actualitzaDisponibilitat(Espai esp){
            int id_espai=esp.getId();
        try {
            Connection conn = Conexion.conectar();
            String query = "UPDATE espai set disponibilitat=0 where id_espai=1"+id_espai+"";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();


        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }


    }



}
