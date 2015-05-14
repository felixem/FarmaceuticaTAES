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
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.FragmentBase;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaCentroMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class MisGastosFragment extends FragmentBase implements OnSpinnerListener{

    SpinnerOnChangeAdapter spinnerCentrosMedicos;
    SpinnerOnChangeAdapter spinnerMedicos;
    SpinnerOnChangeAdapter spinnerVisitas;
    TextView textView_centros;
    TextView textView_medicos;
    TextView textView_visitas;
    Button button_ver_detalles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visitas_medicos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        spinnerCentrosMedicos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_centros_medicos);
        spinnerMedicos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_medicos);
        spinnerVisitas = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_visitas);
        textView_centros = (TextView) view.findViewById(R.id.txt_centro_medico);
        textView_medicos = (TextView) view.findViewById(R.id.txt_medico);
        textView_visitas = (TextView) view.findViewById(R.id.txt_visita);
        button_ver_detalles = (Button) view.findViewById(R.id.button_ver_detalles);

        //Vincular los listeners
        spinnerCentrosMedicos.setOnSpinnerListener(this);
        spinnerMedicos.setOnSpinnerListener(this);
        spinnerVisitas.setOnSpinnerListener(this);

        //Provisionalmente creado un área hospitalaria que será la del visitador actual
        AreaHospitalaria area = new AreaHospitalaria();
        area.setCodPostal(3009);

        //Vincular al spinner de centros médicos los centros médicos
        final BaseAdapter adapter = new AdaptadorListaCentrosMedicos(getActivity(), FachadaCentroMedico.obtenerCentrosMedicosPorArea(getActivity(),area));
        spinnerCentrosMedicos.setAdapter(adapter);

        //Establecer eventos en el spinner de centros médicos
        spinnerCentrosMedicos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()    {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View v, int position, long id) {

                        //Meter en el spinner de visitas las visitas del médico
                        CentroMedico centro = (CentroMedico)parent.getItemAtPosition(position);
                        List<Medico> medicos = FachadaMedico.obtenerMedicosPorCentroMedico(getActivity(), centro);
                        BaseAdapter adapter = new AdaptadorListaMedicos(getActivity(), medicos);
                        spinnerMedicos.setAdapter(adapter);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

        //Establecer eventos en el spinner de médicos
        spinnerMedicos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()    {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                View v, int position, long id) {

            //Meter en el spinner de visitas las visitas del médico
            Medico medico = (Medico)parent.getItemAtPosition(position);
            List<Visita> visitas = FachadaVisita.obtenerVisitasPorMedico(getActivity(),medico);
            BaseAdapter adapter = new AdaptadorListaVisitas(getActivity(), visitas);
            spinnerVisitas.setAdapter(adapter);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
                });

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

    @Override
    public void onAdapterChange(View v)
    {
        SpinnerAdapter adapter;
        //Gestionar set adapter a spinner centros médicos
        if(v.equals(spinnerCentrosMedicos))
        {
            adapter = spinnerCentrosMedicos.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                textView_centros.setVisibility(View.VISIBLE);
                spinnerCentrosMedicos.setVisibility(View.INVISIBLE);
                textView_centros.setText("No se encontraron centros medicos");
                spinnerMedicos.setAdapter(new AdaptadorListaMedicos(getActivity(),new ArrayList<Medico>()));
            }
            else
            {
                textView_centros.setVisibility(View.VISIBLE);
                spinnerCentrosMedicos.setVisibility(View.VISIBLE);
                textView_centros.setText("Selecciona centro médico");

                CentroMedico centro = (CentroMedico)spinnerCentrosMedicos.getSelectedItem();
                List<Medico> medicos = FachadaMedico.obtenerMedicosPorCentroMedico(getActivity(), centro);
                BaseAdapter adaptadorbase = new AdaptadorListaMedicos(getActivity(),medicos);
                spinnerMedicos.setAdapter(adaptadorbase);
            }

        }
        //Gestionar set adapter a spinner médicos
        else if(v.equals(spinnerMedicos))
        {
            adapter = spinnerMedicos.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                textView_medicos.setVisibility(View.VISIBLE);
                spinnerMedicos.setVisibility(View.INVISIBLE);
                textView_medicos.setText("No se encontraron médicos");
                spinnerVisitas.setAdapter(new AdaptadorListaVisitas(getActivity(),new ArrayList<Visita>()));
            }
            else
            {
                textView_medicos.setVisibility(View.VISIBLE);
                spinnerMedicos.setVisibility(View.VISIBLE);
                textView_medicos.setText("Selecciona médico");

                Medico medico = (Medico)spinnerMedicos.getSelectedItem();
                List<Visita> visitas = FachadaVisita.obtenerVisitasPorMedico(getActivity(), medico);
                BaseAdapter adaptadorbase = new AdaptadorListaVisitas(getActivity(),visitas);
                spinnerVisitas.setAdapter(adaptadorbase);
            }

        }

        //Gestionar set adapter a spinner visitas
        else if(v.equals(spinnerVisitas))
        {
            adapter = spinnerVisitas.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                textView_visitas.setVisibility(View.VISIBLE);
                spinnerVisitas.setVisibility(View.INVISIBLE);
                textView_visitas.setText("No se encontraron visitas");
                button_ver_detalles.setVisibility(View.INVISIBLE);
            }
            else
            {
                textView_visitas.setVisibility(View.VISIBLE);
                spinnerVisitas.setVisibility(View.VISIBLE);
                textView_visitas.setText("Selecciona visita");
                button_ver_detalles.setVisibility(View.VISIBLE);
            }

        }

    }
}
