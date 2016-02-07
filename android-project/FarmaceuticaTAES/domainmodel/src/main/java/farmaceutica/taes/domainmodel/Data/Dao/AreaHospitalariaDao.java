package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;

/**
 * Created by felix on 28/04/15.
 */

public class AreaHospitalariaDao extends BaseDaoImpl<AreaHospitalaria,Integer> implements IAreaHospitalariaDao
{
    // Constructor
    public AreaHospitalariaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, AreaHospitalaria.class);
    }
}
