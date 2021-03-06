package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.DiaVisitableDao;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = DiaVisitable.TABLE, daoClass = DiaVisitableDao.class)
public class DiaVisitable {
    //Tabla
    public static final String TABLE = "dia_visitable";

    //Campos
    public static final String ID = "_id";
    public static final String DIA = "dia";
    public static final String HORAINICIO = "hora_inicio";
    public static final String HORAFIN = "hora_fin";

    //Relaciones
    public static final String MEDICOLUGARTRABAJO ="fk_medico_lugar_trabajo";

    //Campos relacionados
    public static final String MEDICOLUGARTRABAJO_CAMPO= "medicoLugarTrabajo";

    //Atributos
    @DatabaseField(columnName = ID, useGetSet = true, generatedId = true)
    private int id;

    @DatabaseField(columnName = DIA, useGetSet = true, canBeNull = false)
    private Dia dia;

    @DatabaseField(columnName = HORAINICIO, useGetSet = true, canBeNull = false)
    private String horaInicio;

    @DatabaseField(columnName = HORAFIN, useGetSet = true, canBeNull = false)
    private String horaFin;

    //Relaciones
    @DatabaseField(columnName = MEDICOLUGARTRABAJO, foreign = true, canBeNull = false, useGetSet = true)
    private MedicoLugarTrabajo medicoLugarTrabajo;

    public DiaVisitable() {
    }

    public DiaVisitable(Dia dia, String horaInicio, String horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    //Relaciones


    public MedicoLugarTrabajo getMedicoLugarTrabajo() {
        return medicoLugarTrabajo;
    }

    public void setMedicoLugarTrabajo(MedicoLugarTrabajo medicoLugarTrabajo) {
        this.medicoLugarTrabajo = medicoLugarTrabajo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiaVisitable)) return false;

        DiaVisitable that = (DiaVisitable) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
