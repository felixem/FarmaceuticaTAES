package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;
import android.hardware.Camera;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.AmbulatorioDao;
import farmaceutica.taes.domainmodel.Data.Dao.AreaHospitalariaDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitadorAreaHospitalariaDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Model.VisitadorAreaHospitalaria;

/**
 * Created by felix on 28/04/15.
 */

public class AreaHospitalariaRepository {
    private DatabaseHelper db;
    private AreaHospitalariaDao mainDao;
    private VisitadorAreaHospitalariaDao visitadorAreaDao;

    public AreaHospitalariaRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getAreaHospitalariaDao();
            visitadorAreaDao = db.getVisitadorAreaHospitalariaDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(AreaHospitalaria data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(AreaHospitalaria data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(AreaHospitalaria data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public int refresh(AreaHospitalaria area) throws SQLException {
        return mainDao.refresh(area);
    }

    public List<AreaHospitalaria> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public AreaHospitalaria getAreaById(int id)
    {
        try {
            return mainDao.queryForId(id);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public AreaHospitalaria getAreaByVisitador(Visitador visitador) throws SQLException {
        QueryBuilder<AreaHospitalaria,Integer> builder = mainDao.queryBuilder();
        QueryBuilder<VisitadorAreaHospitalaria,Integer> visitadorAreaBuilder = visitadorAreaDao.queryBuilder();
        visitadorAreaBuilder.where().eq(VisitadorAreaHospitalaria.VISITADOR,visitador.getCodigo());
        builder.join(visitadorAreaBuilder);
        return builder.queryForFirst();
    }
}
