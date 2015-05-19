package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.MaterialPromocionalRepository;
import farmaceutica.taes.farmaceutica.R;

import farmaceutica.taes.domainmodel.Repository.VisitaRepository;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas.FachadaVisita;

/**
 * Created by User on 19/05/2015.
 */
public class DetallesVisita extends
{
    //private VisitaRepository visita;
    private DialogInterface.OnClickListener listener; // obtengo el id especifico de la visita
    private Visita visita;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.verDetallesVisita,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        Bundle b = getA

        TextView fechaVisita = (TextView)view.findViewById(R.id.DatoFechaVisita);
        TextView fechaReporte = (TextView)view.findViewById(R.id.DatoFechaReporte);
        TextView minutos =(TextView)view.findViewById(R.id.DatoMinutos);
        TextView lugarVisita = (TextView) view.findViewById(R.id.DatoLugarVisita);

        TextView acompañado = (TextView) view.findViewById(R.id.DatoAcompañado);
        TextView obserbaciones = (TextView) view.findViewById(R.id.DatoObservaciones);
        TextView productosPromocionados = (TextView) view.findViewById(R.id.DatoProductosPromocionados);

        FachadaVisita visitaFachada = new FachadaVisita();
        visita = visitaFachada.ObtenerVisitasPorId(this,bundle.);
        MaterialPromocionalRepository numProductosPromocionados = new MaterialPromocionalRepository();


        fechaVisita.setText(visita.getFechaVisita().toString());
        fechaReporte.setText(visita.getFechaReporte().toString());
        minutos.setText(visita.getMinutos().toString());
        lugarVisita.setText(visita.getLugarVisita().toString());
        String sacomp = visita.getAcompanyado() ? "Si" : "No";
        acompañado.setText(sacomp);
        obserbaciones.setText(visita.getObservaciones());
        productosPromocionados.setText(numProductosPromocionados.CountProductosPromocionads(/*le paso el id de la visita actual*/););
    }

}
