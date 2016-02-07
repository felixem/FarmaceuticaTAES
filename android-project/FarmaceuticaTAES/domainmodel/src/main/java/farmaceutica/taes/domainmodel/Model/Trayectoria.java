package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import farmaceutica.taes.domainmodel.Data.Dao.TrayectoriaDao;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = Trayectoria.TABLE, daoClass = TrayectoriaDao.class)
public class Trayectoria
{
    //TABLA
    public static final String TABLE = "trayectoria";

    //CAMPOS
    public static final String ID = "_id";
    public static final String FECHAINICIO = "fecha_inicio";
    public static final String FECHAFIN = "fecha_fin";

    //Relaciones
    public static final String MEDICO ="fk_medico";
    public static final String PROVINCIA="fk_provincia";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHAINICIO, useGetSet = true, canBeNull = false)
    private Date fechaInicio;

    @DatabaseField(columnName = FECHAFIN, useGetSet = true, canBeNull = false)
    private Date fechaFin;

    //Relaciones
    @DatabaseField(columnName = MEDICO, foreign = true, useGetSet = true, canBeNull = false)
    private Medico medico;

    @DatabaseField(columnName = PROVINCIA, foreign = true, useGetSet = true, canBeNull = false)
    private Provincia provincia;


    public Trayectoria() {
    }

    public Trayectoria(Date fechaInicio, Date fechaFin, Medico medico, Provincia provincia) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.medico = medico;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    //Relaciones

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trayectoria)) return false;

        Trayectoria that = (Trayectoria) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
