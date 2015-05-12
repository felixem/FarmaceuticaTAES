package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.MedicoRepository;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaComunicador;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaVisita;

/**
 * Created by Javi on 12/05/2015.
 */
public class ListaVisitas extends ActivityBase {

    private ListView lv;
    private TextView txt;
    private Visita visita;
    private FachadaVisita fachadaVisita;
    private FachadaComunicador fachadaComunicador;
    private Class<?> destino;
    private MedicoRepository repositorio;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listavisitas);

        fachadaVisita = new FachadaVisita();
        fachadaComunicador = new FachadaComunicador();

        txt= (TextView) findViewById(R.id.txt_visita_elegida);
        visita = fachadaComunicador.RecibirUniversidadPosicion0();
        txt.setText("Visita: " + visita.getFechaVisita().toString() +" con el doctor: " + repositorio.getMedicoById(visita.getMedico().getId()).getNombre() + " " + repositorio.getMedicoById(visita.getMedico().getId()).getApellidos());

        destino = null;

        //Creamos el adaptador para el ListView
        ArrayList<Visita> visitas = fachadaVisita.obtenerVisitas(this);



    }



    @Override
    public void onBackPressed() {

        fachadaComunicador.volverAtras();
        super.onBackPressed();
    }

}
