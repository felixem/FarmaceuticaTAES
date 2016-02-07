package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;

/**
 * Created by felix on 28/04/15.
 */

public class AmbulatorioDao extends BaseDaoImpl<Ambulatorio,Integer> implements IAmbulatorioDao
{
    CentroMedicoDao centroDao;
    // Constructor
    public AmbulatorioDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Ambulatorio.class);

        centroDao = new CentroMedicoDao(connectionSource);
    }

    //Crear
    @Override
    public int create(Ambulatorio data) throws SQLException {

        //Crear centro medico
        centroDao.create(data);
        //Crear Ambulatorio
        return super.create(data);
    }

    //Actualizar
    @Override
    public int update(Ambulatorio data) throws SQLException {

        //Actualizar centro medico
        centroDao.update(data);
        //Actualizar Ambulatorio
        return super.update(data);
    }

    //Crear
    @Override
    public int delete(Ambulatorio data) throws SQLException {

        //Borrar centro medico
        centroDao.delete(data);
        //Borrar Ambulatorio
        return super.delete(data);
    }
}
