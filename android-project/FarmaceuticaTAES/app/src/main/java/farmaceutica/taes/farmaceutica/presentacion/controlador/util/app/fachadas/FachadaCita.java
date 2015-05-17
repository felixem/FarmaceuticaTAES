package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Repository.CitaRepository;

/**
 * Created by Paco on 17/05/2015.
 */
public class FachadaCita {

    public List<Cita> obtenerCitasByRuta(Context context, Ruta ruta) throws SQLException {
        CitaRepository repository = new CitaRepository(context);
        return repository.getByRuta(ruta);
    }
}
