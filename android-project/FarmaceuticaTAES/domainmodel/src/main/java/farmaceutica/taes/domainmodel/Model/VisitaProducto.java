package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = VisitaProducto.TABLE)
public class VisitaProducto {

    //Tablas
    public static final String TABLE = "visita_producto";

    //Campos
    public static final String ID = "_id";
    public static final String ORDEN = "orden";
    public static final String VALORACION = "valoracion";

    //Atributos
    @DatabaseField (columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField (columnName = ORDEN, useGetSet = true, canBeNull = false)
    private int orden;

    @DatabaseField(columnName = VALORACION, useGetSet = true, canBeNull = false)
    private ValoracionProducto valoracion;

    public VisitaProducto() {
    }

    public VisitaProducto(int orden, ValoracionProducto valoracion) {
        this.orden = orden;
        this.valoracion = valoracion;
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
