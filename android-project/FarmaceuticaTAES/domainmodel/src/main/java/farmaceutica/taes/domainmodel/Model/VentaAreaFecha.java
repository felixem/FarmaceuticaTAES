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

    //Relaciones
    public static final String VENTAAREA = "fk_venta_area";

    //Campos relacionados
    public static final String VENTAAREA_CAMPO= "ventaArea";

    //Propiedades
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHA, useGetSet = true, canBeNull = false)
    private Date fecha;

    @DatabaseField(columnName = CANTIDAD, useGetSet = true, canBeNull = false)
    private int cantidad;

    //Relaciones
    private VentaArea ventaArea;

    public VentaAreaFecha() {
    }

    public VentaAreaFecha(Date fecha, int cantidad, VentaArea ventaArea) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.ventaArea = ventaArea;
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

    //Relaciones


    public VentaArea getVentaArea() {
        return ventaArea;
    }

    public void setVentaArea(VentaArea ventaArea) {
        this.ventaArea = ventaArea;
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
