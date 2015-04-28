package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Medico;

/**
 * Created by felix on 28/04/15.
 */

public class MedicoDao extends BaseDaoImpl<Medico,Integer> implements IMedicoDao
{
    // Constructor
    public MedicoDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Medico.class);
    }
}
