package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.Ruta;

/**
 * Created by felix on 28/04/15.
 */

public class RutaDao extends BaseDaoImpl<Ruta,Integer> implements IRutaDao
{
    // Constructor
    public RutaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Ruta.class);
    }
}
