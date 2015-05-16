package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Repository.MedicoRepository;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaLugarVisita
{
    public static List<LugarVisita> obtenerLugaresVisita()
    {
       return new ArrayList<LugarVisita>(Arrays.asList(LugarVisita.class.getEnumConstants()));
    }

}
