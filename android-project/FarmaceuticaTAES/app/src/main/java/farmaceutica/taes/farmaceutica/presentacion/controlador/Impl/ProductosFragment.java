package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.FragmentBase;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaProducto;

/**
 * Created by John on 12/05/2015.
 */
public class ProductosFragment extends FragmentBase{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listado, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView lv = (ListView) view.findViewById(R.id.ListView_listado);

        //Obtener la lista de centros medicos
        FachadaProducto fachada = new FachadaProducto();
        BaseAdapter adapter = new AdaptadorListaProductos(getActivity(), fachada.obtenerProductos(getActivity()));
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
