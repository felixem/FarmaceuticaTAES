package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas;

import android.content.Context;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Repository.ProductoRepository;

/**
 * Created by Gio on 13/05/2015.
 */
public class FachadaProducto {

    public List<Producto> obtenerProductos(Context context){

        ProductoRepository repository= new ProductoRepository(context);

        return repository.getAll();

    }
    public Producto obtenerProducto(Context context,int id){
        ProductoRepository repository= new ProductoRepository(context);

        return repository.getProductoById(id);

    }
    public int obtenerCantidadValoracionProducto(Context context,int idProducto,ValoracionProducto  valoracion) throws Exception {

        ProductoRepository repository= new ProductoRepository(context);

        return repository.getCantidadValoracionProducto(idProducto,valoracion);

    }
}
