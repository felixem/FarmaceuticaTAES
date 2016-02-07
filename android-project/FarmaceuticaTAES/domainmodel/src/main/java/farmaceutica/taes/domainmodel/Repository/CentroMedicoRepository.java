package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.CentroMedicoDao;
import farmaceutica.taes.domainmodel.Data.Dao.ClinicaPrivadaDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.ClinicaPrivada;

/**
 * Created by felix on 28/04/15.
 */

public class CentroMedicoRepository {
    private DatabaseHelper db;
    CentroMedicoDao mainDao;

    public CentroMedicoRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getCentroMedicoDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(CentroMedico data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(CentroMedico data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(CentroMedico data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<CentroMedico> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<CentroMedico> getAllByAreaHospitalaria(AreaHospitalaria area)
    {
        try {

            QueryBuilder<CentroMedico,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(CentroMedico.AREAHOSPITALARIA, area.getCodPostal());
            builder.orderBy(CentroMedico.NOMBRE,true);
            return builder.query();

        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            e.printStackTrace();
        }

        return null;
    }

    public CentroMedico getCentroMedicoById(int id)
    {
        try {
            return mainDao.queryForId(id);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public int refresh(CentroMedico data)
    {
        try {
            return mainDao.refresh(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
