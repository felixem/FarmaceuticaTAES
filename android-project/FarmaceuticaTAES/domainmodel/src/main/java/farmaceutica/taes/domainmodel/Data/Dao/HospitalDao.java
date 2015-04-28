package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Hospital;

/**
 * Created by felix on 28/04/15.
 */

public class HospitalDao extends BaseDaoImpl<Hospital,Integer> implements IHospitalDao
{
    // Constructor
    public HospitalDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Hospital.class);
    }
}
