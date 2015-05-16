package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaLugaresVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitasProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaLugarVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaValoracionProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class RegistrarVisitaFragment extends BaseFragment implements OnSpinnerListener{

    SpinnerOnChangeAdapter spinnerMedicos;
    SpinnerOnChangeAdapter spinnerLugarVisita;
    SpinnerOnChangeAdapter spinnerProductosOfertados;
    SpinnerOnChangeAdapter spinnerMaterialesEntregados;

    //Listas con el contenido de los spinner
    List<Medico> medicos;
    List<LugarVisita> lugaresVisita;
    List<VisitaProducto> productosOfertados;
    List<MaterialPromocional> materialesEntregados;

    TextView textView_medico;
    TextView textView_lugar_visita;
    TextView textView_producto_ofertado;
    TextView textView_material_entregado;
    Button button_crear_visita_producto;
    Button button_crear_material_entregado;
    Button button_crear_visita;

    //The "x" and "y" position of the "Show Button" on screen.
    Point punto_crear_visita_producto;

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

        textView_medico = (TextView) view.findViewById(R.id.txt_medico);
        textView_lugar_visita = (TextView) view.findViewById(R.id.txt_lugar_visita);
        textView_producto_ofertado = (TextView) view.findViewById(R.id.txt_productos_ofertados);
        textView_material_entregado = (TextView) view.findViewById(R.id.txt_materiales_entregados);
        button_crear_material_entregado = (Button) view.findViewById(R.id.button_crear_material_entregado);
        button_crear_visita_producto = (Button) view.findViewById(R.id.button_crear_visita_producto);
        button_crear_visita = (Button) view.findViewById(R.id.button_crear_visita);

        //Vincular los listeners

        //Provisionalmente creado un área hospitalaria que será la del visitador actual
        AreaHospitalaria area = new AreaHospitalaria();
        area.setCodPostal(3009);

        //Vincular al spinner de médicos
        medicos = FachadaMedico.obtenerMedicosPorAreaHospitalaria(getActivity(), area);
        BaseAdapter adapter = new AdaptadorListaMedicos(getActivity(), medicos);
        spinnerMedicos.setAdapter(adapter);

        //Vincular al spinner de lugares de visita conlos lugares posibles
        lugaresVisita = FachadaLugarVisita.obtenerLugaresVisita();
        adapter = new AdaptadorListaLugaresVisita(getActivity(), lugaresVisita);
        spinnerLugarVisita.setAdapter(adapter);

        //Creación provisional de visitas de producto
        productosOfertados = new ArrayList<>();
        adapter = new AdaptadorListaVisitasProducto(getActivity(),productosOfertados);
        spinnerProductosOfertados.setAdapter(adapter);

        //Vincular al botón de crear productos ofertados la creación del popup
        button_crear_visita_producto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        localizarBotonCrearProducto();
                        crearVisitaProducto(view,punto_crear_visita_producto);
                    }
                }
        );

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

    @Override
    public void onAdapterChange(View v)
    {

    }

//Localizar al botón crear producto
    public void localizarBotonCrearProducto() {

        int[] location = new int[2];
        //Obtener localización del botón crear producto
        button_crear_visita_producto.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        punto_crear_visita_producto = new Point();
        punto_crear_visita_producto.x = location[0];
        punto_crear_visita_producto.y = location[1];
    }

    /** Popup Menu */
    private void crearVisitaProducto(View view, Point p)
    {
        final int popupWidth = 400;
        final int popupHeight = 300;
        final int OFFSET_X = 300;
        final int OFFSET_Y = -300;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) view.findViewById(R.id.linear_layout_crear_visita_producto);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_crear_visita_producto, viewGroup);

        //Crea instancia a PopupMenu
        PopupWindow popup = new PopupWindow(view);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        //Permitir que se cierre al pulsar fuera
        popup.setBackgroundDrawable(new BitmapDrawable(getResources(), ""));
        //Configurar la vista
        configurarVisitaProducto(layout,popup);

        //Mostrar el popup
        popup.showAtLocation(layout, Gravity.NO_GRAVITY,p.x+OFFSET_X,p.y+OFFSET_Y);
    }

    //Configurar el popup de visita producto
    private void configurarVisitaProducto(View view, PopupWindow popup)
    {
        SpinnerOnChangeAdapter spinnerProducto = (SpinnerOnChangeAdapter)view.findViewById(R.id.spinner_productos);
        RatingBar ratingbar_valoracion = (RatingBar) view.findViewById(R.id.rating_bar_valoracion);
        Button button_crear = (Button)view.findViewById(R.id.button_crear_visita_producto);

        //Establecer el rating bar adecuado
        ratingbar_valoracion.setNumStars(FachadaValoracionProducto.obtenerCantidadValoraciones() - 1);
        ratingbar_valoracion.setMax(FachadaValoracionProducto.maxValue());

        //Vincular los productos ofertables
        BaseAdapter adapter = new AdaptadorListaProductos(getActivity(),new FachadaProducto().obtenerProductos(getActivity()));
        spinnerProducto.setAdapter(adapter);

        //Modificar el onclick de crear producto visitado
        button_crear.setOnClickListener(new CrearVisitaProductoListener(spinnerProducto,ratingbar_valoracion, popup));

    }

    //Clase listener para crear una visita de un producto
    private class CrearVisitaProductoListener implements View.OnClickListener
    {
        private SpinnerOnChangeAdapter spinnerProducto;
        private RatingBar rating_bar_valoracion;
        private PopupWindow popup;

        //Constructor
        public CrearVisitaProductoListener(SpinnerOnChangeAdapter spinnerProducto,RatingBar rating_bar_valoracion, PopupWindow popup)
        {
            this.spinnerProducto = spinnerProducto;
            this.rating_bar_valoracion = rating_bar_valoracion;
            this.popup = popup;
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
            productosOfertados.add(visitaProd);
            spinnerProductosOfertados.setAdapter(new AdaptadorListaVisitasProducto(getActivity(),productosOfertados));

            //Cerrar el popup
            popup.dismiss();
        }
    }
}
