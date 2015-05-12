package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.CitaVisitaDao;

/**
 * Created by felix on 8/05/15.
 */

@DatabaseTable(tableName = CitaVisita.TABLE, daoClass = CitaVisitaDao.class)
public class CitaVisita
{
    //TABLA
    public static final String TABLE = "cita_visita";

    //Campos
    public static final String ID = "_id";

    //Relaciones
    public static final String CITA = "fk_cita";
    public static final String VISITA = "fk_visita";

    //Campos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    //Relaciones
    @DatabaseField(columnName = CITA, foreign = true, useGetSet = true, canBeNull = false, unique = true)
    private Cita cita;
    @DatabaseField(columnName = VISITA, foreign = true, useGetSet = true, canBeNull = false, unique = true)
    private Visita visita;

    public CitaVisita() {
    }

    public CitaVisita(Cita cita, Visita visita) {
        this.cita = cita;
        this.visita = visita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitaVisita)) return false;

        CitaVisita that = (CitaVisita) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
