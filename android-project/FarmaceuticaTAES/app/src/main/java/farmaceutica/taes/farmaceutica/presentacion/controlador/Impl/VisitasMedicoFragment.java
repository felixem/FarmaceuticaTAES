package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.AdaptadorListaCentrosMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.FragmentBase;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaCentroMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.listeners.ListenerSpinnerCentrosMedicos;

/**
 * Created by John on 12/05/2015.
 */
public class VisitasMedicoFragment extends FragmentBase{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visitas_medicos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        final Spinner spinnerCentrosMedicos = (Spinner) view.findViewById(R.id.spinner_centros_medicos);
        final Spinner spinnerMedicos = (Spinner) view.findViewById(R.id.spinner_medicos);
        final Spinner spinnerVisitas = (Spinner) view.findViewById(R.id.spinner_visitas);
        final TextView textView_centros = (TextView) view.findViewById(R.id.txt_centro_medico);
        final TextView textView_medicos = (TextView) view.findViewById(R.id.txt_medico);
        final TextView textView_visitas = (TextView) view.findViewById(R.id.txt_visita);

        //Obtener la lista de centros medicos
        final FachadaCentroMedico fachadaCentro = new FachadaCentroMedico();
        final FachadaMedico fachadaMedico = new FachadaMedico();

        //Provisionalmente creado un área hospitalaria que será la del visitador actual
        AreaHospitalaria area = new AreaHospitalaria();
        area.setCodPostal(3009);

        //Vincular al spinner de centros médicos los centros médicos
        final BaseAdapter adapter = new AdaptadorListaCentrosMedicos(getActivity(), fachadaCentro.obtenerCentrosMedicosPorArea(getActivity(),area));
        spinnerCentrosMedicos.setAdapter(adapter);

        //Ocultar el resto de elementos
        spinnerCentrosMedicos.setVisibility(View.INVISIBLE);
        spinnerMedicos.setVisibility(View.INVISIBLE);
        spinnerVisitas.setVisibility(View.INVISIBLE);
        textView_medicos.setVisibility(View.INVISIBLE);
        textView_visitas.setVisibility(View.INVISIBLE);

        //Establecer eventos en el spinner de centros médicos
        spinnerCentrosMedicos.setOnItemSelectedListener(
                new ListenerSpinnerCentrosMedicos(spinnerCentrosMedicos,spinnerMedicos,
                        textView_centros, textView_medicos, getActivity()));

        //Establecer eventos en el spinner de médicos
        spinnerMedicos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()    {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                android.view.View v, int position, long id) {

            spinnerMedicos.setVisibility(View.VISIBLE);
            textView_medicos.setVisibility(View.VISIBLE);
            textView_medicos.setText("Selecciona un médico");

            //Meter en el spinner de visitas las visitas del médico
            Medico medico = (Medico)parent.getItemAtPosition(position);
            List<Visita> visitas = FachadaVisita.obtenerVisitasPorMedico(getActivity(),medico);
            BaseAdapter adapter = new AdaptadorListaVisitas(getActivity(), visitas);

            spinnerVisitas.setAdapter(adapter);
            textView_visitas.setVisibility(View.VISIBLE);

            if(visitas.size() != 0)
            {
                textView_visitas.setText("Selecciona una visita");
                spinnerVisitas.setVisibility(View.VISIBLE);
            }
            else
            {
                textView_visitas.setText("No se encontraron visitas");
                spinnerVisitas.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            textView_medicos.setVisibility(View.VISIBLE);
            textView_medicos.setText("No se encontraron médicos");
            spinnerMedicos.setVisibility(View.INVISIBLE);
            textView_visitas.setVisibility(View.INVISIBLE);
        }
                });

    }

    public static VisitasMedicoFragment newInstance() {

        // Instantiate a new fragment
        VisitasMedicoFragment fragment = new VisitasMedicoFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
