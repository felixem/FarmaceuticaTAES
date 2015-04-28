package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Producto;

/**
 * Created by felix on 28/04/15.
 */

public class ProductoDao extends BaseDaoImpl<Producto,Integer> implements IProductoDao
{
    // Constructor
    public ProductoDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Producto.class);
    }
}
