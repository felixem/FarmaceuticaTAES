package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.ClinicaPrivada;

/**
 * Created by felix on 28/04/15.
 */

public class ClinicaPrivadaDao extends BaseDaoImpl<ClinicaPrivada,Integer> implements IClinicaPrivadaDao
{
    // Constructor
    public ClinicaPrivadaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, ClinicaPrivada.class);
    }
}
