package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = VentaAreaFecha.TABLE)
public class VentaAreaFecha {
    //TABLA
    public static final String TABLE = "venta_area_fecha";

    //Campos
    public static final String ID = "_id";
    public static final String FECHA = "fecha";
    public static final String CANTIDAD = "cantidad";

    //Propiedades
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHA, useGetSet = true, canBeNull = false)
    private Date fecha;

    @DatabaseField(columnName = CANTIDAD, useGetSet = true, canBeNull = false)
    private int cantidad;

    public VentaAreaFecha() {
    }

    public VentaAreaFecha(Date fecha, int cantidad) {
        this.fecha = fecha;
        this.cantidad = cantidad;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VentaAreaFecha)) return false;

        VentaAreaFecha that = (VentaAreaFecha) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
