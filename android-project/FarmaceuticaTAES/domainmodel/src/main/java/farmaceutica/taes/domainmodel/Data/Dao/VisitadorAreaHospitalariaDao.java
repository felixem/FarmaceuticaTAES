package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.VisitadorAreaHospitalaria;

/**
 * Created by felix on 7/05/15.
 */
public class VisitadorAreaHospitalariaDao extends BaseDaoImpl<VisitadorAreaHospitalaria,Integer> implements  IVisitadorAreaHospitalariaDao {
    // Constructor
    public VisitadorAreaHospitalariaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, VisitadorAreaHospitalaria.class);
    }
}
