package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Repository.CentroMedicoRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaCentroMedico
{
    public List<CentroMedico> obtenerCentros(Context context){
        CentroMedicoRepository repository = new CentroMedicoRepository(context);
        return repository.getAll();
    }

    public CentroMedico obtenerCentro(Context context, int id)
    {
        CentroMedicoRepository repository = new CentroMedicoRepository(context);
        return repository.getById(id);
    }
}
