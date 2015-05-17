package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Medico;
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

    public static List<Visita> obtenerVisitasPorMedico(Context context, Medico medico)
    {
        VisitaRepository repository = new VisitaRepository(context);
        return repository.getAllByMedico(medico);
    }

    public static int create(Context context, Visita visita)
    {
        VisitaRepository repository = new VisitaRepository(context);
        return repository.create(visita);
    }
}
