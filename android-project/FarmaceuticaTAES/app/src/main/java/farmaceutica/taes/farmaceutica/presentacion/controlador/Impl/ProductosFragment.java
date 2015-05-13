package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.FragmentBase;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaProducto;

/**
 * Created by  on 12/05/2015.
 */
public class ProductosFragment extends FragmentBase{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listado, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView lv = (ListView) view.findViewById(R.id.ListView_listado);
        /*
        TextView txt= (TextView)view.findViewById(R.id.textView_codProducto);
        TextView txt1= (TextView)view.findViewById(R.id.textView_nombre);
        TextView txt2=(TextView)view.findViewById(R.id.textView_descripcion);*/

        FachadaProducto fachada= new FachadaProducto();
        List<Producto> datos= fachada.obtenerProductos(getActivity());
        BaseAdapter adapter = new AdaptadorListaProductos(getActivity(),datos);
        lv.setAdapter(adapter);
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
