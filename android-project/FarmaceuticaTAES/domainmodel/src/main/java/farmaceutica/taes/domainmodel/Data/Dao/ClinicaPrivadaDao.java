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
    CentroMedicoDao centroDao;
    // Constructor
    public ClinicaPrivadaDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, ClinicaPrivada.class);

        centroDao = new CentroMedicoDao(connectionSource);
    }

    //Crear
    @Override
    public int create(ClinicaPrivada data) throws SQLException {

        //Crear centro medico
        centroDao.create(data);
        //Crear Ambulatorio
        return super.create(data);
    }

    //Actualizar
    @Override
    public int update(ClinicaPrivada data) throws SQLException {

        //Modificar centro medico
        centroDao.update(data);
        //Modificar Ambulatorio
        return super.update(data);
    }

    //Borrar
    @Override
    public int delete(ClinicaPrivada data) throws SQLException {

        //Borrar centro medico
        centroDao.delete(data);
        //Borrar Ambulatorio
        return super.delete(data);
    }
}
