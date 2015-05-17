package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaCitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaCita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaRuta;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaRutas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by Jesus Ortega Mateu on 12/05/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ListaRutasFragment extends BaseFragment implements OnSpinnerListener
{


    private ListView listViewRutas;
    private String[] listaDetallesRuta;
    private FachadaRuta fachadaRuta;
    private FachadaCita fachadaCita;
    private Visitador visitador;

    SpinnerOnChangeAdapter spinnerRutas;
    SpinnerOnChangeAdapter spinnerCitas;
    private TextView txtRutas, txtCitas;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_rutas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewRutas = (ListView) view.findViewById(R.id.listViewDetallesRuta);
        spinnerRutas = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_rutas);
        spinnerCitas = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_citas);


        txtCitas = (TextView) view.findViewById(R.id.txt_citas);
        txtRutas = (TextView) view.findViewById(R.id.txt_rutas);

        fachadaRuta = new FachadaRuta();
        fachadaCita = new FachadaCita();

        //Vincular los listeners
        spinnerRutas.setOnSpinnerListener(this);
        spinnerCitas.setOnSpinnerListener(this);

        List<Ruta> listaRutas = null;
        visitador = new Visitador();
        visitador.setCodigo(1); //TODO


        try {
            listaRutas = fachadaRuta.obtenerRutasPorVisitador(view.getContext(),visitador);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        BaseAdapter adapter= new AdaptadorListaRutas(view.getContext(), listaRutas);
        spinnerRutas.setAdapter(adapter);




        //Establecer eventos en el spinner de centros médicos
        spinnerRutas.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()    {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {

                        //Meter en la lista las citas de la ruta
                        Ruta ruta = (Ruta)parent.getItemAtPosition(position);
                        List<Cita> citas = null;
                        try {
                            citas = fachadaCita.obtenerCitasByRuta(getActivity(), ruta);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        BaseAdapter adapter = new AdaptadorListaCitas(v.getContext(),citas);

                        spinnerCitas.setAdapter(adapter);
                        txtCitas.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

    }


    public static ListaRutasFragment newInstance() {

        // Instantiate a new fragment
        ListaRutasFragment fragment = new ListaRutasFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onAdapterChange(View v) {

        SpinnerAdapter adapter;
        //Gestionar set adapter a spinner centros médicos
        if(v.equals(spinnerRutas))
        {
            adapter = spinnerRutas.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                txtRutas.setText("No hay rutas asignadas");
                txtCitas.setVisibility(View.INVISIBLE);
                spinnerRutas.setVisibility(View.INVISIBLE);
                spinnerCitas.setVisibility(View.INVISIBLE);
            }
            else
            {
                txtRutas.setText("Selecciona una ruta");
                txtCitas.setVisibility(View.INVISIBLE);
                spinnerRutas.setVisibility(View.VISIBLE);
                spinnerCitas.setVisibility(View.INVISIBLE);
            }

        }else
            if(v.equals(spinnerCitas))
            {
                adapter = spinnerCitas.getAdapter();

                if(adapter.isEmpty())
                {
                    txtCitas.setText("No hay citas asignadas");
                    spinnerCitas.setVisibility(View.INVISIBLE);
                }else
                {
                    txtCitas.setText("Selecciona una cita");
                    spinnerCitas.setVisibility(View.VISIBLE);
                }


            }


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
