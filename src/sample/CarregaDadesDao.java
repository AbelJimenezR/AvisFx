package sample;

import sample.espais.*;
import sample.historial.*;
import sample.suggeriment.*;
import sample.iaios.GrauIncapacitat;
import sample.iaios.Iaio;
import sample.usuaris.Usuari;
import sample.usuaris.UsuariCoordinador;
import sample.usuaris.UsuariGestor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CarregaDadesDao implements ICarregaDadesDao {
    private static ArrayList<Usuari> llistaUsuaris = new ArrayList<Usuari>();
    private static ArrayList<Iaio> llistaIaios = new ArrayList<Iaio>();
    private static ArrayList<Espai> llistaEspais = new ArrayList<Espai>();
    private static ArrayList<Suggeriment> llistaSuggeriments = new ArrayList<Suggeriment>();
    private static ArrayList<Historial> llistaHistorial = new ArrayList<Historial>();
    private ArrayList<ArrayList> planta = new ArrayList<>();
    private ArrayList<ArrayList> habitacion = new ArrayList<>();

    int idEspai = 0;
    String nom = null;
    String tipusEntitat = null;
    int numPlantes = 0;
    String adreca = null;
    int accessibilitat = 0;
    boolean access = false;
    int numPlaces = 0;
    int disponibilitat = 0;
    boolean disp = false;
    Espai ep;


    @Override
    public void carregaUsuaris() {
        try {

            Connection conn = Conexion.conectar();
            String query = "SELECT * FROM usuari";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id_usuari");
                String nom = rs.getString("nom");
                String password = rs.getString("password");
                int rol = rs.getInt("rol");

                if (rol == 1) {
                    llistaUsuaris.add(new UsuariCoordinador(id, nom, password));
                } else if (rol == 2) {
                    llistaUsuaris.add(new UsuariGestor(id, nom, password));
                } else if (rol == 3) {
                    llistaUsuaris.add(new Usuari(id, nom, password));
                }

            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void carregaIaios() {
        try {

            Connection conn = Conexion.conectar();
            String query = "SELECT * FROM iaio";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id_iaio");
                String nom = rs.getString("nom");
                int password = rs.getInt("edat");
                int incapacitat = rs.getInt("incapacitat");

                GrauIncapacitat gi = Utilitat.comprovaIncapacitat(incapacitat);
                llistaIaios.add(new Iaio(id, nom, password,gi));

            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void carregaEspais() {

        try {
            ArrayList l = new ArrayList();
            int a = 0;
           // planta.add(a, l);
            Connection conn = Conexion.conectar();
            String query = "SELECT * FROM planta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id_espai = rs.getInt("id_espai");
                int plant = rs.getInt("id_planta");
                int superficie = rs.getInt("superficie");
                int sales = rs.getInt("num_sales");
                int habitacions = rs.getInt("num_habitacions");
                l.add(id_espai);
                l.add(plant);
                l.add(superficie);
                l.add(sales);
                l.add(habitacions);
                ArrayList s = (ArrayList) l.clone();
                planta.add(a, s);
                l.clear();
                a = a + 1;


            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        try {
            ArrayList l = new ArrayList();
            int a = 0;
            Connection conn = Conexion.conectar();
            String query = "SELECT * FROM habitacio";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id_espai = rs.getInt("id_espai");
                int id_planta = rs.getInt("id_planta");
                int id_habitacio = rs.getInt("id_habitacio");
                int num_llits = rs.getInt("num_llits");
                l.add(id_espai);
                l.add(id_planta);
                l.add(id_habitacio);
                l.add(num_llits);
                ArrayList s = (ArrayList) l.clone();
                habitacion.add(a, s);
                l.clear();
                a = a + 1;


            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        try {

            Connection conn = Conexion.conectar();
            String query = "SELECT * FROM espai";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                idEspai = rs.getInt("id_espai");
                nom = rs.getString("nom");
                tipusEntitat = rs.getString("tipus_entitat");
                numPlantes = rs.getInt("num_Plantes");
                adreca = rs.getString("adreca");
                accessibilitat = rs.getInt("accessibilitat");
                if (accessibilitat == 0) {
                    access = false;
                } else {
                    access = true;
                }
                numPlaces = rs.getInt("num_Places");
                disponibilitat = rs.getInt("disponibilitat");
                if (disponibilitat == 0) {
                    disp = false;
                } else {
                    disp = true;
                }
                cE();
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
 
    public void carregaSuggeriments() {
    	try {

            Connection conn = Conexion.conectar();
            String query = "SELECT * FROM suggeriment";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                int idE = rs.getInt("id_espai");
                int idP = rs.getInt("id_planta");
                int idH = rs.getInt("id_habitacio");
                String nomI = rs.getString("nom_iaio");
                int idI = rs.getInt("id_iaio");
                Date dE = rs.getDate("data_entrada");
                Date dS = rs.getDate("data_sortida");

                llistaSuggeriments.add(new Suggeriment(idE, idP, idH, nomI, idI, dE, dS));

            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }   
    }
    
    public void carregaHistorial() {
    	try {

            Connection conn = Conexion.conectar();
            String query = "SELECT * FROM historial";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                int idE = rs.getInt("id_espai");
                int idP = rs.getInt("id_planta");
                int idH = rs.getInt("id_habitacio");
                String nomI = rs.getString("nom_iaio");
                int idI = rs.getInt("id_iaio");
                Date dE = rs.getDate("d_Entrada");
                Date dS = rs.getDate("d_Sortida");

                llistaHistorial.add(new Historial(idE, idP, idH, nomI, idI, dE, dS));

            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }    	
    }
    
    public void cE(){
        if(tipusEntitat.equals("null")) {
            ep = new EspaiPropietari(idEspai, adreca, access, disp, numPlantes, nom);
        }else{
            ep = new EspaiEntitat(idEspai, adreca, access, disp, numPlantes, nom,tipusEntitat);
        }
        ArrayList<Planta> ap = ep.getPlantes();
        int pl=1;
        //int hb=1;

    for(Planta p : ap){
            for(int y = 0;y<planta.size();y++){
                if((idEspai)==Integer.parseInt(planta.get(y).get(0).toString())&& Integer.parseInt(planta.get(y).get(1).toString())==(pl)){
                p.setId(pl);
                p.setSuperficie(Double.parseDouble(planta.get(y).get(2).toString()));
                p.setNumSales(Integer.parseInt(planta.get(y).get(3).toString()));
                p.setNumHabitacions(Integer.parseInt(planta.get(y).get(4).toString()));
               // pl=pl+1;

                    int hb=1;
                    ArrayList<Habitacio> h =p.getHabitacions();
                    for(Habitacio hab : h) {
                        for(int x = 0;x<habitacion.size();x++){
                            if((idEspai)==Integer.parseInt(habitacion.get(x).get(0).toString())&& Integer.parseInt(habitacion.get(x).get(1).toString())==(pl)&&Integer.parseInt(habitacion.get(x).get(2).toString())==(hb)) {
                                hab.setId(hb);
                                hab.setNumLlits(Integer.parseInt(habitacion.get(x).get(3).toString()));
                                hb=hb+1;
                                break;
                            }
                        }


                        }
                pl=pl+1;
                        break;
                }
            }

           /* int hb=0;
            ArrayList<Habitacio> h =p.getHabitacions();
            for(Habitacio hab : h) {
                for(int x = 0;x<habitacion.size();x++){
                    if((idEspai)==Integer.parseInt(habitacion.get(x).get(0).toString())&& Integer.parseInt(habitacion.get(x).get(1).toString())==(hb+1)){
                        hab.setId(hb+1);
                        hab.setNumLlits(Integer.parseInt(habitacion.get(x).get(3).toString()));

break;
                    }
                }
                hb=hb+1;
            }*/
        }

        ep.setNumPlaces();
        llistaEspais.add(ep);
        //System.out.println(ep);
    }

    public static ArrayList<Usuari> getLlistaUsuaris() {
        return llistaUsuaris;
    }

    public static void setLlistaUsuaris(ArrayList<Usuari> llistaUsuaris) {
        CarregaDadesDao.llistaUsuaris = llistaUsuaris;
    }

    public static ArrayList<Iaio> getLlistaIaios() {
        return llistaIaios;
    }

    public static void setLlistaIaios(ArrayList<Iaio> llistaIaios) {
        CarregaDadesDao.llistaIaios = llistaIaios;
    }

    public static ArrayList<Espai> getLlistaEspais() {
        return llistaEspais;
    }

    public static void setLlistaEspais(ArrayList<Espai> llistaEspais) {
        CarregaDadesDao.llistaEspais = llistaEspais;
    }
    

	public static ArrayList<Suggeriment> getLlistaSuggeriments() {
		return llistaSuggeriments;
	}
	

	public static void setLlistaSuggeriments(ArrayList<Suggeriment> llistaSuggeriments) {
		CarregaDadesDao.llistaSuggeriments = llistaSuggeriments;
	}
	

	public static ArrayList<Historial> getLlistaHistorial() {
		return llistaHistorial;
	}
	

	public static void setLlistaHistorial(ArrayList<Historial> llistaHistorial) {
		CarregaDadesDao.llistaHistorial = llistaHistorial;
	}

    

}
