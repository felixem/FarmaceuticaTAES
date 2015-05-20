package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.VisitaMaterial;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaLugaresVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMateriales;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMisVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitasMaterial;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitasProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AlertaDialogo;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.MySession;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaLugarVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMaterialPromocional;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaValoracionProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */

public class VerVisitaFragment extends BaseFragment{

    List<VisitaProducto> visitaProductos;
    List<VisitaMaterial> visitaMateriales;


    //Campos con información introducida
    DatePicker datepicker_fecha_visita;
    TimePicker timepicker;

    CheckBox checkbox_acompanyado;
    ListView productos, materiales;


    TextView txt_titulo_detalles_visita,txt_medico,
            txt_medico_dato, txt_lugar_visita,txt_lugar_visita_dato,
            txt_fecha_visita, txt_hora_inicio, hora_inicio, txt_duracion_datos, txt_observaciones,txt_observaciones_dato ;

    Button button_volver_atras;

    private Visita visita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ver_visita, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        MySession session = (MySession)getActivity().getApplication();
        visita = session.getVisita();

        txt_observaciones_dato = (TextView) view.findViewById(R.id.txt_observaciones_dato);
        txt_observaciones = (TextView) view.findViewById(R.id.txt_observaciones);
        txt_duracion_datos = (TextView) view.findViewById(R.id.txt_duracion_datos);

        txt_titulo_detalles_visita = (TextView) view.findViewById(R.id.txt_titulo_detalles_visita);

        timepicker = (TimePicker) view.findViewById(R.id.hora_inicio);

        button_volver_atras = (Button) view.findViewById(R.id.button_volver_atras);
        txt_medico = (TextView) view.findViewById(R.id.txt_medico);
        txt_medico_dato = (TextView) view.findViewById(R.id.txt_medico_dato);
        txt_lugar_visita = (TextView) view.findViewById(R.id.txt_lugar_visita);
        txt_lugar_visita_dato = (TextView) view.findViewById(R.id.txt_lugar_visita_dato);
        txt_fecha_visita = (TextView) view.findViewById(R.id.txt_fecha_visita);
        txt_hora_inicio = (TextView) view.findViewById(R.id.txt_hora_inicio);


        productos = (ListView) view.findViewById(R.id.list_productos);
        materiales = (ListView) view.findViewById(R.id.list_materiales);


//TODO

        //BaseAdapter adapterProductos = new AdaptadorListaProductos(getActivity(), fachadaProducto.obtener(getActivity()));
        //productos.setAdapter(adapterProductos);


        //Obtener la lista de materiales
        //BaseAdapter adapterMateriales = new AdaptadorListaMisVisitas(getActivity(), FachadaVisita.obtenerVisitasPorVisitador(getActivity(), visitador));
       // materiales.setAdapter(adapterMateriales);

        datepicker_fecha_visita = (DatePicker) view.findViewById(R.id.date_picker_fecha_visita);

        checkbox_acompanyado = (CheckBox) view.findViewById(R.id.checkBox_acompanyado);
        checkbox_acompanyado.setEnabled(false);

        FachadaMedico fachadaMedico = new FachadaMedico();
        Medico m = FachadaMedico.obtenerMedico(getActivity(), visita.getMedico().getId());

        FachadaVisita fachadaVisita = new FachadaVisita();
        visita = fachadaVisita.ObtenerVisitasPorId(getActivity(),visita.getId());

        txt_medico_dato.setText(m.getNombre() + " " + m.getApellidos());
        txt_lugar_visita_dato.setText(visita.getLugarVisita().toString());

        checkbox_acompanyado.setChecked(visita.getAcompanyado());
        txt_duracion_datos.setText(""+visita.getMinutos());
        txt_observaciones_dato.setText(visita.getObservaciones()==""? visita.getObservaciones() : "Sin observaciones");



        Date d = visita.getFechaVisita();

        Calendar c = Calendar.getInstance();
        c.setTime(d);

        timepicker.setCurrentMinute(c.get(Calendar.MINUTE));
        timepicker.setCurrentHour(c.get(Calendar.HOUR));

        timepicker.setEnabled(false);
        datepicker_fecha_visita.setEnabled(false);



        int xxday = c.get(Calendar.DATE);
        int xxmonth = c.get(Calendar.MONTH);
        int xxyear = c.get(Calendar.YEAR);

        datepicker_fecha_visita.init(xxyear, xxmonth, xxday, null);


        Calendar cal = Calendar.getInstance();
        cal.setTime(visita.getFechaVisita());

        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

        String formatted = format1.format(cal.getTime());
        txt_titulo_detalles_visita.setText("Detalles de la visita del " + formatted);



        button_volver_atras.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().onBackPressed();


                    }
                }
        );


    }


}
