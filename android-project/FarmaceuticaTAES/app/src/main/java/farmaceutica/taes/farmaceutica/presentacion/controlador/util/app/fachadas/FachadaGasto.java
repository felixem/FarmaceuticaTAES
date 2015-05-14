package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Repository.GastoRepository;
import farmaceutica.taes.domainmodel.Repository.ReporteGastosRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaGasto
{
    public static List<Gasto> obtenerGastosPorReporteGasto(Context context, ReporteGastos reporte){
        GastoRepository repository = new GastoRepository(context);
        return repository.getAllByReporteGastos(reporte);
    }

    public static List<ReporteGastos> obtenerReportesGastos(Context context){
        ReporteGastosRepository repository = new ReporteGastosRepository(context);
        return repository.getAll();
    }
}
