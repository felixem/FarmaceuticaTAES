package farmaceutica.taes.domainmodel.Model;

import android.hardware.Camera;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = VentaArea.TABLE)
public class VentaArea
{
    //TABLA
    public static final String TABLE = "venta_area";

    //Campos
    public static final String ID = "_id";

    //Relaciones
    public static final String AREAHOSPITALARIA = "fk_area_hospitalaria";
    public static final String PRODUCTO = "fk_producto";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    //Relaciones
    @DatabaseField(columnName = AREAHOSPITALARIA, foreign = true, useGetSet = true, canBeNull = false)
    private AreaHospitalaria areaHospitalaria;

    @DatabaseField(columnName = PRODUCTO, foreign = true, useGetSet = true, canBeNull = false)
    private Producto producto;

    @ForeignCollectionField(eager = false, foreignFieldName = VentaAreaFecha.VENTAAREA_CAMPO)
    private ForeignCollection<VentaAreaFecha> ventasAreaFecha;

    public VentaArea() {
    }

    public VentaArea(AreaHospitalaria areaHospitalaria, Producto producto) {
        this.areaHospitalaria = areaHospitalaria;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AreaHospitalaria getAreaHospitalaria() {
        return areaHospitalaria;
    }

    public void setAreaHospitalaria(AreaHospitalaria areaHospitalaria) {
        this.areaHospitalaria = areaHospitalaria;
    }

    //RElaciones

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ForeignCollection<VentaAreaFecha> getVentasAreaFecha() {
        return ventasAreaFecha;
    }

    public void setVentasAreaFecha(ForeignCollection<VentaAreaFecha> ventasAreaFecha) {
        this.ventasAreaFecha = ventasAreaFecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VentaArea)) return false;

        VentaArea ventaArea = (VentaArea) o;

        if (id != ventaArea.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
