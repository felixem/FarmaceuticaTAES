package farmaceutica.taes.farmaceutica.presentacion.controlador.util.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import farmaceutica.taes.domainmodel.Model.ConceptoGasto;
import farmaceutica.taes.farmaceutica.R;

/**
 * Created by John on 17/05/2015.
 */
public class AdaptadorSpinnerConceptoGasto extends BaseAdapter{

    private Context context;

    public AdaptadorSpinnerConceptoGasto(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return ConceptoGasto.total_estados;
    }

    @Override
    public Object getItem(int position) {
        switch (position){
            case 0:
                return ConceptoGasto.COMIDA;
            case 1:
                return ConceptoGasto.PARKING;
            case 2:
                return ConceptoGasto.TRANSPORTE;
            default:
                throw new RuntimeException("Se ha intentado acceder a un estado no valido");
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item= convertView;
        ViewHolder holder;
        if(item==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            holder= new ViewHolder();
            item = inflater.inflate(R.layout.item_spinner_default, null);

            holder.txt=(TextView)item.findViewById(R.id.txt_default);

            //Almacenamos el elemento en como un tag de la View
            item.setTag(holder);
        }else{
            //Si el item ya ha sido instanciado con anterioridad lo recuperamos del convertView
            holder= (ViewHolder)item.getTag();
        }

        holder.txt.setText(((ConceptoGasto)getItem(position)).getNombre());

        return item;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    static class ViewHolder {
        TextView txt;
    }
}
