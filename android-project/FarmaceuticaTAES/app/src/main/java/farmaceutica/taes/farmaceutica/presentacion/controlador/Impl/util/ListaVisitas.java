package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.MedicoRepository;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaComunicador;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaVisita;
import android.app.Activity;

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
        visita = fachadaComunicador.RecibirVisitaPosicion0();
        txt.setText("Visita: " + visita.getFechaVisita().toString() +" con el doctor: " + repositorio.getMedicoById(visita.getMedico().getId()).getNombre() + " " + repositorio.getMedicoById(visita.getMedico().getId()).getApellidos());

        destino = null;

        //Creamos el adaptador para el ListView
        List<Visita> visitas = fachadaVisita.obtenerVisitas(this);
        BaseAdapter adapter= new AdaptadorListaVisitas(this, visitas);

        lv=(ListView) findViewById(R.id.txt_listavisitas);
        lv.setAdapter(adapter);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgressDialog dialog = ProgressDialog.show(ListaVisitas.this, "",
                        "Cargando...", true);
                //Si retorna de la activity podemos perder la referencia al destino. Así aseguramos su permanencia
                if (destino == null)
                    destino = fachadaComunicador.RecibirDestinoPosicionFinal();

                Intent intent = new Intent(ListaVisitas.this, destino);
                startActivity(intent);
                dialog.cancel();
            }
    });
    }

    public void onBackPressed() {

        fachadaComunicador.volverAtras();
        super.onBackPressed();
    }

}