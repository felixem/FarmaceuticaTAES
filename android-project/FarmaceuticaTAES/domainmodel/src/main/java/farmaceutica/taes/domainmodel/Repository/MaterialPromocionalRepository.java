package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.AreaHospitalariaDao;
import farmaceutica.taes.domainmodel.Data.Dao.MaterialPromocionalDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.VisitaMaterial;

/**
 * Created by felix on 28/04/15.
 */

public class MaterialPromocionalRepository {
    private DatabaseHelper db;
    MaterialPromocionalDao mainDao;

    public MaterialPromocionalRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getMaterialPromocionalDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(MaterialPromocional data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(MaterialPromocional data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(MaterialPromocional data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public int refresh(MaterialPromocional data)
    {
        try {
            return mainDao.refresh(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public MaterialPromocional getMaterialById(int id)
    {
        try {
            return mainDao.queryForId(id);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<MaterialPromocional> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<MaterialPromocional> getProductosOfertados(int idVisita)//idVisita
    {

        try
        {
            QueryBuilder<MaterialPromocional,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(VisitaMaterial.ID, idVisita);
            builder.orderBy(VisitaMaterial.ID,true);

            return builder.query();
        }
        catch(SQLException e)
        {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
    public List<MaterialPromocional> getAllByProducto(Producto prod)
    {
        try {

            QueryBuilder<MaterialPromocional,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(MaterialPromocional.PRODUCTO, prod.getCodNacional());
            builder.orderBy(MaterialPromocional.NOMBRE,true);
            return builder.query();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
