package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 25/04/15.
 */

@DatabaseTable(tableName = Ambulatorio.TABLE)
public class Ambulatorio extends CentroMedico
{
    //Tabla
    public static final String TABLE = "ambulatorio";

    public Ambulatorio() {
    }
}
