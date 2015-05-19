package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Dialog;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaValoracionProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class ProductosFragment extends BaseFragment {

    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listaproductos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        lv = (ListView) view.findViewById(R.id.ListView_listaProductos);

        //Obtener la lista de centros medicos
        FachadaProducto fachada = new FachadaProducto();
        BaseAdapter adapter = new AdaptadorListaProductos(getActivity(), fachada.obtenerProductos(getActivity()));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                verValoracionesProducto(view,(Producto)parent.getAdapter().getItem(position));
            }
        });
    }
    private void verValoracionesProducto(View view,Producto prod)
    {
        // custom dialog
        final Dialog dialog = new Dialog(ProductosFragment.this.getActivity(), R.style.AppTheme_Dialog);
        //final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_valoraciones_producto);

        //Configurar la vista
        configurarValoracionesProducto(dialog, prod.getCodNacional());

        dialog.show();

    }

    //Configurar el popup de visita producto
    private void configurarValoracionesProducto(Dialog dialog,int codigo)
    {
        TextView textView_codigo = (TextView) dialog.findViewById(R.id.textView_cod);
        TextView textView_nombre= (TextView) dialog.findViewById(R.id.textView_nombreProd);
        TextView textView_descripcion=(TextView) dialog.findViewById(R.id.textView_descripcion);
        TextView textView_cantidad1=(TextView) dialog.findViewById(R.id.textView_totalNada);
        TextView textView_cantidad2=(TextView) dialog.findViewById(R.id.textView_totalPoco);
        TextView textView_cantidad3=(TextView) dialog.findViewById(R.id.textView_totalMedio);
        TextView textView_cantidad4=(TextView) dialog.findViewById(R.id.textView_totalMucho);

        FachadaProducto fachada= new FachadaProducto();
        Producto prod=fachada.obtenerProducto(getActivity(),codigo);
        textView_codigo.setText(Integer.toString(prod.getCodNacional()));
        textView_nombre.setText(prod.getNombre());
        textView_descripcion.setText(prod.getDescripcion());
        try {
            textView_cantidad1.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(), ValoracionProducto.NADA)));
            textView_cantidad2.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(),ValoracionProducto.POCO)));
            textView_cantidad3.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(),ValoracionProducto.MEDIO)));
            textView_cantidad4.setText(Integer.toString(fachada.obtenerCantidadValoracionProducto(getActivity(),prod.getCodNacional(),ValoracionProducto.MUCHO)));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static ProductosFragment newInstance() {

        // Instantiate a new fragment
        ProductosFragment fragment = new ProductosFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
