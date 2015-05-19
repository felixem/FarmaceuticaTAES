package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaCitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AlertaDialogo;
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

public class ListaRutasFragment extends BaseFragment implements OnSpinnerListener
{
    private FachadaRuta fachadaRuta;
    private FachadaCita fachadaCita;
    private Visitador visitador;

    private SpinnerOnChangeAdapter spinnerRutas, spinnerMedicos;
    private SpinnerOnChangeAdapter spinnerCitas;
    private TextView txtRutas, txtCitas, txtMedico, txtLugar, txtInicio, txtFin, txtComentarios;
    private Spinner spinnerLugar;
    private EditText et_hora_inicio, et_hora_fin, et_min_inicio, et_min_fin, et_lugar, et_direccion, et_comentarios;
    private Button btn_guardar, btn_crear_ruta, btn_crear_cita;

    private FachadaMedico fachadaMedico;

    private Cita cita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_rutas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cita = new Cita();

        txtComentarios = (TextView) view.findViewById(R.id.txt_comentarios);
        et_comentarios = (EditText) view.findViewById(R.id.edit_text_comentarios);

        spinnerRutas = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_rutas);
        spinnerCitas = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_citas);
        spinnerMedicos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_medicos);


        txtCitas = (TextView) view.findViewById(R.id.txt_citas);
        txtRutas = (TextView) view.findViewById(R.id.txt_rutas);
        txtMedico= (TextView) view.findViewById(R.id.txt_medico);
        txtLugar = (TextView) view.findViewById(R.id.txt_lugar);
        txtInicio = (TextView) view.findViewById(R.id.txt_hora_inicio);
        txtFin = (TextView) view.findViewById(R.id.txt_hora_fin);

        spinnerLugar = (Spinner) view.findViewById(R.id.spinner_tipo_lugar);

        et_hora_inicio = (EditText) view.findViewById(R.id.edit_text_hora_inicio);
        et_hora_fin = (EditText) view.findViewById(R.id.edit_text_hora_fin);
        et_min_fin = (EditText) view.findViewById(R.id.edit_text_minuto_fin);
        et_min_inicio=(EditText) view.findViewById(R.id.edit_text_minuto_inicio);

        et_lugar = (EditText) view.findViewById(R.id.edit_text_lugar);
        et_direccion = (EditText) view.findViewById(R.id.edit_text_direccion);

        btn_guardar = (Button) view.findViewById(R.id.button_guardar_modificaciones);
        btn_crear_ruta = (Button) view.findViewById(R.id.button_crear_ruta);
        btn_crear_cita = (Button) view.findViewById(R.id.button_crear_cita);


        String[] arraySpinner = new String[] {
                "HOSPITAL", "CONSULTA", "CONGRESO", "OTRO"
        };

        ArrayAdapter<String> adapterLugar = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        spinnerLugar.setAdapter(adapterLugar);

        fachadaMedico = new FachadaMedico();
        fachadaRuta = new FachadaRuta();
        fachadaCita = new FachadaCita();

        //Vincular los listeners
        spinnerRutas.setOnSpinnerListener(this);
        spinnerCitas.setOnSpinnerListener(this);
        spinnerMedicos.setOnSpinnerListener(this);

        List<Ruta> listaRutas = null;
        visitador = new Visitador();
        visitador.setCodigo(1); //TODO



        citaVisible(false);

        try {
            listaRutas = fachadaRuta.obtenerRutasPorVisitador(getActivity(),visitador);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        BaseAdapter adapter= new AdaptadorListaRutas(getActivity(), listaRutas);
        spinnerRutas.setAdapter(adapter);


        btn_guardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            cita.setHoraFin(Integer.parseInt(et_hora_fin.getText().toString()));
                            cita.setHoraInicio(Integer.parseInt(et_hora_inicio.getText().toString()));
                            cita.setMinutoFin(Integer.parseInt(et_min_fin.getText().toString()));
                            cita.setMinutoInicio(Integer.parseInt(et_min_inicio.getText().toString()));

                            fachadaCita.validarHoras(cita);
                        }catch(Exception e)
                        {
                            AlertaDialogo ad = new AlertaDialogo();
                            ad.setMensaje("La hora de inicio o fin de la cita es incorrecta.");
                            ad.setTitulo("Hora incorrecta");
                            ad.setBoton1("OK");
                            ad.setFlags(true);
                            ad.show(getFragmentManager(), "FragmentAlert");
                            return;
                        }


                        cita.setComentario(et_comentarios.getText().toString());
                        cita.setDireccion(et_direccion.getText().toString());
                        cita.setLugar(et_lugar.getText().toString());

                        cita.setMedico((Medico)spinnerMedicos.getSelectedItem());

                        cita.setTipoLugar(spinnerLugar.getSelectedItemId());

                        fachadaCita.modificarCita(getActivity(),cita);


                        CharSequence text = "Se ha modificado la cita";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getActivity(), text, duration);
                        toast.show();
                    }
                }
        );


        //Establecer eventos en el spinner de rutas
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
                            BaseAdapter adapter = new AdaptadorListaCitas(getActivity(),citas);

                            spinnerCitas.setAdapter(adapter);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }



                        if(citas!=null && citas.size()!=0) {
                            cita = citas.get(0);
                            citaDatos(cita);
                            citaVisible(true);
                        }else
                        {
                            citaVisible(false);
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });


        //Establecer eventos en el spinner de citas
        spinnerCitas.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()    {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {


                        List<Medico> medicos = FachadaMedico.obtenerMedicos(getActivity());

                        BaseAdapter adapter = new AdaptadorListaMedicos(getActivity(),medicos);

                        spinnerMedicos.setAdapter(adapter);

                        citaVisible(true);

                        citaDatos((Cita) parent.getItemAtPosition(position));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

    }



    private void citaDatos(Cita cita) {
        this.cita = cita;
        et_direccion.setText(cita.getDireccion());
        et_lugar.setText(cita.getLugar());
        et_min_inicio.setText(cita.getMinutoInicio().toString());
        et_min_fin.setText(cita.getMinutoFin().toString());
        et_hora_fin.setText(cita.getHoraFin().toString());
        et_hora_inicio.setText(cita.getHoraInicio().toString());

        Medico m = FachadaMedico.obtenerMedico(this.getActivity(),cita.getMedico().getId());

        BaseAdapter adapter = (BaseAdapter) spinnerMedicos.getAdapter();

        for(int i = 0; i< spinnerMedicos.getCount(); ++i)
        {


            if(m.equals((Medico) (adapter.getItem(i))))
            {
                spinnerMedicos.setSelection(i);
                break;
            }
        }
        int i = 0;
        switch(cita.getTipoLugar())
        {
            case HOSPITAL:
                i = 0;
                break;

            case CONSULTA:
                i=1;
                break;
            case CONGRESO:
                i=2;
                break;
            default:
                i=3;

        }

        spinnerLugar.setSelection(i);


        et_comentarios.setText(cita.getComentario());

    }

    private void citaVisible(boolean b)
    {
        if(!b)
        {
            txtLugar.setVisibility(View.INVISIBLE);
            txtMedico.setVisibility(View.INVISIBLE);
            txtFin.setVisibility(View.INVISIBLE);
            txtInicio.setVisibility(View.INVISIBLE);
            txtComentarios.setVisibility(View.INVISIBLE);
            btn_guardar.setVisibility(View.INVISIBLE);

            et_direccion.setVisibility(View.INVISIBLE);
            et_lugar.setVisibility(View.INVISIBLE);
            et_min_inicio.setVisibility(View.INVISIBLE);
            et_min_fin.setVisibility(View.INVISIBLE);
            et_hora_fin.setVisibility(View.INVISIBLE);
            et_hora_inicio.setVisibility(View.INVISIBLE);
            et_comentarios.setVisibility(View.INVISIBLE);

            spinnerMedicos.setVisibility(View.INVISIBLE);
            spinnerLugar.setVisibility(View.INVISIBLE);

        }else
        {
            txtLugar.setVisibility(View.VISIBLE);
            txtMedico.setVisibility(View.VISIBLE);
            txtFin.setVisibility(View.VISIBLE);
            txtInicio.setVisibility(View.VISIBLE);
            txtComentarios.setVisibility(View.VISIBLE);
            btn_guardar.setVisibility(View.VISIBLE);

            et_direccion.setVisibility(View.VISIBLE);
            et_lugar.setVisibility(View.VISIBLE);
            et_min_inicio.setVisibility(View.VISIBLE);
            et_min_fin.setVisibility(View.VISIBLE);
            et_hora_fin.setVisibility(View.VISIBLE);
            et_hora_inicio.setVisibility(View.VISIBLE);
            et_comentarios.setVisibility(View.VISIBLE);

            spinnerMedicos.setVisibility(View.VISIBLE);
            spinnerLugar.setVisibility(View.VISIBLE);
        }

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
                spinnerRutas.setVisibility(View.INVISIBLE);
                spinnerCitas.setVisibility(View.INVISIBLE);
            }
            else
            {
                txtRutas.setText("Selecciona una ruta");
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
