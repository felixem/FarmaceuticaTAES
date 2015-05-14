package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.farmaceutica.R;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaValoraciones extends BaseAdapter {

    private ArrayList<Pair<ValoracionProducto,Integer>> valoraciones;
    private Context context;

    public int getValoracionesCantidad(){return valoraciones.size();}

    public Pair<ValoracionProducto,Integer> getProducto(int posicionArray){return valoraciones.get(posicionArray);}

    @Override
    public int getCount() {
        return valoraciones.size();
    }

    @Override
    public Object getItem(int position)
    {
        return valoraciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView valoracion;
        TextView cantidad;
    }

    public AdaptadorListaValoraciones(Context context, ArrayList<Pair<ValoracionProducto,Integer>> datos){
        this.context=context;
        this.valoraciones=datos;
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
            item = inflater.inflate(R.layout.listado, null);

            holder.valoracion= (TextView)item.findViewById(R.id.textView_valoracion);
            holder.cantidad=(TextView)item.findViewById(R.id.textView_cantidad);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        holder.valoracion.setText(valoraciones.get(position).first.toString());
        holder.cantidad.setText(valoraciones.get(position).second);


        return item;
    }
}
