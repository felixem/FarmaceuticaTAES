package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;

/**
 * Created by felix on 28/04/15.
 */

public class AmbulatorioDao extends BaseDaoImpl<Ambulatorio,Integer> implements IAmbulatorioDao
{
    // Constructor
    public AmbulatorioDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Ambulatorio.class);
    }
}
