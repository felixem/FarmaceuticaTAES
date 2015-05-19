package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Repository.MaterialPromocionalRepository;
import farmaceutica.taes.domainmodel.Repository.VisitaRepository;
import farmaceutica.taes.domainmodel.Repository.MaterialPromocionalRepository;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;

/**
 * Created by Javi on 12/05/2015.
 */
public class FachadaVisita {

    public List<Visita> obtenerVisitas(Context context) {
        VisitaRepository repository = new VisitaRepository(context);
        return repository.getAll();
    }

    public Visita ObtenerVisitasPorId(Context context, int id) {
        VisitaRepository visita = new VisitaRepository(context);
        return (visita.getVisitaById(id));
    }


    public List<MaterialPromocional> ObtenerinformacionProducPromocionales(Context context, int idVisita)
    {

        MaterialPromocionalRepository material = new MaterialPromocionalRepository(context);
        return(material.getProductosOfertados(idVisita));
    }

    // Hacer un metodo que dado un id de visita saque los productos de esa visita con su valoracion

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
