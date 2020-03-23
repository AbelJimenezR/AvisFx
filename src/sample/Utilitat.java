package sample;

import sample.iaios.GrauIncapacitat;
import sample.iaios.Iaio;
import sample.usuaris.Usuari;

import java.util.ArrayList;


public class Utilitat {

    public static String comprovaUsuari(String usuari, String pass) {
        ArrayList<Usuari> llistaUsuaris = CarregaDadesDao.getLlistaUsuaris();

        for (Usuari u : llistaUsuaris) {
            if (u.getNom().equals(usuari) && u.getPassword().equals(pass)) {
                return u.getClass().getSimpleName().toString();
            }
        }
        return "no";
    }

    public static GrauIncapacitat comprovaIncapacitat(int incapacitat){
        if (incapacitat == 1) {
            return GrauIncapacitat.BAIX;
        } else if (incapacitat == 2) {
            GrauIncapacitat e = GrauIncapacitat.ALT;
            return GrauIncapacitat.ALT;
        } else {
            GrauIncapacitat e = GrauIncapacitat.MOLTALT;
            return GrauIncapacitat.MOLTALT;
        }
    }
}
