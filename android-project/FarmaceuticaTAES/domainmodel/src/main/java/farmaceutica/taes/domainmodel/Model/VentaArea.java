package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
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

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    public VentaArea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
