package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.MedicoRepository;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaComunicador;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;

/**
 * Created by John on 12/05/2015.
 */
public class ListaVisitas extends BaseFragment {

    private ListView lv;
    private TextView txt;
    private Visita visita;
    private FachadaVisita fachadaVisita;
    private FachadaComunicador fachadaComunicador;
    private Class<?> destino;
    private MedicoRepository repositorio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listavisitas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        fachadaVisita = new FachadaVisita();
        fachadaComunicador = new FachadaComunicador();

        txt= (TextView) view.findViewById(R.id.txt_visita_elegida);
        visita = fachadaComunicador.RecibirVisitaPosicion0();
        txt.setText("Visita: " + visita.getFechaVisita().toString() +" con el doctor: " + repositorio.getMedicoById(visita.getMedico().getId()).getNombre() + " " + repositorio.getMedicoById(visita.getMedico().getId()).getApellidos());

        destino = null;

        //Creamos el adaptador para el ListView
        List<Visita> visitas = fachadaVisita.obtenerVisitas(getActivity());
        BaseAdapter adapter= new AdaptadorListaVisitas(getActivity(), visitas);

        lv=(ListView) view.findViewById(R.id.txt_listavisitas);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                        "Cargando...", true);
                //Si retorna de la activity podemos perder la referencia al destino. Así aseguramos su permanencia
                if (destino == null)
                    destino = fachadaComunicador.RecibirDestinoPosicionFinal();

                Intent intent = new Intent(getActivity(), destino);
                startActivity(intent);
                dialog.cancel();
            }
        });
    }

    /*public void onBackPressed() {

        fachadaComunicador.volverAtras();
        super.onBackPressed();
    }*/

    public static ListaVisitas newInstance() {

        // Instantiate a new fragment
        ListaVisitas fragment = new ListaVisitas();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
