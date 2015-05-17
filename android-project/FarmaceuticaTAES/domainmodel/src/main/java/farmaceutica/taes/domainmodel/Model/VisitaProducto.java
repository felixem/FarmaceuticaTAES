package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.VisitaProductoDao;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = VisitaProducto.TABLE, daoClass = VisitaProductoDao.class)
public class VisitaProducto {

    //Tablas
    public static final String TABLE = "visita_producto";

    //Campos
    public static final String ID = "_id";
    public static final String ORDEN = "orden";
    public static final String VALORACION = "valoracion";

    //Campos relacionbados
    public static final String VISITA_CAMPO="visita";
    public static final String PRODUCTO_CAMPO="producto";

    //Relaciones
    public static final String VISITA ="fk_visita";
    public static final String PRODUCTO = "fk_producto";

    //Atributos
    @DatabaseField (columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField (columnName = ORDEN, useGetSet = true, canBeNull = false)
    private int orden;

    @DatabaseField(columnName = VALORACION, useGetSet = true, canBeNull = false)
    private ValoracionProducto valoracion;

    //Relaciones
    @DatabaseField(columnName = VISITA, foreign = true, useGetSet = true, canBeNull = false, uniqueCombo = true)
    private Visita visita;

    @DatabaseField(columnName = PRODUCTO, foreign = true, useGetSet = true, canBeNull = false, uniqueCombo = true)
    private Producto producto;

    public VisitaProducto() {
    }

    public VisitaProducto(int orden, ValoracionProducto valoracion, Visita visita, Producto producto) {
        this.orden = orden;
        this.valoracion = valoracion;
        this.visita = visita;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public ValoracionProducto getValoracion() {
        return valoracion;
    }

    public void setValoracion(ValoracionProducto valoracion) {
        this.valoracion = valoracion;
    }

    //Relaciones


    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaProducto)) return false;

        VisitaProducto that = (VisitaProducto) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
