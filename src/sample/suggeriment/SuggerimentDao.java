package sample.suggeriment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import sample.CarregaDadesDao;
import sample.Conexion;
import sample.historial.Historial;
import sample.historial.HistorialDao;

public class SuggerimentDao implements ISuggerimentDao {
    private static Scanner sc = new Scanner(System.in);
    ArrayList<Suggeriment> as = CarregaDadesDao.getLlistaSuggeriments();
	
	@Override
	public boolean crearSuggeriment(int idEspai, int idPlanta, int idHabitacio, String nomIaio, int idIaio, Date dataEntrada, Date dataSortida) {
		try {
            Connection conn = Conexion.conectar();
            String query = "INSERT into suggeriment(id_espai, id_planta, id_habitacio, nom_iaio, id_iaio, data_entrada, data_sortida) values ('" + idEspai + "','" + idPlanta + "','" + idHabitacio + "','" + nomIaio + "','" + idIaio + "','" + dataEntrada + "','" + dataSortida + "')";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();

            st.close();
            conn.close();

            as.add(new Suggeriment(idEspai, idPlanta, idHabitacio, nomIaio, idIaio, dataEntrada, dataSortida));


        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }

        return true;
	}

	@Override
	//public boolean acceptarSuggeriment(int idEspai, int idPlanta, int idHabitacio, String nomIaio, int idIaio, Date dataEntrada, Date dataSortida) {
        public boolean acceptarSuggeriment(int idEspai, int idPlanta, int idHabitacio, String nomIaio, int idIaio, String dataEntrada, String dataSortida) {
	    String dE = dataEntrada;
		String dS = dataSortida;
	    try {
            Connection conn = Conexion.conectar();
            String query = "INSERT into historial(id_espai, id_planta, id_habitacio, nom_iaio, id_iaio, data_entrada, data_sortida) values ('" + idEspai + "','" + idPlanta + "','" + idHabitacio + "','" + nomIaio + "','" + idIaio + "','" + dE + "','" + dS + "')";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();

            st.close();
            conn.close();
            SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd");
            Date dEnt=  sd.parse(dataEntrada);
            Date dSor= sd.parse(dataSortida);

            HistorialDao.ah.add(new Historial(idEspai, idPlanta, idHabitacio, nomIaio, idIaio, dEnt, dSor));

            eliminarSuggeriment(idEspai, idIaio);
            
            
        } catch (Exception e) {
            System.err.println("Got an exception! a");
            System.err.println(e.getMessage());
        }

        return true;
	}

	@Override
	public void eliminarSuggeriment(int idEspai, int idIaio) {
		   try {
	            Connection conn = Conexion.conectar();
	            String query = "DELETE from suggeriment where id_iaio='" + idIaio + "' and id_espai='" + idEspai+ "'";
	            Statement st = conn.createStatement();
	            st.execute(query);
	            st.close();
	            conn.close();
	            for (Suggeriment i : as) {
	                if (i.getIdEspai() == idEspai && i.getIdIaio() == idIaio) {
	                    as.remove(i);
	                    break;
	                }
	            }

	        } catch (Exception e) {
	            System.err.println("Got an exception! b");
	            System.err.println(e.getMessage());	            
	        }
	}

	@Override
	public void actualitzarSuggeriment(int oldIdE, int oldIdIaio, int idEspai, int idPlanta, int idHabitacio, String nomIaio,
			int idIaio, Date dataEntrada, Date dataSortida) {
		try {
            Connection conn = Conexion.conectar();
            String query = "UPDATE suggeriment set id_espai='" + idEspai + "' , id_planta='"+ idPlanta  + "' , id_habitacio='"+ idHabitacio  + "' , nom_iaio='"+ nomIaio + "' , id_iaio='"+ idIaio + "' , data_entrada='"+ dataEntrada + "' , data_sortida='"+ dataSortida +"'  where id_iaio='" + oldIdIaio + "' and id_Espai='" + oldIdE + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
            conn.close();

            for (Suggeriment i : as) {
                if (i.getIdEspai() == oldIdE && i.getIdIaio()==oldIdIaio) {
                    i.setIdEspai(idEspai);
                    i.setIdPlanta(idPlanta);
                    i.setIdHabitacio(idHabitacio);
                    i.setDataEntrada(dataEntrada);
                    i.setDataSortida(dataSortida);
                    break;
                }
            }


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		
	}

}
