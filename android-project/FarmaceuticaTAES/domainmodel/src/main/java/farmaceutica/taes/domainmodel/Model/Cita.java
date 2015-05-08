package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.CitaDao;

/**
 * Created by felix on 8/05/15.
 */

@DatabaseTable (tableName = Cita.TABLE, daoClass = CitaDao.class)
public class Cita {
    //TABLA
    public static final String TABLE = "cita";

    //Campos
    public static final String ID = "_id";
    public static final String LUGAR = "lugar";
    public static final String DIRECCION ="direccion";
    public static final String TIPO_LUGAR="tipo_lugar";
    public static final String COMENTARIO="comentario";
    public static final String HORAINICIO="hora_inicio";
    public static final String MINUTOINICIO="minuto_inicio";
    public static final String HORAFIN="hora_fin";
    public static final String MINUTOFIN="minuto_fin";

    //Relaciones
    public static final String MEDICO = "fk_medico";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = LUGAR, useGetSet = true, canBeNull = false)
    private String lugar;

    @DatabaseField(columnName = DIRECCION, useGetSet = true, canBeNull = false)
    private String direccion;

    @DatabaseField(columnName = TIPO_LUGAR, useGetSet = true, canBeNull = false)
    private LugarVisita tipoLugar;

    @DatabaseField(columnName = COMENTARIO, useGetSet = true)
    private String comentario;

    @DatabaseField(columnName = HORAINICIO, useGetSet = true, canBeNull = false)
    private Integer horaInicio;
    @DatabaseField(columnName = MINUTOINICIO, useGetSet = true, canBeNull = false)
    private Integer minutoInicio;

    @DatabaseField(columnName = HORAFIN, useGetSet = true, canBeNull = false)
    private Integer horaFin;

    @DatabaseField(columnName = MINUTOFIN, useGetSet = true, canBeNull = false)
    private Integer minutoFin;

    //Relaciones
    @DatabaseField(columnName = MEDICO, foreign = true, useGetSet = true, canBeNull = false)
    private Medico medico;

    public Cita() {
    }

    public Cita(String lugar, LugarVisita tipoLugar, String direccion, Integer horaInicio, Integer minutoInicio, Integer horaFin, Integer minutoFin, Medico medico) {
        this.lugar = lugar;
        this.tipoLugar = tipoLugar;
        this.direccion = direccion;
        this.horaInicio = horaInicio;
        this.minutoInicio = minutoInicio;
        this.horaFin = horaFin;
        this.minutoFin = minutoFin;
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LugarVisita getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(LugarVisita tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(Integer minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getMinutoFin() {
        return minutoFin;
    }

    public void setMinutoFin(Integer minutoFin) {
        this.minutoFin = minutoFin;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cita)) return false;

        Cita cita = (Cita) o;

        if (id != cita.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
