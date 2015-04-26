package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 25/04/15.
 */

@DatabaseTable(tableName = CentroMedico.TABLE)
public class CentroMedico
{
    //Tabla
    public static final String TABLE = "centro_medico";

    //Campos
    public static final String ID ="_id";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    //Constructor sin parámetros para ORMLITE
    public CentroMedico()
    {

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
        if (!(o instanceof CentroMedico)) return false;

        CentroMedico that = (CentroMedico) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
