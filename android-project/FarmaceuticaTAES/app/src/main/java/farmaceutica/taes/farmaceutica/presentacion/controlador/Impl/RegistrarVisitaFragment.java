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
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
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
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitasMaterial;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitasProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AlertaDialogo;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.MySession;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaAreaHospitalaria;
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
public class RegistrarVisitaFragment extends BaseFragment{

    SpinnerOnChangeAdapter spinnerMedicos;
    SpinnerOnChangeAdapter spinnerLugarVisita;
    SpinnerOnChangeAdapter spinnerProductosOfertados;
    SpinnerOnChangeAdapter spinnerMaterialesEntregados;

    //Listas con el contenido de los spinner
    List<Medico> medicos;
    List<LugarVisita> lugaresVisita;
    List<VisitaProducto> visitaProductos;
    List<Integer> productosOfertados;
    List<VisitaMaterial> visitaMateriales;
    List<Integer> materialesEntregados;

    //Campos con información introducida
    DatePicker datepicker_fecha_visita;
    EditText edittext_duracion;
    EditText edittext_observaciones;
    CheckBox checkbox_acompanyado;

    Button button_crear_visita_producto;
    Button button_crear_material_entregado;
    Button button_crear_visita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_visita, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        spinnerMedicos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_medicos);
        spinnerLugarVisita = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_lugar_visita);
        spinnerProductosOfertados = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_productos_ofertados);
        spinnerMaterialesEntregados = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_materiales_entregados);

        edittext_duracion = (EditText) view.findViewById(R.id.edit_text_duracion);
        datepicker_fecha_visita = (DatePicker) view.findViewById(R.id.date_picker_fecha_visita);
        edittext_observaciones = (EditText) view.findViewById(R.id.edit_text_observaciones);
        checkbox_acompanyado = (CheckBox) view.findViewById(R.id.checkBox_acompanyado);

        button_crear_material_entregado = (Button) view.findViewById(R.id.button_crear_material_entregado);
        button_crear_visita_producto = (Button) view.findViewById(R.id.button_crear_visita_producto);
        button_crear_visita = (Button) view.findViewById(R.id.button_crear_visita);

        //Vincular los listeners

        //Obtener al area hospitalaria del visitador
        final MySession session = (MySession) getActivity().getApplication();
        final Visitador visitador = session.getVisitador();
        final AreaHospitalaria area = FachadaAreaHospitalaria.obtenerAreaHospitalariaPorVisitador(getActivity(), visitador);

        //Vincular al spinner de médicos
        medicos = FachadaMedico.obtenerMedicosPorAreaHospitalaria(getActivity(), area);
        BaseAdapter adapter = new AdaptadorListaMedicos(getActivity(), medicos);
        spinnerMedicos.setAdapter(adapter);

        //Vincular al spinner de lugares de visita conlos lugares posibles
        lugaresVisita = FachadaLugarVisita.obtenerLugaresVisita();
        adapter = new AdaptadorListaLugaresVisita(getActivity(), lugaresVisita);
        spinnerLugarVisita.setAdapter(adapter);

        //Inicialización del resto de spinners
        visitaProductos = new ArrayList<>();
        productosOfertados = new ArrayList<>();
        adapter = new AdaptadorListaVisitasProducto(getActivity(), visitaProductos);
        spinnerProductosOfertados.setAdapter(adapter);

        visitaMateriales = new ArrayList<>();
        materialesEntregados = new ArrayList<>();
        adapter = new AdaptadorListaVisitasMaterial(getActivity(), visitaMateriales);
        spinnerMaterialesEntregados.setAdapter(adapter);

        //Vincular al botón de crear productos ofertados la creación del dialog
        button_crear_visita_producto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        crearVisitaProducto(view);
                    }
                }
        );

        //Vincular al botón de crear materiales entregados la creación del dialog
        button_crear_material_entregado.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        crearMaterialEntregado(view);
                    }
                }
        );

        //Pulsado largo del spinner de productos ofertados o de materiales entregados
        final Handler actionHandler = new Handler();
        final Runnable runnableProducto = new RunnableDeleteProducto();
        final Runnable runnableMaterial = new RunnableDeleteMaterial();

        spinnerProductosOfertados.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    actionHandler.postDelayed(runnableProducto, 1000);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    actionHandler.removeCallbacks(runnableProducto);
                }
                return false;
            }
        });

        spinnerMaterialesEntregados.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    actionHandler.postDelayed(runnableMaterial, 1000);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    actionHandler.removeCallbacks(runnableMaterial);
                }
                return false;
            }
        });

        //Vincular al botón de crear visita la creación del objeto visita en la bd
        button_crear_visita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Control de validación de la duración
                String val = edittext_duracion.getText().toString();
                if(val == null)
                {
                    edittext_duracion.setError("Debe introducirse un número");
                    return;
                }

                int valor = 0;
                try {
                    valor = Integer.parseInt(val);
                    if (valor <= 0) {
                        edittext_duracion.setError("Debe introducirse un número mayor que 0");
                        return;
                    }
                }
                catch (Exception ex)
                {
                    edittext_duracion.setError("Debe introducirse un número");
                    return;
                }

                //Mostrar diálogo de confirmación de operación
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
                dialogo1.setTitle("Creación de la visita");
                dialogo1.setMessage("¿Desea registrar la visita?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        //Obtener la fecha del datepicker
                        int year = datepicker_fecha_visita.getYear();
                        int month = datepicker_fecha_visita.getMonth();
                        int day = datepicker_fecha_visita.getDayOfMonth();
                        Calendar c = Calendar.getInstance();
                        c.set(year, month, day, 0, 0);

                        Visita visita = new Visita();
                        visita.setAcompanyado(checkbox_acompanyado.isChecked());
                        visita.setFechaReporte(new Date());
                        visita.setFechaVisita(c.getTime());
                        visita.setLugarVisita((LugarVisita) spinnerLugarVisita.getSelectedItem());
                        visita.setMedico((Medico) spinnerMedicos.getSelectedItem());
                        visita.setMinutos(Integer.parseInt(edittext_duracion.getText().toString()));
                        visita.setObservaciones(edittext_observaciones.getText().toString());
                        visita.setProductosOfertados(visitaProductos);
                        visita.setMaterialesEntregados(visitaMateriales);

                        //Vincular visitador
                        visita.setVisitador(visitador);

                        //Crear visita en la bd
                        if(FachadaVisita.create(getActivity(),visita) > 0)
                        {
                            AlertaDialogo ad = new AlertaDialogo();
                            ad.setMensaje("La visita ha sido creada con éxito");
                            ad.setTitulo("Operación exitosa");
                            ad.setBoton1("OK");
                            ad.setFlags(true);
                            ad.show(getActivity().getSupportFragmentManager(), "FragmentAlert");

                            //Limpiar los spinners de productos ofertados y materiales entregados
                            visitaMateriales.clear();
                            materialesEntregados.clear();
                            visitaProductos.clear();
                            productosOfertados.clear();
                            spinnerMaterialesEntregados.setAdapter(new AdaptadorListaVisitasMaterial(getActivity(), visitaMateriales));
                            spinnerProductosOfertados.setAdapter(new AdaptadorListaVisitasProducto(getActivity(), visitaProductos));

                            //Limpiar resto de campos
                            edittext_duracion.setText("");
                            edittext_observaciones.setText("");
                        }
                        else
                        {
                            AlertaDialogo ad = new AlertaDialogo();
                            ad.setMensaje("La visita no pudo ser creada");
                            ad.setTitulo("Operación fallida");
                            ad.setBoton1("OK");
                            ad.setFlags(true);
                            ad.show(getActivity().getSupportFragmentManager(), "FragmentAlert");
                        }
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        dialogo1.dismiss();
                    }
                });
                dialogo1.show();
            }
        });

    }

    public static RegistrarVisitaFragment newInstance() {

        // Instantiate a new fragment
        RegistrarVisitaFragment fragment = new RegistrarVisitaFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }

    /** Dialog */
    private void crearVisitaProducto(View view)
    {
        // custom dialog
        final Dialog dialog = new Dialog(RegistrarVisitaFragment.this.getActivity(), R.style.AppTheme_Dialog);
        //final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_crear_visita_producto);

         //Configurar la vista
        configurarVisitaProducto(dialog);

        dialog.show();

    }

    //Configurar el popup de visita producto
    private void configurarVisitaProducto(Dialog dialog)
    {
        final SpinnerOnChangeAdapter spinnerProducto = (SpinnerOnChangeAdapter)dialog.findViewById(R.id.spinner_productos);
        final TextView textView_seleccionar_productos = (TextView)dialog.findViewById(R.id.txt_productos);
        final TextView textView_valoracion = (TextView)dialog.findViewById(R.id.txt_valoracion);
        final RatingBar ratingbar_valoracion = (RatingBar) dialog.findViewById(R.id.rating_bar_valoracion);
        final Button button_crear = (Button)dialog.findViewById(R.id.button_crear_visita_producto);

        //Establecer el rating bar adecuado
        ratingbar_valoracion.setNumStars(FachadaValoracionProducto.obtenerCantidadValoraciones() - 1);
        ratingbar_valoracion.setMax(FachadaValoracionProducto.maxValue());

        spinnerProducto.setOnSpinnerListener(new OnSpinnerListener() {

            @Override
            public void onAdapterChange(View v)
            {
                //No se encontraron productos
                if(spinnerProducto.getAdapter().isEmpty())
                {
                    spinnerProducto.setVisibility(View.INVISIBLE);
                    textView_seleccionar_productos.setText("No se encontraron productos");
                    textView_valoracion.setVisibility(View.INVISIBLE);
                    ratingbar_valoracion.setVisibility(View.INVISIBLE);
                    button_crear.setVisibility(View.INVISIBLE);
                }
                else
                {
                    spinnerProducto.setVisibility(View.VISIBLE);
                    textView_seleccionar_productos.setText("Selecciona producto");
                    textView_valoracion.setVisibility(View.VISIBLE);
                    ratingbar_valoracion.setVisibility(View.VISIBLE);
                    button_crear.setVisibility(View.VISIBLE);
                }
            }
        });

        //Vincular los productos ofertables
        BaseAdapter adapter = new AdaptadorListaProductos(getActivity(),new FachadaProducto().obtenerProductosNotIn(getActivity(), productosOfertados));
        spinnerProducto.setAdapter(adapter);

        //Modificar el onclick de crear producto visitado
        button_crear.setOnClickListener(new CrearVisitaProductoListener(spinnerProducto,ratingbar_valoracion, dialog));

    }

    //Clase listener para crear una visita de un producto
    private class CrearVisitaProductoListener implements View.OnClickListener
    {
        private SpinnerOnChangeAdapter spinnerProducto;
        private RatingBar rating_bar_valoracion;
        private Dialog dialog;

        //Constructor
        public CrearVisitaProductoListener(SpinnerOnChangeAdapter spinnerProducto,RatingBar rating_bar_valoracion, Dialog dialog)
        {
            this.spinnerProducto = spinnerProducto;
            this.rating_bar_valoracion = rating_bar_valoracion;
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v)
        {
            //Recuperar producto
            Producto producto = (Producto)spinnerProducto.getSelectedItem();
            //Obtener valoración
            ValoracionProducto valoracion = ValoracionProducto.values()[Math.round(rating_bar_valoracion.getRating())];
            //Crear visita del producto
            VisitaProducto visitaProd = new VisitaProducto();
            visitaProd.setOrden(spinnerProductosOfertados.getCount()+1);
            visitaProd.setValoracion(valoracion);
            visitaProd.setProducto(producto);

            //Añadir en la lista de productos ofertados
            visitaProductos.add(visitaProd);
            productosOfertados.add(producto.getCodNacional());
            spinnerProductosOfertados.setAdapter(new AdaptadorListaVisitasProducto(getActivity(), visitaProductos));

            //Cerrar el popup
            dialog.dismiss();
        }
    }

    /** Dialog */
    private void crearMaterialEntregado(View view)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity(), R.style.AppTheme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_crear_visita_material);
        //Configurar la vista
        configurarVisitaMaterial(dialog);

        dialog.show();
    }

    //Configurar el dialog de Visita Material
    private void configurarVisitaMaterial(Dialog dialog)
    {
        final SpinnerOnChangeAdapter spinnerProducto = (SpinnerOnChangeAdapter)dialog.findViewById(R.id.spinner_productos);
        final SpinnerOnChangeAdapter spinnerMaterial = (SpinnerOnChangeAdapter)dialog.findViewById(R.id.spinner_materiales);
        final TextView text_view_materiales = (TextView)dialog.findViewById(R.id.txt_materiales);
        final TextView text_view_productos = (TextView)dialog.findViewById(R.id.txt_productos);
        final TextView text_view_cantidad = (TextView)dialog.findViewById(R.id.txt_cantidad);
        final EditText edit_text_cantidad = (EditText)dialog.findViewById(R.id.editText_cantidad);

        final Button button_crear = (Button)dialog.findViewById(R.id.button_crear_visita_material);

        //Vincular los productos ofertables
        BaseAdapter adapter = new AdaptadorListaProductos(getActivity(),new FachadaProducto().obtenerProductosIn(getActivity(), productosOfertados));
        spinnerProducto.setAdapter(adapter);

        //Establecer listener para los materiales entregados
        spinnerProducto.setOnSpinnerListener(new OnSpinnerListener() {
            @Override
            public void onAdapterChange(View v) {
                if(!spinnerProducto.getAdapter().isEmpty())
                {
                    Producto prod = (Producto)spinnerProducto.getSelectedItem();
                    BaseAdapter adapter = new AdaptadorListaMateriales(getActivity(), FachadaMaterialPromocional.obtenerMaterialesPorProductoNotIn(getActivity(),prod, materialesEntregados));
                    spinnerMaterial.setAdapter(adapter);
                }
                else
                {
                    text_view_productos.setText("No se encontraron productos");
                    spinnerProducto.setVisibility(View.INVISIBLE);
                    spinnerMaterial.setAdapter(new AdaptadorListaMateriales(getActivity(),new ArrayList<MaterialPromocional>()));
                }
            }
        });

        //Establecer listener que se active al modificar el adapter
        spinnerMaterial.setOnSpinnerListener(new OnSpinnerListener() {
            @Override
            public void onAdapterChange(View v)
            {
                //Si está vacío, ocultar elementos
                if(spinnerMaterial.getAdapter().isEmpty())
                {
                    if(spinnerProducto.getAdapter().isEmpty())
                        text_view_materiales.setVisibility(View.INVISIBLE);
                    else
                        text_view_materiales.setVisibility(View.VISIBLE);

                    text_view_materiales.setText("No se encontraron materiales");
                    spinnerMaterial.setVisibility(View.INVISIBLE);
                    text_view_cantidad.setVisibility(View.INVISIBLE);
                    edit_text_cantidad.setVisibility(View.INVISIBLE);
                    button_crear.setVisibility(View.INVISIBLE);
                }
                else
                {
                    text_view_materiales.setVisibility(View.VISIBLE);
                    text_view_materiales.setText("Selecciona materiales");
                    text_view_cantidad.setVisibility(View.VISIBLE);
                    edit_text_cantidad.setVisibility(View.VISIBLE);
                    spinnerMaterial.setVisibility(View.VISIBLE);
                    button_crear.setVisibility(View.VISIBLE);
                }
            }
        });

        //Vincular el botón de creación con el listener
        button_crear.setOnClickListener(new CrearVisitaMaterialListener(spinnerMaterial,edit_text_cantidad,dialog));

    }

    //Clase listener para crear material entregado
    private class CrearVisitaMaterialListener implements View.OnClickListener
    {
        private SpinnerOnChangeAdapter spinnerMaterial;
        private EditText editText;
        private Dialog dialog;

        //Constructor
        public CrearVisitaMaterialListener(SpinnerOnChangeAdapter spinnerMaterial,EditText editText, Dialog dialog)
        {
            this.spinnerMaterial = spinnerMaterial;
            this.editText=editText;
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v)
        {
            //Comprobar errores con la cantidad
            String val = editText.getText().toString();
            if(val == null)
            {
                editText.setError("Debe introducirse un número");
                return;
            }

            int valor = 0;
            try {
                valor = Integer.parseInt(val);
                if (valor <= 0) {
                    editText.setError("Debe introducirse un número mayor que 0");
                    return;
                }
            }
            catch (Exception ex)
            {
                editText.setError("Debe introducirse un número");
                return;
            }

            //Recuperar material
            MaterialPromocional material = (MaterialPromocional)spinnerMaterial.getSelectedItem();

            //Crear visita del material
            VisitaMaterial visitaMaterial= new VisitaMaterial();
            visitaMaterial.setCantidad(valor);
            visitaMaterial.setMaterialPromocional(material);

            //Añadir en la lista de materiales entregados
            visitaMateriales.add(visitaMaterial);
            materialesEntregados.add(material.getId());
            spinnerMaterialesEntregados.setAdapter(new AdaptadorListaVisitasMaterial(getActivity(), visitaMateriales));

            //Cerrar el popup
            dialog.dismiss();
        }
    }

    //Clase para el evento de eliminar un item del spinner de productos ofertados
    private class RunnableDeleteProducto implements Runnable
    {
        @Override
        public void run() {

            //Si el spinner está vacío no hacer nada
            if(spinnerProductosOfertados.getAdapter().isEmpty())
                return;

            //Obtener el producto
            final int pos = spinnerProductosOfertados.getSelectedItemPosition();
            final VisitaProducto visitaProd = (VisitaProducto)spinnerProductosOfertados.getItemAtPosition(pos);

            //Mostrar diálogo de confirmación de operación
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
            dialogo1.setTitle("Borrar elemento destacado");
            dialogo1.setMessage("¿Desea borrar la promoción de " + visitaProd.getProducto().getNombre() + "(" + visitaProd.getOrden() + "º)?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    //Borrar producto de la lista
                    visitaProductos.remove(pos);
                    productosOfertados.remove(pos);
                    //Disminuir el orden de los siguientes
                    for(int i=pos; i< visitaProductos.size(); i++)
                    {
                        VisitaProducto vis = visitaProductos.get(i);
                        vis.setOrden(vis.getOrden()-1);
                    }
                    //Meter la lista de nuevo en el spinner
                    spinnerProductosOfertados.setAdapter(new AdaptadorListaVisitasProducto(getActivity(), visitaProductos));
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

    //Clase para el evento de eliminar un item del spinner de materiales entregados
    private class RunnableDeleteMaterial implements Runnable
    {
        @Override
        public void run() {

            //Si el spinner está vacío no hacer nada
            if(spinnerMaterialesEntregados.getAdapter().isEmpty())
                return;

            //Obtener el producto
            final int pos = spinnerMaterialesEntregados.getSelectedItemPosition();
            final VisitaMaterial visitaMat = (VisitaMaterial)spinnerMaterialesEntregados.getItemAtPosition(pos);
            final MaterialPromocional materialPromocional = visitaMat.getMaterialPromocional();

            //Mostrar diálogo de confirmación de operación
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
            dialogo1.setTitle("Borrar elemento destacado");
            dialogo1.setMessage("¿Desea borrar la entrega de " + visitaMat.getCantidad() + " " + materialPromocional.getNombre() +
                    " del producto " + materialPromocional.getProducto().getNombre() + "?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    //Borrar material de la lista
                    visitaMateriales.remove(pos);
                    materialesEntregados.remove(pos);
                    //Meter la lista de nuevo en el spinner
                    spinnerMaterialesEntregados.setAdapter(new AdaptadorListaVisitasMaterial(getActivity(), visitaMateriales));
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
}
