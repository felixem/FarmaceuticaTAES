package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = Trayectoria.TABLE)
public class Trayectoria
{
    //TABLA
    public static final String TABLE = "trayectoria";

    //CAMPOS
    public static final String ID = "_id";
    public static final String FECHAINICIO = "fecha_inicio";
    public static final String FECHAFIN = "fecha_fin";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHAINICIO, useGetSet = true, canBeNull = false)
    private Date fechaInicio;

    @DatabaseField(columnName = FECHAFIN, useGetSet = true, canBeNull = false)
    private Date fechaFin;

    public Trayectoria() {
    }

    public Trayectoria(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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
