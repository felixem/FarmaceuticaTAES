package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.farmaceutica.R;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaCentrosMedicos extends BaseAdapter {

    private List<CentroMedico> centros;
    private Context context;

    public int getCentrosMedicosCantidad(){return centros.size();}

    public CentroMedico getCentroMedico(int posicionArray){return centros.get(posicionArray);}

    @Override
    public int getCount() {
        return centros.size();
    }

    @Override
    public Object getItem(int position)
    {
                return centros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView nombre;
    }

    public AdaptadorListaCentrosMedicos(Context context, List<CentroMedico> datos){
        this.context=context;
        this.centros=datos;
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
            item = inflater.inflate(R.layout.centro_medico, null);

            holder.nombre=(TextView)item.findViewById(R.id.textView_nombreCentro);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        holder.nombre.setText(centros.get(position).getNombre());

        return item;
    }
}
