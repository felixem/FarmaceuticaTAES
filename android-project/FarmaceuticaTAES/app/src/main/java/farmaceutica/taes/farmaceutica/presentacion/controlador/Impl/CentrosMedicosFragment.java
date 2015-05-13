package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.AdaptadorListaCentrosMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.FragmentBase;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaCentroMedico;

/**
 * Created by John on 12/05/2015.
 */
public class CentrosMedicosFragment extends FragmentBase{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listado, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView lv = (ListView) view.findViewById(R.id.ListView_listado);

        //Obtener la lista de centros medicos
        FachadaCentroMedico fachada = new FachadaCentroMedico();
        AreaHospitalaria area = new AreaHospitalaria();
        area.setCodPostal(3009);

        BaseAdapter adapter = new AdaptadorListaCentrosMedicos(getActivity(), fachada.obtenerCentrosMedicosPorArea(getActivity(),area));
        lv.setAdapter(adapter);
    }

    public static CentrosMedicosFragment newInstance() {

        // Instantiate a new fragment
        CentrosMedicosFragment fragment = new CentrosMedicosFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
