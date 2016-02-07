package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.VentaArea;

/**
 * Created by felix on 28/04/15.
 */

public class VentaAreaDao extends BaseDaoImpl<VentaArea,Integer> implements IVentaAreaDao
{
    // Constructor
    public VentaAreaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, VentaArea.class);
    }
}
