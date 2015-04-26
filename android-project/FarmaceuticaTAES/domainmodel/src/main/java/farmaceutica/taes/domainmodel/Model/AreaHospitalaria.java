package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 25/04/15.
 */

@DatabaseTable(tableName = AreaHospitalaria.TABLE)
public class AreaHospitalaria
{
    //Tabla
    public static final String TABLE = "area_hospitalaria";

    //Campos
    public static final String ID = "cod_postal";

    //Atributos
    @DatabaseField(columnName = ID, id = true, useGetSet = true)
    private int codPostal;

    public AreaHospitalaria() {
    }

    public AreaHospitalaria(int codPostal) {
        this.codPostal = codPostal;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaHospitalaria)) return false;

        AreaHospitalaria that = (AreaHospitalaria) o;

        if (codPostal != that.codPostal) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codPostal;
    }
}
