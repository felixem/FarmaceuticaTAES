package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi on 12/05/2015.
 */
public class Comunicador {

    private static Object object= null;

    public static Object getObject() {
        return object;
    }

    public static void setObject(Object obj) {
        object = obj;
        listaComunicadores.add(obj);
    }

    public static Object getObjectAnterior()
    {
        int L = listaComunicadores.size()-1;
        if(L>0)
            listaComunicadores.remove(listaComunicadores.get(L));
        L = listaComunicadores.size()-1;
        if(L>0)
            object = listaComunicadores.get(L);
        else
            object = null;

        return object;
    }

    private static List<Object> listaComunicadores = new ArrayList<Object>();
}
