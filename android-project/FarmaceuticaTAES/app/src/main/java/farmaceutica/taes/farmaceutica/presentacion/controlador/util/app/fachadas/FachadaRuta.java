package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Repository.CitaRepository;
import farmaceutica.taes.domainmodel.Repository.RutaRepository;
import farmaceutica.taes.domainmodel.Repository.VisitaRepository;

/**
 * Created by Javi on 12/05/2015.
 */
public class FachadaRuta {

    public List<Ruta> obtenerRutas(Context context){
        RutaRepository repository = new RutaRepository(context);
        return repository.getAll();
    }

    public List<Ruta> obtenerRutasPorVisitador(Context context, Visitador visitador) throws SQLException {
        RutaRepository repository = new RutaRepository(context);
        return repository.getByVisitador(visitador);
    }


    public void crearCita(Context context, Ruta ruta) throws SQLException {
        RutaRepository repository = new RutaRepository(context);
        repository.create(ruta);
    }


    public void borrarRuta(Context context, Ruta ruta) throws SQLException {
        RutaRepository repository = new RutaRepository(context);
        repository.delete(ruta);
    }

    public void crearRuta(Context context, Ruta ruta) throws SQLException {
        RutaRepository repository = new RutaRepository(context);
        ruta.setValidada(true);

        repository.create(ruta);

    }
}
