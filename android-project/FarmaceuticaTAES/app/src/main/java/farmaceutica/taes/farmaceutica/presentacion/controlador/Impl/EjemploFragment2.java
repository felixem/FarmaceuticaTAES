package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.FragmentBase;

/**
 * Created by John on 12/05/2015.
 */
public class EjemploFragment2 extends FragmentBase{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.plantila, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView txt = (TextView)view.findViewById(R.id.txt_default);
        txt.setText("Fragmen2");
    }

    public static EjemploFragment2 newInstance() {

        // Instantiate a new fragment
        EjemploFragment2 fragment = new EjemploFragment2();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
