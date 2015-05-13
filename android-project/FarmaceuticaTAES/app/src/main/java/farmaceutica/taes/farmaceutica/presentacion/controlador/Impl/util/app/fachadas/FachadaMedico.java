package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Repository.CentroMedicoRepository;
import farmaceutica.taes.domainmodel.Repository.MedicoRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaMedico
{
    public List<Medico> obtenerMedicosPorCentroMedico(Context context, CentroMedico centro){
        MedicoRepository repository = new MedicoRepository(context);
        return repository.getAllByCentroMedico(centro);
    }

    public List<Medico> obtenerMedicos(Context context){
        MedicoRepository repository = new MedicoRepository(context);
        return repository.getAll();
    }

    public Medico obtenerMedico(Context context, int id)
    {
        MedicoRepository repository = new MedicoRepository(context);
        return repository.getMedicoById(id);
    }

    public int refresh(Context context, Medico medico)
    {
        MedicoRepository repository = new MedicoRepository(context);
        return repository.refresh(medico);
    }
}
