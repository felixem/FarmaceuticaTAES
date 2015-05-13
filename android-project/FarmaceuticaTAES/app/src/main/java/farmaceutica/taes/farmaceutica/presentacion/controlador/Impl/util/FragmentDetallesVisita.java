package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import java.util.Date;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import farmaceutica.taes.farmaceutica.R;

/**
 * Created by Jesus Ortega Mateu on 12/05/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentDetallesVisita extends Fragment
{


    private ListView detallesVisita;
    private String[] listaDetallesVisitas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ver_detalles_visita, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // private String[] listaDatos = {};

    /*detallesVisita = (ListView)findViewById(R.id.listViewDetallesVisita);
    adaptador = new ArrayAdapter<String>(this,android.R.layout);*/

    /*
    private ArrayList<String> detalles;
    private Date fechaVisita;
    private Date fechaReporte;
    private String lugar_visita;
    private String Observaciones;
    private int minutos;
    private int productosVendidos;
    private boolean acompañado;



    public FragmentDetallesVisita()
    {
        detalles = {};
        detalles.
    }

    public Date GetFechaVisita(){return fechaVisita;}

    public void SetFechaVisita(){this.fechaVisita = fechaVisita;}*/


}
