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
    CentroMedicoDao centroDao;
    // Constructor
    public HospitalDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, Hospital.class);

        centroDao = new CentroMedicoDao(connectionSource);
    }

    //Crear
    @Override
    public int create(Hospital data) throws SQLException {

        //Crear centro medico
        centroDao.create(data);
        //Crear hospital
        return super.create(data);
    }

    //Crear
    @Override
    public int update(Hospital data) throws SQLException {

        //Crear centro medico
        centroDao.update(data);
        //Crear hospital
        return super.update(data);
    }

    //Crear
    @Override
    public int delete(Hospital data) throws SQLException {

        //Crear centro medico
        centroDao.delete(data);
        //Crear hospital
        return super.delete(data);
    }
}
