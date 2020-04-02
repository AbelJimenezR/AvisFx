package sample.suggeriment;

import java.util.Date;

public interface ISuggerimentDao {
	
    public boolean crearSuggeriment(int idEspai, int idPlanta, int idHabitacio, String nomIaio, int idIaio, Date dataEntrada, Date dataSortida);
    public boolean acceptarSuggeriment(int idEspai, int idPlanta, int idHabitacio, String nomIaio, int idIaio, Date dataEntrada, Date dataSortida);
    public void actualitzarSuggeriment(int oldIdE, int oldIdIaio, int idEspai, int idPlanta, int idHabitacio, String nomIaio, int idIaio, Date dataEntrada, Date dataSortida);
    public void eliminarSuggeriment(int idEspai, int idIaio);
    
}
