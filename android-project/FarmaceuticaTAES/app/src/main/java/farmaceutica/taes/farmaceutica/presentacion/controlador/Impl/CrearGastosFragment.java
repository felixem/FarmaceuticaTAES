package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorSpinnerConceptoGasto;

/**
 * Created by John on 15/05/2015.
 */
public class CrearGastosFragment extends BaseFragment {
    Spinner sp;
    private DatePicker date_picker;
    private int year, month, day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_gasto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = (Spinner) view.findViewById(R.id.sp_dfault);
        sp.setAdapter(new AdaptadorSpinnerConceptoGasto(getActivity()));


        //Introducimos fecha
        //En principio inica la vista con la fecha del momento en que se instancia,
        //pero la instanciamos para asegurarnos.
        date_picker = (DatePicker) view.findViewById(R.id.date_picker);
        final Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        date_picker.init(year, month, day, null);

    }
}
