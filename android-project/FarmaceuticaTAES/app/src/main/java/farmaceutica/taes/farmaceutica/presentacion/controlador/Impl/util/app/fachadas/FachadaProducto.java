package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Repository.ProductoRepository;

/**
 * Created by Gio on 13/05/2015.
 */
public class FachadaProducto {

    public List<Producto> obtenerProductos(Context context){

        ProductoRepository repository= new ProductoRepository(context);

        return repository.getAll();

    }
}
