package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Repository.AreaHospitalariaRepository;
import farmaceutica.taes.domainmodel.Repository.CentroMedicoRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaAreaHospitalaria
{
    public static List<AreaHospitalaria> obtenerAreasHospitalarias(Context context){
        AreaHospitalariaRepository repository = new AreaHospitalariaRepository(context);
        return repository.getAll();
    }

    public static AreaHospitalaria obtenerAreaHospitalaria(Context context, int id)
    {
        AreaHospitalariaRepository repository = new AreaHospitalariaRepository(context);
        return repository.getAreaById(id);
    }

    public static AreaHospitalaria obtenerAreaHospitalariaPorVisitador(Context context, Visitador visitador)
    {
        AreaHospitalariaRepository repository = new AreaHospitalariaRepository(context);
        try {
            return repository.getAreaByVisitador(visitador);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int refresh(Context context, AreaHospitalaria area)
    {
        AreaHospitalariaRepository repository = new AreaHospitalariaRepository(context);
        try {
            return repository.refresh(area);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
