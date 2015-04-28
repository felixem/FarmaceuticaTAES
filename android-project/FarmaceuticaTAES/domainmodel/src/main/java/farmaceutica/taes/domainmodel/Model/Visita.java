package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by felix on 26/04/15.
 */
@DatabaseTable(tableName = Visita.TABLE)
public class Visita {
    //Tabla
    public static final String TABLE = "visita";

    //Campos
    public static final String ID ="_id";
    public static final String FECHAVISITA = "fecha_visita";
    public static final String FECHAREPORTE = "fecha_reporte";
    public static final String OBSERVACIONES = "observaciones";
    public static final String MINUTOS = "minutos";
    public static final String LUGARVISITA = "lugar_visita";
    public static final String ACOMPANYADO = "acompanyado";

    //Relaciones
    public static final String VISITADOR = "fk_visitador";
    public static final String MEDICO = "fk_medico";

    //Campos relacionados
    public static final String VISITADOR_CAMPO = "visitador";
    public static final String MEDICO_CAMPO = "medico";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHAVISITA, useGetSet = true, canBeNull = false)
    private Date fechaVisita;

    @DatabaseField(columnName = FECHAREPORTE, useGetSet = true, canBeNull = false)
    private Date fechaReporte;

    @DatabaseField(columnName = OBSERVACIONES, useGetSet = true)
    private String observaciones;

    @DatabaseField(columnName = MINUTOS, useGetSet = true, canBeNull = false)
    private Integer minutos;

    @DatabaseField(columnName = LUGARVISITA, useGetSet = true, canBeNull = false)
    private LugarVisita lugarVisita;

    @DatabaseField(columnName = ACOMPANYADO, useGetSet = true, canBeNull = false)
    private boolean acompanyado;

    //Relaciones

    @DatabaseField(columnName = VISITADOR, foreign = true, useGetSet = true ,canBeNull = false)
    private Visitador visitador;

    @DatabaseField(columnName = MEDICO, foreign = true, useGetSet = true, canBeNull = false)
    private Medico medico;


    public Visita() {
    }

    public Visita(Date fechaVisita, Date fechaReporte, Integer minutos, LugarVisita lugarVisita, boolean acompanyado, Visitador visitador, Medico medico) {
        this.fechaVisita = fechaVisita;
        this.fechaReporte = fechaReporte;
        this.minutos = minutos;
        this.lugarVisita = lugarVisita;
        this.acompanyado = acompanyado;
        this.visitador = visitador;
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public LugarVisita getLugarVisita() {
        return lugarVisita;
    }

    public void setLugarVisita(LugarVisita lugarVisita) {
        this.lugarVisita = lugarVisita;
    }

    public boolean getAcompanyado() {
        return acompanyado;
    }

    public void setAcompanyado(boolean acompanyado) {
        this.acompanyado = acompanyado;
    }

    //Relaciones
    public Visitador getVisitador() {
        return visitador;
    }

    public void setVisitador(Visitador visitador) {
        this.visitador = visitador;
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
        if (!(o instanceof Visita)) return false;

        Visita visita = (Visita) o;

        if (id != visita.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
