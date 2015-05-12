package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.CitaDao;
import farmaceutica.taes.domainmodel.Data.Dao.CitaVisitaDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.CitaVisita;

/**
 * Created by felix on 28/04/15.
 */

public class CitaVisitaRepository {
    private DatabaseHelper db;
    CitaVisitaDao mainDao;

    public CitaVisitaRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getCitaVisitaDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(CitaVisita data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(CitaVisita data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(CitaVisita data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<CitaVisita> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
