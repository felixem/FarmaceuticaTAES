package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;
import android.hardware.Camera;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.AreaHospitalariaDao;
import farmaceutica.taes.domainmodel.Data.Dao.MedicoDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.EspecialidadMedica;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.MedicoLugarTrabajo;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;

/**
 * Created by felix on 28/04/15.
 */

public class MedicoRepository {
    private DatabaseHelper db;
    MedicoDao mainDao;

    public MedicoRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getMedicoDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(Medico data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Medico data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Medico data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<Medico> getAll()
    {
        try {
            QueryBuilder<Medico,Integer> builder = mainDao.queryBuilder();
            builder.orderBy(Medico.APELLIDOS,true);
            return builder.query();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Medico> getAllByEspecialidad(EspecialidadMedica especialidad)
    {
        try {

            QueryBuilder<Medico,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Medico.ESPECIALIDAD, especialidad.getId());
            builder.orderBy(Medico.APELLIDOS,true);
            return builder.query();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public Medico getMedicoById(int id){

        try{
            QueryBuilder<Medico,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Medico.ID, id);
            builder.orderBy(Medico.APELLIDOS,true);
            return builder.query().get(0);
        }catch(SQLException e){
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Medico> getAllByCentroMedico(CentroMedico centroMedico)
    {
        try {

            QueryBuilder<Medico,Integer> builder = mainDao.queryBuilder();
            QueryBuilder<MedicoLugarTrabajo,Integer> builderRelacion = db.getMedicoLugarTrabajoDao().queryBuilder();
            builderRelacion.where().eq(MedicoLugarTrabajo.CENTROMEDICO, centroMedico.getId());
            builder.join(builderRelacion);
            builder.orderBy(Medico.APELLIDOS,true);
            return builder.query();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Medico> getAllByAreaHospitalaria(AreaHospitalaria area)
    {
        try {

            QueryBuilder<Medico,Integer> builder = mainDao.queryBuilder();
            QueryBuilder<MedicoLugarTrabajo,Integer> builderLugarTrabajo = db.getMedicoLugarTrabajoDao().queryBuilder();
            QueryBuilder<CentroMedico,Integer> builderCentroMedico = db.getCentroMedicoDao().queryBuilder();

            builderCentroMedico.where().eq(CentroMedico.AREAHOSPITALARIA, area.getCodPostal());
            builderLugarTrabajo.join(builderCentroMedico);

            builder.join(builderLugarTrabajo);

            builder.orderBy(Medico.APELLIDOS,true);
            return builder.query();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public int refresh(Medico data)
    {
        try {
            return mainDao.refresh(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
