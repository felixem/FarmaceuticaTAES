package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.farmaceutica.R;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaReportes extends BaseAdapter {

    private List<ReporteGastos> reportes;
    private Context context;

    public int getReportesCantidad(){return reportes.size();}

    public ReporteGastos getReporte(int posicionArray){return reportes.get(posicionArray);}

    @Override
    public int getCount() {
        return reportes.size();
    }

    @Override
    public Object getItem(int position)
    {
                return reportes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView reporte;
    }

    public AdaptadorListaReportes(Context context, List<ReporteGastos> datos){
        this.context=context;
        this.reportes=datos;
    }

    //Inflamos el elemento de la lista con los datos que queremos
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Optimizamos la creación del layout realizándola solo una primera vez.
        View item= convertView;
        ViewHolder holder;
        if(item==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            holder= new ViewHolder();
            item = inflater.inflate(R.layout.reporte, null);

            holder.reporte=(TextView)item.findViewById(R.id.textView_reporte);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        ReporteGastos reporte = reportes.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(reporte.getFecha());

        holder.reporte.setText("Reporte del día "+date);

        return item;
    }
}
