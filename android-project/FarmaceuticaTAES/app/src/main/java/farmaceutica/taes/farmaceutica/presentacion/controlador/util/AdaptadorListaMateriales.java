package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.farmaceutica.R;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaMateriales extends BaseAdapter {

    private List<MaterialPromocional> materiales;
    private Context context;

    public int getMaterialesCantidad(){return materiales.size();}

    public MaterialPromocional getMaterial(int posicionArray){return materiales.get(posicionArray);}

    @Override
    public int getCount() {
        return materiales.size();
    }

    @Override
    public Object getItem(int position)
    {
        return materiales.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView nombre;
    }

    public AdaptadorListaMateriales(Context context, List<MaterialPromocional> datos){
        this.context=context;
        this.materiales =datos;
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
            item = inflater.inflate(R.layout.material_promocional, null);

            holder.nombre=(TextView)item.findViewById(R.id.textView_nombre);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }


        holder.nombre.setText(materiales.get(position).getNombre());

        return item;
    }
}
