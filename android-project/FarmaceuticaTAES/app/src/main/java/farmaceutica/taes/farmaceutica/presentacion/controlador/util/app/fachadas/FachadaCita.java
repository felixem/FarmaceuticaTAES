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


    public void validarHoras(Cita cita) throws Exception {
        comprobarHora(cita.getHoraInicio());
        comprobarHora(cita.getHoraFin());
        comprobarMinutos(cita.getMinutoInicio());
        comprobarMinutos(cita.getMinutoFin());


    }


    public void borrarCita(Context context, Cita cita) throws SQLException {
        CitaRepository repository = new CitaRepository(context);
        repository.delete(cita);

    }


    public void crearCita(Context context, Cita cita) throws SQLException {
        CitaRepository repository = new CitaRepository(context);
        repository.create(cita);
    }


    private void comprobarHora(int hora) throws Exception {
        if(hora<0 || hora>23)
            throw new Exception("");

    }

    private void comprobarMinutos(int min) throws Exception {
        if(min<0 || min>59)
            throw new Exception("");

    }

    public void modificarCita(Context context, Cita cita) {
        CitaRepository repository = new CitaRepository(context);
        repository.update(cita);
    }
}
