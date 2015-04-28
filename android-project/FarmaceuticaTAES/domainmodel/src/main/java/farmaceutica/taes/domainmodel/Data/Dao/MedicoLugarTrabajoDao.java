package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.MedicoLugarTrabajo;

/**
 * Created by felix on 28/04/15.
 */

public class MedicoLugarTrabajoDao extends BaseDaoImpl<MedicoLugarTrabajo,Integer> implements IMedicoLugarTrabajoDao
{
    // Constructor
    public MedicoLugarTrabajoDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, MedicoLugarTrabajo.class);
    }
}
