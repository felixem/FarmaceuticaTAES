package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;

/**
 * Created by felix on 28/04/15.
 */

public class ReporteGastosDao extends BaseDaoImpl<ReporteGastos,Integer> implements IReporteGastosDao
{
    private GastoDao gastoDao;

    // Constructor
    public ReporteGastosDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, ReporteGastos.class);
        gastoDao = new GastoDao(connectionSource);
    }

    //Crear reporte gastos
    public int create(ReporteGastos data) throws SQLException {

        this.assignEmptyForeignCollection(data,ReporteGastos.GASTOS_CAMPO);
        return super.create(data);
    }

    //Borrar reportes de gastos
    public int delete(ReporteGastos data) throws SQLException {
        for(Gasto gasto : data.getGastos())
        {
            gastoDao.delete(gasto);
        }

        return super.delete(data);
    }
}
