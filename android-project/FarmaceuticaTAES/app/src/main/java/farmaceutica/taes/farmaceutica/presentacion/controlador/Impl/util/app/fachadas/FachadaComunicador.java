package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas;

import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.Comunicador;

/**
 * Created by Javi on 12/05/2015.
 */
public class FachadaComunicador {

    public void ComunicarDestino(Class<?> datos){
        Comunicador.setObject(new Object[]{datos});
    }

    //Prepara una visita para ser recibida por otra activity
    public void ComunicarVisita(Visita visita, Class<?> datos){
        Comunicador.setObject(new Object[]{visita, datos});
    }

    //Recupera una visita enviada en la posición 0
    public Visita RecibirUniversidadPosicion0(){
        return (Visita)((Object[])Comunicador.getObject())[0];
    }

    public void volverAtras()
    {
        Comunicador.getObjectAnterior();
    }

}
