package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaCitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaLugaresVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMateriales;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitasProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AlertaDialogo;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaCita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaLugarVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMaterialPromocional;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
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
    private TextView txtRutas, txtCitas, txtMedico, txtLugar, txtInicio, txtFin, txtComentarios, txtPuntosInicio, txtPuntosFin;
    private Spinner spinnerLugar;
    private EditText et_hora_inicio, et_hora_fin, et_min_inicio, et_min_fin, et_lugar, et_direccion, et_comentarios;
    private Button btn_guardar, btn_crear_ruta, button_borrar_ruta, btn_borrar_cita;

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
        txtPuntosInicio = (TextView) view.findViewById(R.id.txt_separador_inicio);
        txtPuntosFin = (TextView) view.findViewById(R.id.txt_separador_fin);

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
        button_borrar_ruta = (Button) view.findViewById(R.id.button_borrar_ruta);
        btn_borrar_cita = (Button) view.findViewById(R.id.button_borrar_cita);


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

        //Pulsado largo del spinner de productos ofertados o de materiales entregados
        final Handler actionHandler = new Handler();
        final Runnable runnableCita = new RunnableAddCita();






        spinnerRutas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    actionHandler.postDelayed(runnableCita, 1000);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    actionHandler.removeCallbacks(runnableCita);
                }
                return false;
            }
        });







        btn_borrar_cita.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            fachadaCita.borrarCita(getActivity(), cita);


                            List<Cita> citas = fachadaCita.obtenerCitasByRuta(getActivity(), cita.getRuta());

                            BaseAdapter adapter = new AdaptadorListaCitas(getActivity(),citas);
                            spinnerCitas.setAdapter(adapter);
                            cita = (Cita)spinnerCitas.getSelectedItem();
                            citaDatos(cita);
                            if(citas.size()==0)
                                citaVisible(false);

                        }catch(SQLException e)
                        {
                            AlertaDialogo ad = new AlertaDialogo();
                            ad.setMensaje("No se ha podido eliminar la cita.");
                            ad.setTitulo("Información");
                            ad.setBoton1("OK");
                            ad.setFlags(true);
                            ad.show(getFragmentManager(), "FragmentAlert");
                            return;
                        }


                        CharSequence text = "Se ha eliminado la cita";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getActivity(), text, duration);
                        toast.show();
                    }
                }
        );


        button_borrar_ruta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Mostrar diálogo de confirmación de operación
                        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
                        dialogo1.setTitle("Eliminar cita");
                        dialogo1.setMessage("¿Desea borrar la ruta " + ((Ruta)spinnerRutas.getSelectedItem()).getFecha().toString() + "?");
                        dialogo1.setCancelable(false);
                        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                Ruta ruta = (Ruta)spinnerRutas.getSelectedItem();
                                try {
                                    fachadaRuta.borrarRuta(getActivity(), (Ruta) spinnerRutas.getSelectedItem());
                                } catch (SQLException e) {

                                }
                                Visitador visitador1 = new Visitador();
                                visitador1.setCodigo(1);
                                List<Ruta> listaRutasAux = null;
                                try {
                                    listaRutasAux = fachadaRuta.obtenerRutasPorVisitador(getActivity(),visitador1);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                BaseAdapter adapter= new AdaptadorListaRutas(getActivity(), listaRutasAux);
                                spinnerRutas.setAdapter(adapter);

                            }
                        });
                        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                dialogo1.dismiss();
                            }
                        });
                        dialogo1.show();


                        CharSequence text = "Se ha eliminado la cita";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getActivity(), text, duration);
                        toast.show();
                    }
                }
        );





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

    private void actualizarCita() {


    }


    /** Dialog */
    private void crearMaterialEntregado()
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity(), R.style.AppTheme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_crear_cita);
        //Configurar la vista
        configurarVisitaMaterial(dialog);

        dialog.show();
    }


    //Configurar el dialog de Visita Material
    private void configurarVisitaMaterial(final Dialog dialog)
    {
        final SpinnerOnChangeAdapter spinnerMedicosForm;
        final TextView txtTitle;
        final Spinner spinnerLugarForm;
        final EditText et_hora_inicioForm, et_hora_finForm, et_min_inicioForm, et_min_finForm, et_lugarForm, et_direccionForm, et_comentariosForm;
        final Button btn_guardarForm;

        spinnerMedicosForm = (SpinnerOnChangeAdapter) dialog.findViewById(R.id.spinner_medicos_form);
        spinnerLugarForm = (Spinner) dialog.findViewById(R.id.spinner_tipo_lugar_form);

        et_hora_inicioForm = (EditText) dialog.findViewById(R.id.edit_text_hora_inicio_form);
        et_hora_finForm = (EditText) dialog.findViewById(R.id.edit_text_hora_fin_form);
        et_min_inicioForm = (EditText) dialog.findViewById(R.id.edit_text_minuto_inicio_form);
        et_min_finForm = (EditText) dialog.findViewById(R.id.edit_text_minuto_fin_form);

        et_lugarForm = (EditText) dialog.findViewById(R.id.edit_text_lugar_form);
        et_direccionForm = (EditText) dialog.findViewById(R.id.edit_text_direccion_form);
        et_comentariosForm = (EditText) dialog.findViewById(R.id.edit_text_comentarios_form);

        btn_guardarForm = (Button) dialog.findViewById(R.id.button_crear_cita_form);

        txtTitle = (TextView) dialog.findViewById(R.id.txt_titulo_crear_cita);



        //Vincular los productos ofertables
        BaseAdapter adapterMedico = new AdaptadorListaMedicos(getActivity(),FachadaMedico.obtenerMedicos(getActivity()));
        spinnerMedicosForm.setAdapter(adapterMedico);

        BaseAdapter adapterTipoLugar = new AdaptadorListaLugaresVisita(getActivity(), FachadaLugarVisita.obtenerLugaresVisita());
        spinnerLugarForm.setAdapter(adapterTipoLugar);



        //Establecer listener para los materiales entregados
        spinnerLugarForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_guardarForm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            cita.setTipoLugar((LugarVisita)spinnerLugarForm.getSelectedItem());
                            cita.setMedico((Medico)spinnerMedicosForm.getSelectedItem());
                            cita.setLugar(et_lugarForm.getText().toString());
                            cita.setDireccion(et_direccionForm.getText().toString());
                            //cita.setRuta((Ruta)spinnerRutas.getSelectedItem());
                            try {
                                cita.setHoraFin(Integer.parseInt(et_hora_finForm.getText().toString()));
                                cita.setHoraInicio(Integer.parseInt(et_hora_inicioForm.getText().toString()));
                                cita.setMinutoFin(Integer.parseInt(et_min_finForm.getText().toString()));
                                cita.setMinutoInicio(Integer.parseInt(et_min_inicioForm.getText().toString()));

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


                            fachadaCita.crearCita(getActivity(), cita);
                            List<Cita> citas = fachadaCita.obtenerCitasByRuta(getActivity(), cita.getRuta());
                            BaseAdapter adapter = new AdaptadorListaCitas(getActivity(),citas);
                            spinnerCitas.setAdapter(adapter);

                            cita = (Cita)spinnerCitas.getSelectedItem();
                            citaDatos(cita);
                            if(citas.size()==0)
                                citaVisible(false);

                        }catch(SQLException e)
                        {
                            AlertaDialogo ad = new AlertaDialogo();
                            ad.setMensaje("No se ha podido crear la cita. Revise los campos.");
                            ad.setTitulo("Información");
                            ad.setBoton1("OK");
                            ad.setFlags(true);
                            ad.show(getFragmentManager(), "FragmentAlert");
                            return;
                        }



                        dialog.dismiss();
                        CharSequence text = "Se ha creado la cita";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getActivity(), text, duration);
                        toast.show();
                    }
                }
        );




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
            txtPuntosInicio.setVisibility(View.INVISIBLE);
            txtPuntosFin.setVisibility(View.INVISIBLE);

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
            txtPuntosInicio.setVisibility(View.VISIBLE);
            txtPuntosFin.setVisibility(View.VISIBLE);

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


    //Clase para el evento de crear una cita
    private class RunnableAddCita implements Runnable
    {
        @Override
        public void run() {

            //Si el spinner está vacío no hacer nada
            if(spinnerRutas.getAdapter().isEmpty())
                return;

            //Obtener el producto
            final int pos = spinnerRutas.getSelectedItemPosition();
            final Ruta ruta = (Ruta)spinnerRutas.getItemAtPosition(pos);

            //Mostrar diálogo de confirmación de operación
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
            dialogo1.setTitle("Añadir cita");
            dialogo1.setMessage("¿Desea añadir una cita en la ruta " + ruta.getFecha().toString() + "?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    cita.setRuta((Ruta)spinnerRutas.getSelectedItem());
                    crearMaterialEntregado();
                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    dialogo1.dismiss();
                }
            });
            dialogo1.show();
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



