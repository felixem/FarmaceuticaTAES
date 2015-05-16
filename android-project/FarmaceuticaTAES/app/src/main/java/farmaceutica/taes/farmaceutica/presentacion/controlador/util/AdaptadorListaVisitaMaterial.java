package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Model.VisitaMaterial;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMaterialPromocional;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaValoracionProducto;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaVisitaMaterial extends BaseAdapter {

    private List<VisitaMaterial> visitasMaterial;
    private Context context;

    public int getVisitasMaterialCantidad(){return visitasMaterial.size();}

    public VisitaMaterial getVisitaMaterial(int posicionArray){return visitasMaterial.get(posicionArray);}

    @Override
    public int getCount() {
        return visitasMaterial.size();
    }

    @Override
    public Object getItem(int position)
    {
        return visitasMaterial.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView material;
    }

    public AdaptadorListaVisitaMaterial(Context context, List<VisitaMaterial> datos){
        this.context=context;
        this.visitasMaterial =datos;
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
            item = inflater.inflate(R.layout.visita_material, null);

            holder.material =(TextView)item.findViewById(R.id.textView_visita_material);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        VisitaMaterial visitaMaterial = visitasMaterial.get(position);
        FachadaMaterialPromocional.refresh(context, visitaMaterial.getMaterialPromocional());
        holder.material.setText(visitaMaterial.getCantidad() + " " + visitaMaterial.getMaterialPromocional().getNombre());

        return item;
    }
}
