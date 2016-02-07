package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.EspecialidadMedica;
import farmaceutica.taes.domainmodel.Repository.EspecialidadMedicaRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaEspecialidadMedica
{
    public static List<EspecialidadMedica> obtenerEspecialidadMedicas(Context context){
        EspecialidadMedicaRepository repository = new EspecialidadMedicaRepository(context);
        return repository.getAll();
    }

    public static EspecialidadMedica obtenerEspecialidadNedica(Context context, int id)
    {
        EspecialidadMedicaRepository repository = new EspecialidadMedicaRepository(context);
        return repository.getEspecialidadMedicaById(id);
    }

    public static int refresh(Context context, EspecialidadMedica especialidad)
    {
        EspecialidadMedicaRepository repository = new EspecialidadMedicaRepository(context);
        return repository.refresh(especialidad);
    }
}
