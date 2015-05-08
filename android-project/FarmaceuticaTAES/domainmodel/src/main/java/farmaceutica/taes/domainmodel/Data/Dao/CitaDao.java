package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.Gasto;

/**
 * Created by felix on 28/04/15.
 */

public class CitaDao extends BaseDaoImpl<Cita,Integer> implements ICitaDao
{
    // Constructor
    public CitaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Cita.class);
    }
}
