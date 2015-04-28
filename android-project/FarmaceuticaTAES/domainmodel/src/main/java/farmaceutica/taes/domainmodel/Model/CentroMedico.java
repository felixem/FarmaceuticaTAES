package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.CentroMedicoDao;

/**
 * Created by felix on 25/04/15.
 */

@DatabaseTable(tableName = CentroMedico.TABLE, daoClass = CentroMedicoDao.class)
public class CentroMedico
{
    //Tabla
    public static final String TABLE = "centro_medico";

    //Campos
    public static final String ID ="_id";

    //Relaciones
    public static final String AREAHOSPITALARIA = "fk_area_hospitalaria";

    //Campos relacionados
    public static final String AREAHOSPITALARIA_CAMPO = "areaHospitalaria";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    //Relaciones
    @DatabaseField(columnName = AREAHOSPITALARIA, foreign = true, useGetSet = true)
    private AreaHospitalaria areaHospitalaria;

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

    //Relaciones

    public AreaHospitalaria getAreaHospitalaria() {
        return areaHospitalaria;
    }

    public void setAreaHospitalaria(AreaHospitalaria areaHospitalaria) {
        this.areaHospitalaria = areaHospitalaria;
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
