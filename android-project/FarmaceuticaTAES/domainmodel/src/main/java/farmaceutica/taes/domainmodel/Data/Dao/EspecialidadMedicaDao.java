package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.EspecialidadMedica;

/**
 * Created by felix on 28/04/15.
 */

public class EspecialidadMedicaDao extends BaseDaoImpl<EspecialidadMedica,Integer> implements IEspecialidadMedicaDao
{
    // Constructor
    public EspecialidadMedicaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, EspecialidadMedica.class);
    }
}
