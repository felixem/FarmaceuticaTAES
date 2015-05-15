package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaGastos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaReportes;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaGasto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaReporteGastos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class MisGastosFragment extends BaseFragment implements OnSpinnerListener{

    SpinnerOnChangeAdapter spinnerReportes;
    SpinnerOnChangeAdapter spinnerGastos;
    TextView textView_reportes;
    TextView textView_gastos;
    Button button_ver_detalles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_gastos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        spinnerReportes = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_reportes);
        spinnerGastos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_gastos);
        textView_reportes = (TextView) view.findViewById(R.id.txt_reporte);
        textView_gastos = (TextView) view.findViewById(R.id.txt_gasto);
        button_ver_detalles = (Button) view.findViewById(R.id.button_ver_detalles);

        //Vincular los listeners
        spinnerReportes.setOnSpinnerListener(this);
        spinnerGastos.setOnSpinnerListener(this);

        //Creado provisionalmente un visitador
        Visitador visitador = new Visitador();
        visitador.setCodigo(1);

        //Vincular al spinner de reportes los reportes
        final BaseAdapter adapter = new AdaptadorListaReportes(getActivity(), FachadaReporteGastos.obtenerReportesGastosPorVisitador(getActivity(), visitador));
        spinnerReportes.setAdapter(adapter);

        //Establecer eventos en el spinner de centros médicos
        spinnerReportes.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View v, int position, long id) {

                        //Meter en el spinner de gastos los gastos del reporte
                        ReporteGastos reporte = (ReporteGastos) parent.getItemAtPosition(position);
                        List<Gasto> gastos = FachadaGasto.obtenerGastosPorReporteGasto(getActivity(), reporte);
                        BaseAdapter adapter = new AdaptadorListaGastos(getActivity(), gastos);
                        spinnerGastos.setAdapter(adapter);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    @Override
    public void onAdapterChange(View v)
    {
        SpinnerAdapter adapter;
        //Gestionar set adapter a spinner centros médicos
        if(v.equals(spinnerReportes))
        {
            adapter = spinnerReportes.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                textView_reportes.setVisibility(View.VISIBLE);
                spinnerReportes.setVisibility(View.INVISIBLE);
                textView_reportes.setText("No se encontraron reportes");
                spinnerGastos.setAdapter(new AdaptadorListaGastos(getActivity(),new ArrayList<Gasto>()));
            }
            else
            {
                textView_reportes.setVisibility(View.VISIBLE);
                spinnerReportes.setVisibility(View.VISIBLE);
                textView_reportes.setText("Selecciona el reporte de gastos");

                ReporteGastos reporte = (ReporteGastos)spinnerReportes.getSelectedItem();
                List<Gasto> gastos = FachadaGasto.obtenerGastosPorReporteGasto(getActivity(), reporte);
                BaseAdapter adaptadorbase = new AdaptadorListaGastos(getActivity(),gastos);
                spinnerGastos.setAdapter(adaptadorbase);
            }

        }
        //Gestionar set adapter a spinner médicos
        else if(v.equals(spinnerGastos))
        {
            adapter = spinnerGastos.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                textView_gastos.setVisibility(View.VISIBLE);
                spinnerGastos.setVisibility(View.INVISIBLE);
                textView_gastos.setText("No se encontraron gastos asociados");
                button_ver_detalles.setVisibility(View.INVISIBLE);
            }
            else
            {
                textView_gastos.setVisibility(View.VISIBLE);
                spinnerGastos.setVisibility(View.VISIBLE);
                textView_gastos.setText("Selecciona gasto");
                button_ver_detalles.setVisibility(View.VISIBLE);
            }

        }

    }
}
