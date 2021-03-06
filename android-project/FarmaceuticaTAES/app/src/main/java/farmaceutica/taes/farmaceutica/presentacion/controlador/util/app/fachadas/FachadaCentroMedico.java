package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Repository.CentroMedicoRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaCentroMedico
{
    public static List<CentroMedico> obtenerCentrosMedicosPorArea(Context context, AreaHospitalaria area){
        CentroMedicoRepository repository = new CentroMedicoRepository(context);
        return repository.getAllByAreaHospitalaria(area);
    }

    public static List<CentroMedico> obtenerCentrosMedicos(Context context){
        CentroMedicoRepository repository = new CentroMedicoRepository(context);
        return repository.getAll();
    }

    public static CentroMedico obtenerCentro(Context context, int id)
    {
        CentroMedicoRepository repository = new CentroMedicoRepository(context);
        return repository.getCentroMedicoById(id);
    }

    public static int refresh(Context context, CentroMedico centro)
    {
        CentroMedicoRepository repository = new CentroMedicoRepository(context);
        return repository.refresh(centro);
    }
}
