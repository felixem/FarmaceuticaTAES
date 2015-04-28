package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;

/**
 * Created by felix on 28/04/15.
 */

public class ReporteGastosDao extends BaseDaoImpl<ReporteGastos,Integer> implements IReporteGastosDao
{
    // Constructor
    public ReporteGastosDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, ReporteGastos.class);
    }
}
