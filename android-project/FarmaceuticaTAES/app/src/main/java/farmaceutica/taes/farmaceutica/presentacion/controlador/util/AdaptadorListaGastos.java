package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.farmaceutica.R;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaGastos extends BaseAdapter {

    private List<Gasto> gastos;
    private Context context;

    public int getGastosCantidad(){return gastos.size();}

    public Gasto getGasto(int posicionArray){return gastos.get(posicionArray);}

    @Override
    public int getCount() {
        return gastos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return gastos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView gasto;
    }

    public AdaptadorListaGastos(Context context, List<Gasto> datos){
        this.context=context;
        this.gastos = datos;
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
            //item = inflater.inflate(R.layout.gasto, null);

            //holder.gasto=(TextView)item.findViewById(R.id.textView_gasto);
            item = inflater.inflate(android.R.layout.simple_spinner_item, null);
            holder.gasto = (TextView)item;

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        Gasto gasto = gastos.get(position);
        holder.gasto.setText(gasto.getConceptoGasto().getNombre().toString());

        return item;
    }
}
