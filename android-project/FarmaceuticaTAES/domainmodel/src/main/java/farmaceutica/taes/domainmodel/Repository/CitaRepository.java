package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.CitaDao;
import farmaceutica.taes.domainmodel.Data.Dao.ClinicaPrivadaDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.ClinicaPrivada;
import farmaceutica.taes.domainmodel.Model.Ruta;

/**
 * Created by felix on 28/04/15.
 */

public class CitaRepository {
    private DatabaseHelper db;
    CitaDao mainDao;

    public CitaRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getCitaDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(Cita data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Cita data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Cita data) throws SQLException {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Cita> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Cita> getByRuta(Ruta ruta) throws SQLException {
        try {
            QueryBuilder<Cita,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Cita.RUTA, ruta);
            builder.orderBy(Cita.HORAINICIO,true);
            return builder.query();

        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            throw e;
        }

    }
}
