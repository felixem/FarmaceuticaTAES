package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.EspecialidadMedicaDao;

/**
 * Created by felix on 25/04/15.
 */

@DatabaseTable(tableName = EspecialidadMedica.TABLE, daoClass = EspecialidadMedicaDao.class)
public class EspecialidadMedica {
    //Tabla
    public static final String TABLE = "especialidad_medica";

    //Campos
    public static final String ID = "_id";
    public static final String NOMBRE = "nombre";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = NOMBRE, unique = true, useGetSet = true, canBeNull = false)
    private String nombre;

    @ForeignCollectionField(eager = false, foreignFieldName = Medico.ESPECIALIDAD)
    private ForeignCollection<Medico> medicos;

    public EspecialidadMedica() {
    }

    public EspecialidadMedica(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EspecialidadMedica)) return false;

        EspecialidadMedica that = (EspecialidadMedica) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
