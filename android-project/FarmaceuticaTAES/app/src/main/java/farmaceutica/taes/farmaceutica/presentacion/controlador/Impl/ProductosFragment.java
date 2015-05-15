package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;

/**
 * Created by John on 12/05/2015.
 */
public class ProductosFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listaproductos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView lv = (ListView) view.findViewById(R.id.ListView_listaProductos);

        //Obtener la lista de centros medicos
        FachadaProducto fachada = new FachadaProducto();
        BaseAdapter adapter = new AdaptadorListaProductos(getActivity(), fachada.obtenerProductos(getActivity()));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int cod= ((Producto)parent.getAdapter().getItem(position)).getCodNacional();
                Bundle bd= new Bundle();
                bd.putInt(Producto.ID,cod);
            }
        });
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
