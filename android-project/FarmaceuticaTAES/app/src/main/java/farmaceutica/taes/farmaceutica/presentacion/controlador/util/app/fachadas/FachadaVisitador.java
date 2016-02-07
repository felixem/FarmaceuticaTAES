package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Repository.VisitadorRepository;

/**
 * Created by Paco on 16/05/2015.
 */
public class FachadaVisitador {

    public Visitador login(Context context, String user, String password) throws Exception {
        VisitadorRepository visi = new VisitadorRepository(context);
        Visitador v =null;

        try {
            int id = Integer.parseInt(user);
            v = visi.getByID(id);
            if(v!=null && v.getPassword().equals(password))
                return v;

        }catch (Exception e)
        {

        }finally {

        }
        try {
            v = visi.getByEMAIL(user);
            if (v != null && v.getPassword().equals(password))
                return v;
        }catch (Exception e)
        {
            throw e;

        }
        return null;
    }

    public static Visitador obtenerVisitador(Context context, int id)
    {
        VisitadorRepository visitadorRepository = new VisitadorRepository(context);
        try {
            return visitadorRepository.getByID(id);
        } catch (SQLException e) {
            return null;
        }
    }

}
