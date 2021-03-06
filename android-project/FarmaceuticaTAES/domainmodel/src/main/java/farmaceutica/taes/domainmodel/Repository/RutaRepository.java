package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.CitaVisitaDao;
import farmaceutica.taes.domainmodel.Data.Dao.RutaDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.CitaVisita;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.Visitador;

/**
 * Created by felix on 28/04/15.
 */

public class RutaRepository {
    private DatabaseHelper db;
    RutaDao mainDao;

    public RutaRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getRutaDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(Ruta data) throws SQLException {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            throw e;
        }
    }
    public int update(Ruta data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Ruta data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<Ruta> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Ruta> getByVisitador(Visitador visitador) throws SQLException {
        try {
            QueryBuilder<Ruta,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Ruta.VISITADOR, visitador);
            builder.orderBy(Ruta.FECHA,true);
            return builder.query();

        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            throw e;
        }
    }



}
