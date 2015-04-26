package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 25/04/15.
 */
@DatabaseTable(tableName = Hospital.TABLE)
public class Hospital extends CentroMedico
{
    //Tabla
    public static final String TABLE = "hospital";

    public Hospital() {
    }
}
