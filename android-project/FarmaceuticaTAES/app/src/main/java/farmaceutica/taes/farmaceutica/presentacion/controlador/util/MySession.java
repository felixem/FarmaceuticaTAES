package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.app.Application;
import farmaceutica.taes.domainmodel.Model.Visitador;

/**
 * Created by John on 19/05/2015.
 */
public class MySession extends Application {

    private Visitador visitador;

    public void setVisitador(Visitador visitador){
        this.visitador=visitador;
    }

    public Visitador getVisitador(){
        return visitador;
    }
}
