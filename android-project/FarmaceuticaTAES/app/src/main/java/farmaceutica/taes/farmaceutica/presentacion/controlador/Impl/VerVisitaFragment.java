package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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

public class VerVisitaFragment extends Dialog {

    List<VisitaProducto> visitaProductos;
    List<VisitaMaterial> visitaMateriales;

    private Visita visita;
    private Activity activity;
    private Context context ;


    public void setVisita(Visita v ) {visita = v;}
    public Visita getVisita(){return visita;}


    //Campos con información introducida
    DatePicker datepicker_fecha_visita;
    TimePicker timepicker;

    CheckBox checkbox_acompanyado;
    ListView productos, materiales;


    TextView txt_titulo_detalles_visita,txt_medico,
            txt_medico_dato, txt_lugar_visita,txt_lugar_visita_dato,
            txt_fecha_visita, txt_hora_inicio, hora_inicio, txt_duracion_datos,
            txt_observaciones,txt_observaciones_dato, txt_productos_entregados, txt_materiales_entregados ;

    Button button_volver_atras;


    public VerVisitaFragment(Context context, int theme, Activity a) {
        super(context, theme);
        activity = a;
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_ver_visita);
    }

    public void cargarDialog(){

        txt_materiales_entregados = (TextView) findViewById(R.id.txt_materiales_entregados);
        txt_productos_entregados = (TextView) findViewById(R.id.txt_productos_entregados);

        txt_observaciones_dato = (TextView) findViewById(R.id.txt_observaciones_dato);
        txt_observaciones = (TextView) findViewById(R.id.txt_observaciones);
        txt_duracion_datos = (TextView) findViewById(R.id.txt_duracion_datos);

        txt_titulo_detalles_visita = (TextView) findViewById(R.id.txt_titulo_detalles_visita);

        timepicker = (TimePicker) findViewById(R.id.hora_inicio);

        button_volver_atras = (Button) findViewById(R.id.button_volver_atras);
        txt_medico = (TextView) findViewById(R.id.txt_medico);
        txt_medico_dato = (TextView) findViewById(R.id.txt_medico_dato);
        txt_lugar_visita = (TextView) findViewById(R.id.txt_lugar_visita);
        txt_lugar_visita_dato = (TextView) findViewById(R.id.txt_lugar_visita_dato);
        txt_fecha_visita = (TextView) findViewById(R.id.txt_fecha_visita);
        txt_hora_inicio = (TextView) findViewById(R.id.txt_hora_inicio);


        productos = (ListView) findViewById(R.id.list_productos);
        materiales = (ListView) findViewById(R.id.list_materiales);


//TODO

        //BaseAdapter adapterProductos = new AdaptadorListaProductos(getActivity(), fachadaProducto.obtener(getActivity()));
        //productos.setAdapter(adapterProductos);


        //Obtener la lista de materiales
        //BaseAdapter adapterMateriales = new AdaptadorListaMisVisitas(getActivity(), FachadaVisita.obtenerVisitasPorVisitador(getActivity(), visitador));
       // materiales.setAdapter(adapterMateriales);

        datepicker_fecha_visita = (DatePicker) findViewById(R.id.date_picker_fecha_visita);

        checkbox_acompanyado = (CheckBox) findViewById(R.id.checkBox_acompanyado);
        checkbox_acompanyado.setEnabled(false);

        FachadaMedico fachadaMedico = new FachadaMedico();
        Medico m = FachadaMedico.obtenerMedico(context, visita.getMedico().getId());

        FachadaVisita fachadaVisita = new FachadaVisita();
        visita = fachadaVisita.ObtenerVisitasPorId(context,visita.getId());

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
                        dismiss();


                    }
                }
        );


    }


}
