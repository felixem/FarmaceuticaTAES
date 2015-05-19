package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;

/**
 * Created by John on 12/05/2015.
 */
public class Ejemplo1Fragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gasto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //ListView lv = (ListView) view.findViewById(R.id.ListView_listado);
        //BaseAdapter ad= new ArrayAdapter<String>(getActivity(), R.layout.textview_ejemplo, new String[]{"Probando1", "Probando2", "Probando3"});
        //lv.setAdapter(ad);
    }

    public static Ejemplo1Fragment newInstance() {

        // Instantiate a new fragment
        Ejemplo1Fragment fragment = new Ejemplo1Fragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
