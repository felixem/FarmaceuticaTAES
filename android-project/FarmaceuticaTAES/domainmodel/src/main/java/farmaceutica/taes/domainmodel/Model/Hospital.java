package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.HospitalDao;

/**
 * Created by felix on 25/04/15.
 */
@DatabaseTable(tableName = Hospital.TABLE, daoClass = HospitalDao.class)
public class Hospital extends CentroMedico
{
    //Tabla
    public static final String TABLE = "hospital";

    public Hospital() {
    }

    public Hospital(String nombre, String direccion) {
        super(nombre, direccion);
    }
}
