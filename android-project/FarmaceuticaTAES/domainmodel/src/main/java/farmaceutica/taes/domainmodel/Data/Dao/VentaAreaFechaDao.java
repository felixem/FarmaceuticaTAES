package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.VentaAreaFecha;

/**
 * Created by felix on 28/04/15.
 */

public class VentaAreaFechaDao extends BaseDaoImpl<VentaAreaFecha,Integer> implements IVentaAreaFechaDao
{
    // Constructor
    public VentaAreaFechaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, VentaAreaFecha.class);
    }
}
