package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Repository.CentroMedicoRepository;
import farmaceutica.taes.domainmodel.Repository.ReporteGastosRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaReporteGastos
{
    public static List<ReporteGastos> obtenerReportesGastosPorVisitador(Context context, Visitador visitador){
        ReporteGastosRepository repository = new ReporteGastosRepository(context);
        return repository.getAllByVisitador(visitador);
    }

    public static List<ReporteGastos> obtenerReportesGastos(Context context){
        ReporteGastosRepository repository = new ReporteGastosRepository(context);
        return repository.getAll();
    }
}
