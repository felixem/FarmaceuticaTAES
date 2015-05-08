package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.CitaVisita;
import farmaceutica.taes.domainmodel.Model.Gasto;

/**
 * Created by felix on 28/04/15.
 */

public class CitaVisitaDao extends BaseDaoImpl<CitaVisita,Integer> implements ICitaVisitaDao
{
    // Constructor
    public CitaVisitaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, CitaVisita.class);
    }
}
