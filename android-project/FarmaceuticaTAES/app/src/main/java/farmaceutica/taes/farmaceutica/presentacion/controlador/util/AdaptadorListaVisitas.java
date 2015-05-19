package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.MedicoRepository;
import farmaceutica.taes.farmaceutica.R;

import java.text.SimpleDateFormat;
import java.util.List;
/**
 * Created by Javi on 12/05/2015.
 */
public class AdaptadorListaVisitas extends BaseAdapter {

    private Context context;
    private List<Visita> visitas;

    public int getCount() {
        return visitas.size();
    }

    public Object getItem(int position) {
        return visitas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView txtView;
    }

    public AdaptadorListaVisitas(Context context, List<Visita> datos){
        this.context=context;
        visitas=datos;
    }

    //Inflamos el elemento de la lista con los datos que queremos
    public View getView(int position, View convertView, ViewGroup parent) {
        //Optimizamos la creación del layout realizándola solo una primera vez.
        View item= convertView;
        ViewHolder holder;
        if(item==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            //item = inflater.inflate(R.layout.list_item_default, null);

            holder= new ViewHolder();
            //holder.txtView= (TextView)item.findViewById(R.id.txt_listitem_default);
            item = inflater.inflate(android.R.layout.simple_spinner_item, null);
            holder.txtView = (TextView)item;

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(visitas.get(position).getFechaVisita());
        holder.txtView.setText("Visita a las " + date);

        return item;
    }

}
