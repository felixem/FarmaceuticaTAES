package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import farmaceutica.taes.domainmodel.Data.Dao.VisitaDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitadorDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.Visitador;

/**
 * Created by felix on 28/04/15.
 */

public class VisitadorRepository {
    private DatabaseHelper db;
    VisitadorDao mainDao;

    public VisitadorRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getVisitadorDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }


    public Visitador getByID(int id) throws SQLException {
        try
        {
            return mainDao.queryForId(id);
        }catch (SQLException e)
        {
            throw e;
        }
    }

    public int create(Visitador data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Visitador data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Visitador data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<Visitador> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public Visitador getByEMAIL(String email) throws SQLException {

        try {

            QueryBuilder<Visitador,Integer> builder = mainDao.queryBuilder();

            builder.where().eq(Visitador.EMAIL, email);

            Visitador v = builder.queryForFirst();
            return v;

        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            throw e;
        }

    }
}
