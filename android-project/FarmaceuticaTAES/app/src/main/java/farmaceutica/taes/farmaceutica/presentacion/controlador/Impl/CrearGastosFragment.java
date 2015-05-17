package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.AdaptadorSpinnerConceptoGasto;

/**
 * Created by John on 15/05/2015.
 */
public class CrearGastosFragment extends BaseFragment {
    Spinner sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_gasto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = (Spinner) view.findViewById(R.id.sp_dfault);
        sp.setAdapter(new AdaptadorSpinnerConceptoGasto(getActivity()));

    }
}
