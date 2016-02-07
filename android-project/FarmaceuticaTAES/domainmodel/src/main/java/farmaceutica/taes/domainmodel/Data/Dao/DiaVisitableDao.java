package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.DiaVisitable;

/**
 * Created by felix on 28/04/15.
 */

public class DiaVisitableDao extends BaseDaoImpl<DiaVisitable,Integer> implements IDiaVisitableDao
{
    // Constructor
    public DiaVisitableDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, DiaVisitable.class);
    }
}
