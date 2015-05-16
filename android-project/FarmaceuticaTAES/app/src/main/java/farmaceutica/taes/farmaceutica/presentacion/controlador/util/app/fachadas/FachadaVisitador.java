package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Repository.VisitadorRepository;

/**
 * Created by Paco on 16/05/2015.
 */
public class FachadaVisitador {

    public boolean login(Context context, String user, String password) throws Exception {
        VisitadorRepository visi = new VisitadorRepository(context);
        Visitador v =null;

        try {
            int id = Integer.parseInt(user);
            v = visi.getByID(id);
            if(v!=null && v.getPassword().equals(password))
                return true;

        }catch (Exception e)
        {

        }finally {

        }
        try {
            v = visi.getByEMAIL(user);
            if (v != null && v.getPassword().equals(password))
                return true;
        }catch (Exception e)
        {
            throw e;

        }
        return false;
    }

}
