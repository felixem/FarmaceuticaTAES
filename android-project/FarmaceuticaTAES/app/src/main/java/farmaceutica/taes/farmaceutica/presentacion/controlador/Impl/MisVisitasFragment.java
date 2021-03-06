package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Repository.MedicoRepository;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMisVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaProductos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaVisitas;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.Linker;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.MySession;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaComunicador;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisita;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaVisitador;

/**
 * Created by John on 12/05/2015.
 */
public class MisVisitasFragment extends BaseFragment {

    ListView lv;
    Class<?> destino;
    FachadaVisita fachadaVisita;
    Visitador visitador;
    MySession session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_visitas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        lv = (ListView) view.findViewById(R.id.ListView_listaVisitas);
        TextView txt_titulo = (TextView) view.findViewById(R.id.txt_seleccioneProductos);

        session = (MySession) getActivity().getApplication();
        visitador = session.getVisitador();

        //Obtener la lista de visitas
        BaseAdapter adapter = new AdaptadorListaMisVisitas(getActivity(), FachadaVisita.obtenerVisitasPorVisitador(getActivity(), visitador));
        lv.setAdapter(adapter);

        //Cambiar texto en caso de que sea vacio
        if(adapter.isEmpty())
            txt_titulo.setText("No se encontraron visitas");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visita v = (Visita)lv.getItemAtPosition(position);
                session.setVisita(v);

                final VerVisitaFragment dialog = new VerVisitaFragment(MisVisitasFragment.this.getActivity(), R.style.AppTheme_Dialog, getActivity());
                dialog.setVisita(session.getVisita());
                dialog.cargarDialog();
                dialog.show();


            }
        });
    }

    public static MisVisitasFragment newInstance() {

        // Instantiate a new fragment
        MisVisitasFragment fragment = new MisVisitasFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
