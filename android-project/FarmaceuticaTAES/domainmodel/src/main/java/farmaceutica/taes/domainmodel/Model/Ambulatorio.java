package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.AmbulatorioDao;

/**
 * Created by felix on 25/04/15.
 */

@DatabaseTable(tableName = Ambulatorio.TABLE, daoClass = AmbulatorioDao.class)
public class Ambulatorio extends CentroMedico
{
    //Tabla
    public static final String TABLE = "ambulatorio";

    public Ambulatorio() {
    }

    public Ambulatorio(String nombre, String direccion) {
        super(nombre, direccion);
    }
}
