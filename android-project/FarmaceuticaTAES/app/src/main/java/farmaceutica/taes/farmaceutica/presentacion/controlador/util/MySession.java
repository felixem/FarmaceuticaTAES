package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.app.Application;

import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Visitador;

/**
 * Created by John on 19/05/2015.
 */
public class MySession extends Application {

    private Visitador visitador;

    private Visita visita;

    public void setVisitador(Visitador visitador){
        this.visitador=visitador;
    }

    public Visitador getVisitador(){
        return visitador;
    }

    public void setVisita(Visita v) {this.visita = v;}

    public Visita getVisita(){return visita;}

}
