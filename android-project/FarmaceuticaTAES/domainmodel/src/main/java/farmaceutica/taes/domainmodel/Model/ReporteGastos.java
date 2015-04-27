package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by felix on 26/04/15.
 */
@DatabaseTable(tableName = ReporteGastos.TABLE)
public class ReporteGastos
{
    //Tabla
    public static final String TABLE = "reporte_gastos";

    //Campos
    public static final String ID="_id";
    public static final String FECHA="fecha";
    public static final String ENVIADO="enviado";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHA, useGetSet = true, canBeNull = false)
    private Date fecha;

    @DatabaseField(columnName = ENVIADO, useGetSet = true, canBeNull = false)
    private boolean enviado;

    public ReporteGastos() {
    }

    public ReporteGastos(Date fecha, boolean enviado) {
        this.fecha = fecha;
        this.enviado = enviado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
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
