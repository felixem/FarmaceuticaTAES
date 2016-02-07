package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;

/**
 * Created by felix on 28/04/15.
 */

public class VisitaProductoDao extends BaseDaoImpl<VisitaProducto,Integer> implements IVisitaProductoDao
{
    // Constructor
    public VisitaProductoDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, VisitaProducto.class);
    }
}
