package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.CentroMedico;

/**
 * Created by felix on 28/04/15.
 */

public class CentroMedicoDao extends BaseDaoImpl<CentroMedico,Integer> implements ICentroMedicoDao
{
    // Constructor
    public CentroMedicoDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, CentroMedico.class);
    }
}
