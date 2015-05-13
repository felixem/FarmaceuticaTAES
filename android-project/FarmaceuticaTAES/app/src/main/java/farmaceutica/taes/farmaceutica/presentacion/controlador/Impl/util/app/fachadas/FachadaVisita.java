package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas;

import android.content.Context;
import java.util.List;
import java.util.ArrayList;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.VisitaRepository;

/**
 * Created by Javi on 12/05/2015.
 */
public class FachadaVisita {

    public List<Visita> obtenerVisitas(Context context){
        VisitaRepository repository = new VisitaRepository(context);
        return repository.getAll();
    }
}
