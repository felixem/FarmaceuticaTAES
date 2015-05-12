package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import java.util.Date;
import android.os.Build;
import android.os.Bundle;
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


    protected void OnCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ver_detalles_visita);

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
