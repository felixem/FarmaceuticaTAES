package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Repository.MaterialPromocionalRepository;
import farmaceutica.taes.domainmodel.Repository.ProductoRepository;

/**
 * Created by Gio on 13/05/2015.
 */
public class FachadaMaterialPromocional {

    public List<MaterialPromocional> obtenerMateriales(Context context){

        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.getAll();

    }
    public MaterialPromocional obtenerMaterial(Context context,int id){
        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.getMaterialById(id);

    }

    public static int refresh(Context context, MaterialPromocional materialPromocional)
    {
        MaterialPromocionalRepository repository= new MaterialPromocionalRepository(context);
        return repository.refresh(materialPromocional);
    }
}
