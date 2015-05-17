package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.farmaceutica.R;

/**
 * Created by Fran on 17/05/2015.
 */
public class AdaptadorListaRutas extends BaseAdapter {

    private Context context;
    private List<Ruta> rutas;

    public int getCount() {
        return rutas.size();
    }

    public Object getItem(int position) {
        return rutas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView txtView;
    }

    public AdaptadorListaRutas(Context context, List<Ruta> datos){
        this.context=context;
        rutas=datos;
    }

    //Inflamos el elemento de la lista con los datos que queremos
    public View getView(int position, View convertView, ViewGroup parent) {
        //Optimizamos la creación del layout realizándola solo una primera vez.
        View item= convertView;
        ViewHolder holder;
        if(item==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            item = inflater.inflate(R.layout.list_item_default, null);

            holder= new ViewHolder();
            holder.txtView= (TextView)item.findViewById(R.id.txt_listitem_default);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //String date = sdf.format(rutas.get(position).getFechaVisita());



        Calendar cal = Calendar.getInstance();
        cal.setTime(rutas.get(position).getFecha());

        SimpleDateFormat format1 = new SimpleDateFormat("EE dd-MMM-yyyy");

        String formatted = format1.format(cal.getTime());



        holder.txtView.setText("" + formatted);
        holder.txtView.setGravity(View.TEXT_ALIGNMENT_CENTER);

        return item;
    }

}
