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

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaCentrosMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaLugaresVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaCentroMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaLugarVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class RegistrarVisitaFragment extends BaseFragment implements OnSpinnerListener{

    SpinnerOnChangeAdapter spinnerMedicos;
    SpinnerOnChangeAdapter spinnerLugarVisita;
    SpinnerOnChangeAdapter spinnerProductosOfertados;
    SpinnerOnChangeAdapter spinnerMaterialesEntregados;
    TextView textView_medico;
    TextView textView_lugar_visita;
    TextView textView_producto_ofertado;
    TextView textView_material_entregado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_visita, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        spinnerMedicos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_medicos);
        spinnerLugarVisita = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_lugar_visita);
        spinnerProductosOfertados = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_productos_ofertados);
        spinnerMaterialesEntregados = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_materiales_entregados);
        textView_medico = (TextView) view.findViewById(R.id.txt_medico);
        textView_lugar_visita = (TextView) view.findViewById(R.id.txt_lugar_visita);
        textView_producto_ofertado = (TextView) view.findViewById(R.id.txt_productos_ofertados);
        textView_material_entregado = (TextView) view.findViewById(R.id.txt_materiales_entregados);

        //Vincular los listeners

        //Provisionalmente creado un área hospitalaria que será la del visitador actual
        AreaHospitalaria area = new AreaHospitalaria();
        area.setCodPostal(3009);

        //Vincular al spinner de centros médicos los centros médicos
        final BaseAdapter adapterMedicos = new AdaptadorListaMedicos(getActivity(), FachadaMedico.obtenerMedicosPorAreaHospitalaria(getActivity(), area));
        spinnerMedicos.setAdapter(adapterMedicos);

        //Vincular al spinner de lugares de visita los lugares posibles
        final BaseAdapter adapterLugares = new AdaptadorListaLugaresVisita(getActivity(), FachadaLugarVisita.obtenerLugaresVisita(getActivity()));
        spinnerLugarVisita.setAdapter(adapterLugares);

        //Establecer eventos en el spinner de médicos
        spinnerMedicos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()    {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                View v, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
                });

    }

    public static RegistrarVisitaFragment newInstance() {

        // Instantiate a new fragment
        RegistrarVisitaFragment fragment = new RegistrarVisitaFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onAdapterChange(View v)
    {
        SpinnerAdapter adapter;
    }
}
