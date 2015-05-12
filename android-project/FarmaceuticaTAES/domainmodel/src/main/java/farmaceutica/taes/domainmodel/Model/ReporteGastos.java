package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import farmaceutica.taes.domainmodel.Data.Dao.ReporteGastosDao;

/**
 * Created by felix on 26/04/15.
 */
@DatabaseTable(tableName = ReporteGastos.TABLE, daoClass = ReporteGastosDao.class)
public class ReporteGastos
{
    //Tabla
    public static final String TABLE = "reporte_gastos";

    //Campos
    public static final String ID="_id";
    public static final String FECHA="fecha";
    public static final String ENVIADO="enviado";

    //Relaciones
    public static final String VISITADOR="fk_visitador";

    //Campos relacionados
    public static final String VISITADOR_CAMPO="visitador";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHA, useGetSet = true, canBeNull = false)
    private Date fecha;

    @DatabaseField(columnName = ENVIADO, useGetSet = true, canBeNull = false)
    private boolean enviado;

    //Relaciones

    @DatabaseField(columnName = VISITADOR, foreign = true, useGetSet = true, canBeNull = false)
    private Visitador visitador;

    @ForeignCollectionField(eager = false , foreignFieldName = Gasto.REPORTEGASTOS_CAMPO)
    ForeignCollection<Gasto> gastos;

    public ReporteGastos() {
    }

    public ReporteGastos(Date fecha, boolean enviado, Visitador visitador) {
        this.fecha = fecha;
        this.enviado = enviado;
        this.visitador = visitador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    //Relaciones


    public Visitador getVisitador() {
        return visitador;
    }

    public void setVisitador(Visitador visitador) {
        this.visitador = visitador;
    }

    public ForeignCollection<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(ForeignCollection<Gasto> gastos) {
        this.gastos = gastos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReporteGastos)) return false;

        ReporteGastos that = (ReporteGastos) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
