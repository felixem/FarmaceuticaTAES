package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.Ruta;

/**
 * Created by felix on 28/04/15.
 */

public class RutaDao extends BaseDaoImpl<Ruta,Integer> implements IRutaDao
{
    private CitaDao citaDao;

    // Constructor
    public RutaDao(ConnectionSource connectionSource)
            throws SQLException {

        super(connectionSource, Ruta.class);
        citaDao = new CitaDao(connectionSource);
    }

    @Override
    public int create(Ruta data) throws SQLException {

        this.assignEmptyForeignCollection(data,Ruta.CITAS_CAMPO);
        return super.create(data);
    }

    @Override
    public int delete(Ruta data) throws SQLException
    {
        //Borrar las citas de la ruta
        for(Cita cita : data.getCitas())
        {
            citaDao.delete(cita);
        }

        return super.create(data);
    }
}
