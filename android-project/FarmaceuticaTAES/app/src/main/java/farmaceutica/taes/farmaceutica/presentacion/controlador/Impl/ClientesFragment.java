package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import farmaceutica.taes.domainmodel.Model.Trayectoria;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaCentrosMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.MySession;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaAreaHospitalaria;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaCentroMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaTrayectoria;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class ClientesFragment extends BaseFragment implements OnSpinnerListener{

    SpinnerOnChangeAdapter spinnerCentrosMedicos;
    SpinnerOnChangeAdapter spinnerMedicos;
    SpinnerOnChangeAdapter spinnerVisitas;
    TextView textView_centros;
    TextView textView_medicos;
    TextView textView_visitas;
    Button button_ver_detalles_medico;
    Button button_ver_detalles_visita;

    /***********************************************/
    TextView txt_infoAdicional,txt_tipoCliente,txt_visitable,txt_email,txt_tlf,txt_apellidos,txt_nombre,txt_numeroCorrelativo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clientes, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        spinnerCentrosMedicos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_centros_medicos);
        spinnerMedicos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_medicos);
        spinnerVisitas = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_visitas);
        textView_centros = (TextView) view.findViewById(R.id.txt_centro_medico);
        textView_medicos = (TextView) view.findViewById(R.id.txt_medico);
        textView_visitas = (TextView) view.findViewById(R.id.txt_visita);
        button_ver_detalles_medico = (Button) view.findViewById(R.id.button_ver_detalles_medico);
        button_ver_detalles_visita = (Button) view.findViewById(R.id.button_ver_detalles_visita);

        //Vincular los listeners
        spinnerCentrosMedicos.setOnSpinnerListener(this);
        spinnerMedicos.setOnSpinnerListener(this);
        spinnerVisitas.setOnSpinnerListener(this);

        //Obtener al area hospitalaria del visitador
        MySession session = (MySession) getActivity().getApplication();
        Visitador visitador = session.getVisitador();
        AreaHospitalaria area = FachadaAreaHospitalaria.obtenerAreaHospitalariaPorVisitador(getActivity(),visitador);

        //Vincular al spinner de centros médicos los centros médicos
        final BaseAdapter adapter = new AdaptadorListaCentrosMedicos(getActivity(), FachadaCentroMedico.obtenerCentrosMedicosPorArea(getActivity(),area));
        spinnerCentrosMedicos.setAdapter(adapter);

        //Establecer eventos en el spinner de centros médicos
        spinnerCentrosMedicos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()    {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {

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
                android.view.View v, int position, long id) {

            //Meter en el spinner de visitas las visitas del médico
            Medico medico = (Medico)parent.getItemAtPosition(position);
            List<Visita> visitas = FachadaVisita.obtenerVisitasPorMedico(getActivity(),medico);
            BaseAdapter adapter = new AdaptadorListaVisitas(getActivity(), visitas);
            spinnerVisitas.setAdapter(adapter);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
                });

        //Vincular al botón de crear productos ofertados la creación del dialog
        button_ver_detalles_medico.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verDetallesMedico();
                    }
                }
        );
    }

    /** Dialog */
    private void verDetallesMedico()
    {
        // custom dialog
        final Dialog dialog = new Dialog(ClientesFragment.this.getActivity(), R.style.AppTheme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detalles_medico);

        //Recojo al medico
        Medico mde = (Medico)spinnerMedicos.getSelectedItem();

        //Cojo Controladores del Dialog
        txt_infoAdicional = (TextView) dialog.findViewById(R.id.txt_infoAdicional);
        txt_tipoCliente = (TextView) dialog.findViewById(R.id.txt_tipoCliente);
        txt_visitable = (TextView) dialog.findViewById(R.id.txt_visitable);
        txt_email = (TextView) dialog.findViewById(R.id.txt_email);
        txt_tlf = (TextView) dialog.findViewById(R.id.txt_tlf);
        txt_apellidos = (TextView) dialog.findViewById(R.id.txt_apellidos);
        txt_nombre = (TextView) dialog.findViewById(R.id.txt_nombre);
        txt_numeroCorrelativo = (TextView) dialog.findViewById(R.id.txt_numeroCorrelativo);

        //Recojo datos
        txt_numeroCorrelativo.setText(""+mde.getNumCorrelativo());
        txt_nombre.setText(mde.getNombre());
        txt_apellidos.setText(mde.getApellidos());
        txt_tlf.setText(mde.getTelefono());
        txt_email.setText(mde.getEmail());

        if(mde.getVisitable())
            txt_visitable.setText("Permite");
        else
            txt_visitable.setText("No Permite");

        txt_tipoCliente.setText(mde.getTipoCliente().getNombre());
        txt_infoAdicional.setText(mde.getInformacionAdicional());

        dialog.show();
    }

    public static ClientesFragment newInstance() {

        // Instantiate a new fragment
        ClientesFragment fragment = new ClientesFragment();

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
                if(spinnerCentrosMedicos.getAdapter().isEmpty())
                    textView_medicos.setVisibility(View.INVISIBLE);
                else
                    textView_medicos.setVisibility(View.VISIBLE);

                spinnerMedicos.setVisibility(View.INVISIBLE);
                textView_medicos.setText("No se encontraron médicos");
                spinnerVisitas.setAdapter(new AdaptadorListaVisitas(getActivity(),new ArrayList<Visita>()));
                button_ver_detalles_medico.setVisibility(View.INVISIBLE);
            }
            else
            {
                textView_medicos.setVisibility(View.VISIBLE);
                spinnerMedicos.setVisibility(View.VISIBLE);
                textView_medicos.setText("Selecciona médico");
                button_ver_detalles_medico.setVisibility(View.VISIBLE);

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
                if(spinnerMedicos.getAdapter().isEmpty())
                    textView_visitas.setVisibility(View.INVISIBLE);
                else
                    textView_visitas.setVisibility(View.VISIBLE);

                spinnerVisitas.setVisibility(View.INVISIBLE);
                textView_visitas.setText("No se encontraron visitas");
                button_ver_detalles_visita.setVisibility(View.INVISIBLE);
            }
            else
            {
                textView_visitas.setVisibility(View.VISIBLE);
                spinnerVisitas.setVisibility(View.VISIBLE);
                textView_visitas.setText("Selecciona visita");
                button_ver_detalles_visita.setVisibility(View.VISIBLE);
            }

        }

    }
}
