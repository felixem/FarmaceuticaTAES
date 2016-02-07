package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Trayectoria;

/**
 * Created by felix on 28/04/15.
 */

public class TrayectoriaDao extends BaseDaoImpl<Trayectoria,Integer> implements ITrayectoriaDao
{
    // Constructor
    public TrayectoriaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Trayectoria.class);
    }
}
