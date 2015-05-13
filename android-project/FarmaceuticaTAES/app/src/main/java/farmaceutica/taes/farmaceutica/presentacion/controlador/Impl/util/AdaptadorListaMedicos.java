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
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.farmaceutica.R;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaMedicos extends BaseAdapter {

    private List<Medico> medicos;
    private Context context;

    public int getMedicosCantidad(){return medicos.size();}

    public Medico getMedico(int posicionArray){return medicos.get(posicionArray);}

    @Override
    public int getCount() {
        return medicos.size();
    }

    @Override
    public Object getItem(int position)
    {
                return medicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView nombre;
        TextView especialidad;
    }

    public AdaptadorListaMedicos(Context context, List<Medico> datos){
        this.context=context;
        this.medicos=datos;
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

            holder.nombre=(TextView)item.findViewById(R.id.textView_nombreMedico);
            holder.especialidad=(TextView)item.findViewById(R.id.textView_especialidadMedico);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        holder.nombre.setText(medicos.get(position).getNombre());

        return item;
    }
}
