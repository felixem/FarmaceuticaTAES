package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.ConceptoGasto;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaGastos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaReportes;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorSpinnerConceptoGasto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.Linker;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaGasto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaReporteGastos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.CrearGastoView;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class MisGastosFragment extends BaseFragment implements OnSpinnerListener{

    SpinnerOnChangeAdapter spinnerReportes;
    SpinnerOnChangeAdapter spinnerGastos;
    TextView textView_reportes;
    TextView textView_gastos;
    //Button button_ver_detalles;

    private LinearLayout ll_container;
    private Button btn_reportar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_gastos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ll_container = (LinearLayout) view.findViewById(R.id.ll_container);
        spinnerReportes = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_reportes);
        spinnerGastos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_gastos);
        textView_reportes = (TextView) view.findViewById(R.id.txt_reporte);
        textView_gastos = (TextView) view.findViewById(R.id.txt_gasto);
        //button_ver_detalles = (Button) view.findViewById(R.id.button_ver_detalles);
        btn_reportar = (Button) view.findViewById(R.id.btn_reportar);

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
                        //BaseAdapter adapter = new AdaptadorListaGastos(getActivity(), gastos);
                        BaseAdapter adapter = new AdaptadorSpinnerConceptoGasto(getActivity());
                        spinnerGastos.setAdapter(adapter);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        spinnerGastos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CargarLinearLayouContainer();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linker linker = new Linker(getFragmentManager(), true);
                linker.CrearGasto();
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
                //button_ver_detalles.setVisibility(View.INVISIBLE);
            }
            else
            {
                textView_gastos.setVisibility(View.VISIBLE);
                spinnerGastos.setVisibility(View.VISIBLE);
                textView_gastos.setText("Selecciona gasto");
                //button_ver_detalles.setVisibility(View.VISIBLE);
                CargarLinearLayouContainer();
            }

        }

    }

    private void CargarLinearLayouContainer(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_container.getLayoutParams();
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        ll_container.removeAllViewsInLayout();
        ll_container.setLayoutParams(params);
        ReporteGastos rg = (ReporteGastos)spinnerReportes.getSelectedItem();
        ConceptoGasto cg = ((ConceptoGasto)spinnerGastos.getSelectedItem());
        List<Gasto> gastos = FachadaGasto.obtenerGastosPorReporteGasto(getActivity(), rg);
        CrearGastoView cgv;
        for(Gasto g : gastos){
            if(cg.equals(g.getConceptoGasto())) {
                cgv = new CrearGastoView(getActivity(), g);
                cgv.setOnImageButtonClickeable(false);
                ll_container.addView(cgv);
            }
        }
    }
}
