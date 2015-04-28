package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Provincia;

/**
 * Created by felix on 28/04/15.
 */

public class ProvinciaDao extends BaseDaoImpl<Provincia,Integer> implements IProvinciaDao
{
    // Constructor
    public ProvinciaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Provincia.class);
    }
}
