package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Gasto;

/**
 * Created by felix on 28/04/15.
 */

public class GastoDao extends BaseDaoImpl<Gasto,Integer> implements IGastoDao
{
    // Constructor
    public GastoDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Gasto.class);
    }
}
