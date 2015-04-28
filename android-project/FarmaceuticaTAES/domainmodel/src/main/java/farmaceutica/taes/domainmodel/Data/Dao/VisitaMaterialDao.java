package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.VisitaMaterial;

/**
 * Created by felix on 28/04/15.
 */

public class VisitaMaterialDao extends BaseDaoImpl<VisitaMaterial,Integer> implements IVisitaMaterialDao
{
    // Constructor
    public VisitaMaterialDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, VisitaMaterial.class);
    }
}
