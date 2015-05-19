package farmaceutica.taes.domainmodel.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import farmaceutica.taes.domainmodel.Data.Dao.AmbulatorioDao;
import farmaceutica.taes.domainmodel.Data.Dao.AreaHospitalariaDao;
import farmaceutica.taes.domainmodel.Data.Dao.CentroMedicoDao;
import farmaceutica.taes.domainmodel.Data.Dao.CitaDao;
import farmaceutica.taes.domainmodel.Data.Dao.CitaVisitaDao;
import farmaceutica.taes.domainmodel.Data.Dao.ClinicaPrivadaDao;
import farmaceutica.taes.domainmodel.Data.Dao.DiaVisitableDao;
import farmaceutica.taes.domainmodel.Data.Dao.EspecialidadMedicaDao;
import farmaceutica.taes.domainmodel.Data.Dao.GastoDao;
import farmaceutica.taes.domainmodel.Data.Dao.HospitalDao;
import farmaceutica.taes.domainmodel.Data.Dao.MaterialPromocionalDao;
import farmaceutica.taes.domainmodel.Data.Dao.MedicoDao;
import farmaceutica.taes.domainmodel.Data.Dao.MedicoLugarTrabajoDao;
import farmaceutica.taes.domainmodel.Data.Dao.ProductoDao;
import farmaceutica.taes.domainmodel.Data.Dao.ProvinciaDao;
import farmaceutica.taes.domainmodel.Data.Dao.ReporteGastosDao;
import farmaceutica.taes.domainmodel.Data.Dao.RutaDao;
import farmaceutica.taes.domainmodel.Data.Dao.TrayectoriaDao;
import farmaceutica.taes.domainmodel.Data.Dao.VentaAreaDao;
import farmaceutica.taes.domainmodel.Data.Dao.VentaAreaFechaDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitaDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitaMaterialDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitaProductoDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitadorAreaHospitalariaDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitadorDao;
import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.AreaHospitalaria;
import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Cita;
import farmaceutica.taes.domainmodel.Model.CitaVisita;
import farmaceutica.taes.domainmodel.Model.ClinicaPrivada;
import farmaceutica.taes.domainmodel.Model.ConceptoGasto;
import farmaceutica.taes.domainmodel.Model.DiaVisitable;
import farmaceutica.taes.domainmodel.Model.EspecialidadMedica;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.Hospital;
import farmaceutica.taes.domainmodel.Model.LugarVisita;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.MedicoLugarTrabajo;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.Provincia;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Ruta;
import farmaceutica.taes.domainmodel.Model.TipoCliente;
import farmaceutica.taes.domainmodel.Model.TipoMaterial;
import farmaceutica.taes.domainmodel.Model.Trayectoria;
import farmaceutica.taes.domainmodel.Model.VentaArea;
import farmaceutica.taes.domainmodel.Model.VentaAreaFecha;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.VisitaMaterial;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.domainmodel.Model.VisitadorAreaHospitalaria;

/**
 * Created by felix 22/04/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "localdb.db";
    // any time you make changes to your database objects, you may have to increase the database version


    private static final int DATABASE_VERSION = 33;

    //Daos utilizados
    private AmbulatorioDao ambulatorioDao;
    private AreaHospitalariaDao areaHospitalariaDao;
    private CentroMedicoDao centroMedicoDao;
    private CitaDao citaDao;
    private CitaVisitaDao citaVisitaDao;
    private ClinicaPrivadaDao clinicaPrivadaDao;
    private DiaVisitableDao diaVisitableDao;
    private EspecialidadMedicaDao especialidadMedicaDao;
    private GastoDao gastoDao;
    private HospitalDao hospitalDao;
    private MaterialPromocionalDao materialPromocionalDao;
    private MedicoDao medicoDao;
    private MedicoLugarTrabajoDao medicoLugarTrabajoDao;
    private ProductoDao productoDao;
    private ProvinciaDao provinciaDao;
    private ReporteGastosDao reporteGastosDao;
    private RutaDao rutaDao;
    private TrayectoriaDao trayectoriaDao;
    private VentaAreaDao ventaAreaDao;
    private VentaAreaFechaDao ventaAreaFechaDao;
    private VisitaDao visitaDao;
    private VisitadorDao visitadorDao;
    private VisitadorAreaHospitalariaDao visitadorAreaHospitalariaDao;
    private VisitaMaterialDao visitaMaterialDao;
    private VisitaProductoDao visitaProductoDao;


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
        TableUtils.createTable(connectionSource, Cita.class);
        TableUtils.createTable(connectionSource, CitaVisita.class);
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
        TableUtils.createTable(connectionSource, Ruta.class);
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
        TableUtils.dropTable(connectionSource, Cita.class, true);
        TableUtils.dropTable(connectionSource, CitaVisita.class, true);
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
        TableUtils.dropTable(connectionSource, Ruta.class, true);
        TableUtils.dropTable(connectionSource, Trayectoria.class,true);
        TableUtils.dropTable(connectionSource, VentaArea.class,true);
        TableUtils.dropTable(connectionSource, VentaAreaFecha.class,true);
        TableUtils.dropTable(connectionSource, Visita.class,true);
        TableUtils.dropTable(connectionSource, Visitador.class,true);
        TableUtils.dropTable(connectionSource, VisitaMaterial.class,true);
        TableUtils.dropTable(connectionSource, VisitaProducto.class,true);

    }

    public AmbulatorioDao getAmbulatorioDao() throws SQLException {
        if (ambulatorioDao == null) {
            ambulatorioDao = getDao(Ambulatorio.class);
        }
        return ambulatorioDao;
    }

    public AreaHospitalariaDao getAreaHospitalariaDao() throws SQLException {
        if (areaHospitalariaDao == null) {
            areaHospitalariaDao = getDao(AreaHospitalaria.class);
        }
        return areaHospitalariaDao;
    }

    public CentroMedicoDao getCentroMedicoDao() throws SQLException {
        if (centroMedicoDao == null) {
            centroMedicoDao = getDao(CentroMedico.class);
        }
        return centroMedicoDao;
    }

    public CitaDao getCitaDao() throws SQLException {
        if (citaDao == null) {
            citaDao = getDao(Cita.class);
        }
        return citaDao;
    }

    public CitaVisitaDao getCitaVisitaDao() throws SQLException {
        if (citaVisitaDao == null) {
            citaVisitaDao = getDao(CitaVisita.class);
        }
        return citaVisitaDao;
    }

    public ClinicaPrivadaDao getClinicaPrivadaDao() throws SQLException {
        if (clinicaPrivadaDao == null) {
            clinicaPrivadaDao = getDao(ClinicaPrivada.class);
        }
        return clinicaPrivadaDao;
    }

    public DiaVisitableDao getDiaVisitableDao() throws SQLException {
        if (diaVisitableDao == null) {
            diaVisitableDao = getDao(DiaVisitable.class);
        }
        return diaVisitableDao;
    }

    public EspecialidadMedicaDao getEspecialidadMedicaDao() throws SQLException {
        if (especialidadMedicaDao == null) {
            especialidadMedicaDao = getDao(EspecialidadMedica.class);
        }
        return especialidadMedicaDao;
    }

    public GastoDao getGastoDao() throws SQLException {
        if (gastoDao == null) {
            gastoDao = getDao(Gasto.class);
        }
        return gastoDao;
    }

    public HospitalDao getHospitalDao() throws SQLException {
        if (hospitalDao == null) {
            hospitalDao = getDao(Hospital.class);
        }
        return hospitalDao;
    }

    public MaterialPromocionalDao getMaterialPromocionalDao() throws SQLException {
        if (materialPromocionalDao == null) {
            materialPromocionalDao = getDao(MaterialPromocional.class);
        }
        return materialPromocionalDao;
    }

    public MedicoDao getMedicoDao() throws SQLException {
        if (medicoDao == null) {
            medicoDao = getDao(Medico.class);
        }
        return medicoDao;
    }

    public MedicoLugarTrabajoDao getMedicoLugarTrabajoDao() throws SQLException {
        if (medicoLugarTrabajoDao == null) {
            medicoLugarTrabajoDao = getDao(MedicoLugarTrabajo.class);
        }
        return medicoLugarTrabajoDao;
    }

    public ProductoDao getProductoDao() throws SQLException {
        if (productoDao == null) {
            productoDao = getDao(Producto.class);
        }
        return productoDao;
    }

    public ProvinciaDao getProvinciaDao() throws SQLException {
        if (provinciaDao == null) {
            provinciaDao = getDao(Provincia.class);
        }
        return provinciaDao;
    }

    public ReporteGastosDao getReporteGastosDao() throws SQLException {
        if (reporteGastosDao == null) {
            reporteGastosDao = getDao(ReporteGastos.class);
        }
        return reporteGastosDao;
    }

    public RutaDao getRutaDao() throws SQLException {
        if (rutaDao == null) {
            rutaDao = getDao(Ruta.class);
        }
        return rutaDao;
    }

    public TrayectoriaDao getTrayectoriaDao() throws SQLException {
        if (trayectoriaDao == null) {
            trayectoriaDao = getDao(Trayectoria.class);
        }
        return trayectoriaDao;
    }

    public VentaAreaDao getVentaAreaDao() throws SQLException {
        if (ventaAreaDao == null) {
            ventaAreaDao = getDao(VentaArea.class);
        }
        return ventaAreaDao;
    }

    public VentaAreaFechaDao getVentaAreaFechaDao() throws SQLException {
        if (ventaAreaFechaDao == null) {
            ventaAreaFechaDao = getDao(VentaAreaFecha.class);
        }
        return ventaAreaFechaDao;
    }

    public VisitaDao getVisitaDao() throws SQLException {
        if (visitaDao == null) {
            visitaDao = getDao(Visita.class);
        }
        return visitaDao;
    }

    public VisitadorDao getVisitadorDao() throws SQLException {
        if (visitadorDao == null) {
            visitadorDao = getDao(Visitador.class);
        }
        return visitadorDao;
    }

    public VisitadorAreaHospitalariaDao getVisitadorAreaHospitalariaDao() throws SQLException
    {
        if(visitadorAreaHospitalariaDao == null)
        {
            visitadorAreaHospitalariaDao = getDao(VisitadorAreaHospitalaria.class);
        }
        return visitadorAreaHospitalariaDao;
    }

    public VisitaMaterialDao getVisitaMaterialDao() throws SQLException {
        if (visitaMaterialDao == null) {
            visitaMaterialDao = getDao(VisitaMaterial.class);
        }
        return visitaMaterialDao;
    }

    public VisitaProductoDao getVisitaProductoDao() throws SQLException {
        if (visitaProductoDao == null) {
            visitaProductoDao = getDao(VisitaProducto.class);
        }
        return visitaProductoDao;
    }

    //Insertar datos en la bd
    private void inicializarDatos() throws SQLException
    {
        //Crear visitador
        VisitadorDao visitadorDao = getVisitadorDao();
        Visitador visitador = new Visitador(1,"pass","Elvis","Sitante","613199111","elvis@sitante.es");
        visitadorDao.create(visitador);

        //Hospitales
        HospitalDao hospitalDao = getHospitalDao();
        Hospital hospital = new Hospital("Hospital General de Alicante", "Calle del Hospital, 5");
        hospitalDao.create(hospital);

        //Ambulatorios
        AmbulatorioDao ambulatorioDao = getAmbulatorioDao();
        Ambulatorio ambulatorio = new Ambulatorio("Ambulatorio de Babel","Calle Boyero, 12");
        ambulatorioDao.create(ambulatorio);

        //Clinicas privadas
        ClinicaPrivadaDao clinicaPrivadaDao = getClinicaPrivadaDao();
        ClinicaPrivada clinicaPrivada = new ClinicaPrivada("Clínica Vistahermosa", "Carretera de por Ahí, 111");
        clinicaPrivadaDao.create(clinicaPrivada);

        //Provincias
        ProvinciaDao provDao = getProvinciaDao();
        Provincia prov = new Provincia(1,"Alicante");
        provDao.create(prov);

        //Crear area hospitalaria
        AreaHospitalariaDao areaDao = getAreaHospitalariaDao();
        AreaHospitalaria area = new AreaHospitalaria(3009,hospital,prov);
        areaDao.create(area);

        //Vincular centros médicos con el área
        hospital.setAreaHospitalaria(area);
        hospitalDao.update(hospital);
        ambulatorio.setAreaHospitalaria(area);
        ambulatorioDao.update(ambulatorio);
        clinicaPrivada.setAreaHospitalaria(area);
        clinicaPrivadaDao.update(clinicaPrivada);

        //Productos
        ProductoDao productoDao = getProductoDao();
        int prod1 = 69;
        Producto prod = new Producto(prod1,"Viagra","Ideal para venirse arriba");
        productoDao.create(prod);

        int prod2 = 112;
        prod.setCodNacional(prod2);
        prod.setNombre("Vaginesil");
        prod.setDescripcion("Por si te pica... ya sabes... ahí");
        productoDao.create(prod);

        //Material promocional
        MaterialPromocionalDao materialDao = getMaterialPromocionalDao();
        prod.setCodNacional(prod1);
        MaterialPromocional material = new MaterialPromocional("Boli bic", TipoMaterial.BOLIGRAFO, prod);
        materialDao.create(material);

        material.setNombre("Folleto de mano");
        material.setTipoMaterial(TipoMaterial.CATALOGO);
        materialDao.create(material);

        //Crear médicos
        MedicoDao medicoDao = getMedicoDao();
        Medico medico1 = new Medico(32223,"Francisco", "Matesanz", true, TipoCliente.A, prov,prov);
        medicoDao.create(medico1);
        int med1 = medico1.getId();

        Medico medico2 = new Medico(10111, "Agapito", "Di Sousa", true, TipoCliente.B, prov,prov);
        medicoDao.create(medico2);
        int med2 = medico2.getId();

        //Crear vinculación entre médico y lugar de trabajo
        MedicoLugarTrabajoDao medicoLugarTrabajoDao = getMedicoLugarTrabajoDao();
        MedicoLugarTrabajo medicoLugarTrabajo = new MedicoLugarTrabajo(medico1,hospital);
        medicoLugarTrabajoDao.create(medicoLugarTrabajo);


        MedicoLugarTrabajo medicoLugarTrabajo2 = new MedicoLugarTrabajo(medico2,ambulatorio);
        medicoLugarTrabajoDao.create(medicoLugarTrabajo2);




        //Crear visitas
        VisitaDao visitaDao = getVisitaDao();
        Visita visita = new Visita(new Date(),new Date(), 5, LugarVisita.HOSPITAL, true, visitador, medico2);
        visitaDao.create(visita);


        //Crear rutas
        RutaDao rutaDao = getRutaDao();
        Date fecha1 = new Date();
        Ruta ruta1 = new Ruta(fecha1, true, visitador);
        rutaDao.create(ruta1);


        Calendar c1 = GregorianCalendar.getInstance();
        c1.set(2010, Calendar.JANUARY, 14);  //January 30th 2000
        Date fecha2 = c1.getTime();
        Ruta ruta2 = new Ruta(fecha2, true, visitador);
        rutaDao.create(ruta2);


        //Crear cita
        CitaDao citaDao = getCitaDao();
        Cita cita1 = new Cita("Hospital de Alicante", LugarVisita.HOSPITAL, "Av. Pintor Baeza, 12, Alicante", 10, 25, 11, 05, medico1,ruta1);
        citaDao.create(cita1);


        Cita cita2 = new Cita("Hospital de San Juan", LugarVisita.HOSPITAL, "Ctra. Nnal. 332 Alacant-Valencia, s/n, 03550 San Juan de Alicante", 12, 45, 13, 20, medico2,ruta1);
        citaDao.create(cita2);


        //Crear reporte de gastos
        ReporteGastosDao reporteGastosDao = getReporteGastosDao();
        ReporteGastos reporte = new ReporteGastos(new Date(),false,visitador);
        reporteGastosDao.create(reporte);
        int reporte1 = reporte.getId();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        reporte.setFecha(date);
        reporteGastosDao.create(reporte);
        int reporte2 = reporte.getId();

        //Crear gastos
        GastoDao gastoDao = getGastoDao();
        Gasto gasto = new Gasto(33.9f, ConceptoGasto.COMIDA,reporte);
        gastoDao.create(gasto);

        //Crear trayectoria
        TrayectoriaDao trayectoriaDao = getTrayectoriaDao();
        Calendar c2 = GregorianCalendar.getInstance();
        c1.set(2010, Calendar.JANUARY, 14);  //January 30th 2000
        Date fecha3 = c2.getTime();
        Trayectoria trayect = new Trayectoria(fecha3,fecha3,medico2,prov);
        trayectoriaDao.create(trayect);

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
        ambulatorioDao = null;
        areaHospitalariaDao = null;
        centroMedicoDao = null;
        citaDao = null;
        citaVisitaDao = null;
        clinicaPrivadaDao = null;
        diaVisitableDao = null;
        especialidadMedicaDao = null;
        gastoDao = null;
        hospitalDao = null;
        materialPromocionalDao = null;
        medicoDao = null;
        medicoLugarTrabajoDao = null;
        productoDao = null;
        provinciaDao = null;
        reporteGastosDao = null;
        rutaDao = null;
        trayectoriaDao = null;
        ventaAreaDao = null;
        ventaAreaFechaDao = null;
        visitaDao = null;
        visitadorDao = null;
        visitadorAreaHospitalariaDao = null;
        visitaMaterialDao = null;
        visitaProductoDao = null;
    }

}
