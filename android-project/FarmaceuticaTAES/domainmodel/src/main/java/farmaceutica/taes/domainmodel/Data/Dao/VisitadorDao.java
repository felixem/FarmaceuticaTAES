package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Visitador;

/**
 * Created by felix on 28/04/15.
 */

public class VisitadorDao extends BaseDaoImpl<Visitador,Integer> implements IVisitadorDao
{
    // Constructor
    public VisitadorDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Visitador.class);
    }
}
