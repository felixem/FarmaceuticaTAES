package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;

/**
 * Created by felix on 13/05/15.
 */
public class FachadaValoracionProducto {
    public static List<ValoracionProducto> obtenerValoracionesProducto() {
        return new ArrayList<ValoracionProducto>(Arrays.asList(ValoracionProducto.class.getEnumConstants()));
    }

    public static int maxValue()
    {
        return ValoracionProducto.MUCHO.ordinal();
    }

    public static int obtenerCantidadValoraciones()
    {
        return ValoracionProducto.class.getEnumConstants().length;
    }

}
