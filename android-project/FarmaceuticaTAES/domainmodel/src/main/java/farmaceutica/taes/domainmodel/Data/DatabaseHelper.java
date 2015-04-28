package farmaceutica.taes.domainmodel.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.ClinicaPrivada;
import farmaceutica.taes.domainmodel.Model.DiaVisitable;
import farmaceutica.taes.domainmodel.Model.EspecialidadMedica;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.Hospital;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.MedicoLugarTrabajo;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.Provincia;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Trayectoria;
import farmaceutica.taes.domainmodel.Model.VentaArea;
import farmaceutica.taes.domainmodel.Model.VentaAreaFecha;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.VisitaMaterial;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.domainmodel.Model.Visitador;

/**
 * Created by felix 22/04/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "localdb.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    //Daos utilizados

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {

            Log.i(DatabaseHelper.class.getName(), "onCreate");
            //Crear las tablas
            crearTablas();
            //Inicializar los datos
            inicializarDatos();

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }


    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            // after we drop the old databases, we create the new ones
            borrarTablas();
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    //Crear las tablas en la bd
    private void crearTablas() throws SQLException {

        //Crear las tablas en la bd
        TableUtils.createTable(connectionSource, Ambulatorio.class);
        TableUtils.createTable(connectionSource, AreaHospitalaria.class);
        TableUtils.createTable(connectionSource, CentroMedico.class);
        TableUtils.createTable(connectionSource, ClinicaPrivada.class);
        TableUtils.createTable(connectionSource, DiaVisitable.class);
        TableUtils.createTable(connectionSource, EspecialidadMedica.class);
        TableUtils.createTable(connectionSource, Gasto.class);
        TableUtils.createTable(connectionSource, Hospital.class);
        TableUtils.createTable(connectionSource, MaterialPromocional.class);
        TableUtils.createTable(connectionSource, Medico.class);
        TableUtils.createTable(connectionSource, MedicoLugarTrabajo.class);
        TableUtils.createTable(connectionSource, Producto.class);
        TableUtils.createTable(connectionSource, Provincia.class);
        TableUtils.createTable(connectionSource, ReporteGastos.class);
        TableUtils.createTable(connectionSource, Trayectoria.class);
        TableUtils.createTable(connectionSource, VentaArea.class);
        TableUtils.createTable(connectionSource, VentaAreaFecha.class);
        TableUtils.createTable(connectionSource, Visita.class);
        TableUtils.createTable(connectionSource, Visitador.class);
        TableUtils.createTable(connectionSource, VisitaMaterial.class);
        TableUtils.createTable(connectionSource, VisitaProducto.class);

    }

    //Borrar tablas de la bd
    private void borrarTablas() throws SQLException {

        //Borrar las tablas de la bd
        TableUtils.dropTable(connectionSource, Ambulatorio.class, true);
        TableUtils.dropTable(connectionSource, AreaHospitalaria.class,true);
        TableUtils.dropTable(connectionSource, CentroMedico.class,true);
        TableUtils.dropTable(connectionSource, ClinicaPrivada.class,true);
        TableUtils.dropTable(connectionSource, DiaVisitable.class,true);
        TableUtils.dropTable(connectionSource, EspecialidadMedica.class,true);
        TableUtils.dropTable(connectionSource, Gasto.class,true);
        TableUtils.dropTable(connectionSource, Hospital.class,true);
        TableUtils.dropTable(connectionSource, MaterialPromocional.class,true);
        TableUtils.dropTable(connectionSource, Medico.class,true);
        TableUtils.dropTable(connectionSource, MedicoLugarTrabajo.class,true);
        TableUtils.dropTable(connectionSource, Producto.class,true);
        TableUtils.dropTable(connectionSource, Provincia.class,true);
        TableUtils.dropTable(connectionSource, ReporteGastos.class,true);
        TableUtils.dropTable(connectionSource, Trayectoria.class,true);
        TableUtils.dropTable(connectionSource, VentaArea.class,true);
        TableUtils.dropTable(connectionSource, VentaAreaFecha.class,true);
        TableUtils.dropTable(connectionSource, Visita.class,true);
        TableUtils.dropTable(connectionSource, Visitador.class,true);
        TableUtils.dropTable(connectionSource, VisitaMaterial.class,true);
        TableUtils.dropTable(connectionSource, VisitaProducto.class,true);





    }

    //Insertar datos en la bd
    private void inicializarDatos() throws SQLException {

    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        limpiarDaos();
    }

    //Poner a null los daos
    public void limpiarDaos()
    {

    }

}
