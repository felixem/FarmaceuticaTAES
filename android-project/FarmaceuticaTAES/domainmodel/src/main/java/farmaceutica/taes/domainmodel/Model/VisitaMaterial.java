package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = VisitaMaterial.TABLE)
public class VisitaMaterial {

    //Tablas
    public static final String TABLE = "visita_material";

    //Campos
    public static final String ID = "_id";
    public static final String CANTIDAD = "cantidad";

    //Atributos
    @DatabaseField (columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField (columnName = CANTIDAD, useGetSet = true, canBeNull = false)
    private int cantidad;

    public VisitaMaterial() {
    }

    public VisitaMaterial(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(o instanceof VisitaMaterial)) return false;

        VisitaMaterial that = (VisitaMaterial) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
