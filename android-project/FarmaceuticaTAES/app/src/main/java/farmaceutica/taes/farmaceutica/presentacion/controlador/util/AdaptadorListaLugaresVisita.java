package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.farmaceutica.R;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaLugaresVisita extends BaseAdapter {

    private List<LugarVisita> lugaresVisita;
    private Context context;

    public int getLugaresVisitaCantidad(){return lugaresVisita.size();}

    public LugarVisita getLugarVisita(int posicionArray){return lugaresVisita.get(posicionArray);}

    @Override
    public int getCount() {
        return lugaresVisita.size();
    }

    @Override
    public Object getItem(int position)
    {
                return lugaresVisita.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView nombre;
    }

    public AdaptadorListaLugaresVisita(Context context, List<LugarVisita> datos){
        this.context=context;
        this.lugaresVisita =datos;
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
            item = inflater.inflate(R.layout.lugar_visita, null);

            holder.nombre=(TextView)item.findViewById(R.id.textView_nombre_lugar_visita);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        LugarVisita lugar = lugaresVisita.get(position);
        holder.nombre.setText(lugar.toString());

        return item;
    }
}
