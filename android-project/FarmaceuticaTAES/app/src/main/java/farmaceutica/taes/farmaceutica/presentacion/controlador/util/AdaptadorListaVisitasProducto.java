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
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaProducto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaValoracionProducto;


/**
 * Created by John on 08/04/2015.
 *
 * Este clase será modoficada en función de los valores que se quieran mostrar
 * a través de la consulta a la base de datos remota
 */
public class AdaptadorListaVisitasProducto extends BaseAdapter {

    private List<VisitaProducto> visitasProducto;
    private Context context;

    public int getVisitasProductoCantidad(){return visitasProducto.size();}

    public VisitaProducto getVisitaProducto(int posicionArray){return visitasProducto.get(posicionArray);}

    @Override
    public int getCount() {
        return visitasProducto.size();
    }

    @Override
    public Object getItem(int position)
    {
        return visitasProducto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Elemento utilizado para reutilización de instancias
    static class ViewHolder {
        TextView producto;
        RatingBar valoracion;
    }

    public AdaptadorListaVisitasProducto(Context context, List<VisitaProducto> datos){
        this.context=context;
        this.visitasProducto =datos;
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
            item = inflater.inflate(R.layout.visita_producto, null);


            holder.producto =(TextView)item.findViewById(R.id.textView_producto);
            holder.valoracion=(RatingBar)item.findViewById(R.id.rating_bar_valoracion);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        VisitaProducto visitaProd = visitasProducto.get(position);
        FachadaProducto.refresh(context,visitaProd.getProducto());

        ValoracionProducto valoracion = visitaProd.getValoracion();
        visitaProd.setValoracion(valoracion);

        holder.producto.setText(visitaProd.getOrden() + "º " + visitaProd.getProducto().getNombre());
        holder.valoracion.setNumStars(FachadaValoracionProducto.obtenerCantidadValoraciones()-1);
        holder.valoracion.setMax(FachadaValoracionProducto.maxValue());
        float val = valoracion.ordinal();
        holder.valoracion.setRating(val);

        return item;
    }
}
