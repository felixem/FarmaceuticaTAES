package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Trayectoria;
import farmaceutica.taes.domainmodel.Repository.TrayectoriaRepository;

/**
 * Created by Pedro on 19/05/2015.
 */
public class FachadaTrayectoria {

    public static List<Trayectoria> obtenerTrayectorias(Context context, Medico medico){
        TrayectoriaRepository repository = new TrayectoriaRepository(context);
        return repository.getAllByMedico(medico);
    }
}
