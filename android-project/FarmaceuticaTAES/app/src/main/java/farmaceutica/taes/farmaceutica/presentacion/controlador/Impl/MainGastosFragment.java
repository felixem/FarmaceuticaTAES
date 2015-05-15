package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.FragmentBase;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.Linker;

/**
 * Created by John on 15/05/2015.
 */
public class MainGastosFragment extends FragmentBase{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        Linker linker = new Linker(getFragmentManager(), false);
        linker.MisGastos();

        return view;
    }

    public static MisGastosFragment newInstance() {

        // Instantiate a new fragment
        MisGastosFragment fragment = new MisGastosFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
