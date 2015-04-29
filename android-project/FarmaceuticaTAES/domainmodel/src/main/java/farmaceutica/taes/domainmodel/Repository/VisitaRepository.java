package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.MedicoDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitaDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.domainmodel.Model.Visitador;

/**
 * Created by felix on 28/04/15.
 */

public class VisitaRepository {
    private DatabaseHelper db;
    VisitaDao mainDao;

    public VisitaRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getVisitaDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(Visita data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Visita data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Visita data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<Visita> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Visita> getAllByMedico(Medico medico)
    {
        try {

            QueryBuilder<Visita,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Visita.MEDICO,medico);
            builder.orderBy(Visita.ID,true);
            return builder.query();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Visita> getAllByVisitador(Visitador visitador)
    {
        try {

            QueryBuilder<Visita,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Visita.VISITADOR,visitador);
            builder.orderBy(Visita.ID,true);
            return builder.query();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Visita> getAllByProducto(Producto producto)
    {
        try {

            QueryBuilder<Visita,Integer> builder = mainDao.queryBuilder();
            builder.join(db.getVisitaProductoDao().queryBuilder());
            builder.join(db.getProductoDao().queryBuilder());
            builder.where().eq(VisitaProducto.PRODUCTO, producto);
            builder.orderBy(Visita.ID,true);
            return builder.query();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }


}
