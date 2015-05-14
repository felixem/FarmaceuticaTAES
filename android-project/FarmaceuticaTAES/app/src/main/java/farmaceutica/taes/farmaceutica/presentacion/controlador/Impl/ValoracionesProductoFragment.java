package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.ProductoRepository;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaCentrosMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.FragmentBase;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaCentroMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class ValoracionesProductoFragment extends FragmentBase{

    private Producto prod;
    TextView textView_codigo;
    TextView textView_nombre;
    TextView textView_descripcion;
    TextView textView_cantidad1;
    TextView textView_cantidad2;
    TextView textView_cantidad3;
    TextView textView_cantidad4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.valoracionesproducto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        textView_codigo = (TextView) view.findViewById(R.id.textView_cod);
        textView_nombre= (TextView) view.findViewById(R.id.textView_nombreProd);
        textView_descripcion=(TextView) view.findViewById(R.id.textView_descripcion);
        textView_cantidad1=(TextView) view.findViewById(R.id.textView_totalNada);
        textView_cantidad2=(TextView) view.findViewById(R.id.textView_totalPoco);
        textView_cantidad3=(TextView) view.findViewById(R.id.textView_totalMedio);
        textView_cantidad4=(TextView) view.findViewById(R.id.textView_totalMucho);

        FachadaProducto fachada= new FachadaProducto();
        Bundle bd=getArguments();
        prod=fachada.obtenerProducto(getActivity(),bd.getInt(Producto.ID));
        textView_codigo.setText(Integer.toString(prod.getCodNacional()));
        textView_nombre.setText(prod.getNombre());
        textView_descripcion.setText(prod.getDescripcion());
        try {
        textView_cantidad1.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(),ValoracionProducto.NADA)));
        textView_cantidad2.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(),ValoracionProducto.POCO)));
        textView_cantidad3.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(),ValoracionProducto.MEDIO)));
        textView_cantidad4.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(),ValoracionProducto.MUCHO)));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static ValoracionesProductoFragment newInstance() {

        // Instantiate a new fragment
       ValoracionesProductoFragment fragment = new ValoracionesProductoFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }



}
