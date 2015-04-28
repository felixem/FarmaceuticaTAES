package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Visita;

/**
 * Created by felix on 28/04/15.
 */

public class VisitaDao extends BaseDaoImpl<Visita,Integer> implements IVisitaDao
{
    // Constructor
    public VisitaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Visita.class);
    }
}
