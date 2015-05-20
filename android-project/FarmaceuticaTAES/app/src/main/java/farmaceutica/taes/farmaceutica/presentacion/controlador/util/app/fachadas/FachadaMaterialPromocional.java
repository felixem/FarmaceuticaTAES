package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Repository.MaterialPromocionalRepository;
import farmaceutica.taes.domainmodel.Repository.ProductoRepository;

/**
 * Created by Gio on 13/05/2015.
 */
public class FachadaMaterialPromocional {

    public static List<MaterialPromocional> obtenerMateriales(Context context){

        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.getAll();

    }

    public static List<MaterialPromocional> obtenerMaterialesPorProductoNotIn(Context context, Producto producto, List<Integer> materiales){

        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.getAllByProductoNotIn(producto, materiales);

    }

    public static MaterialPromocional obtenerMaterial(Context context,int id){
        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.getMaterialById(id);

    }

    public static List<MaterialPromocional> obtenerMaterialesPorProducto(Context context,Producto prod){
        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.getAllByProducto(prod);

    }

    public static List<MaterialPromocional> obtenerMaterialesPorVisita(Context context,Visita visita){
        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.getAllByVisita(visita);

    }

    public static int refresh(Context context, MaterialPromocional materialPromocional)
    {
        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.refresh(materialPromocional);
    }
}
