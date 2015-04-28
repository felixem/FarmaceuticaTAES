package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
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

    //Relaciones
    public static final String HOSPITAL = "fk_hospital";
    public static final String PROVINCIA = "fk_provincia";

    //Campos relacionados
    public static final String PROVINCIA_CAMPO = "provincia";

    //Atributos
    @DatabaseField(columnName = ID, id = true, useGetSet = true)
    private int codPostal;

    //Relaciones
    @ForeignCollectionField(eager = false, foreignFieldName = CentroMedico.AREAHOSPITALARIA_CAMPO)
    private ForeignCollection<CentroMedico> centrosMedicos;

    @DatabaseField(columnName = HOSPITAL, foreign = true, useGetSet = true, canBeNull = false)
    private Hospital hospital;

    @DatabaseField(columnName = PROVINCIA, foreign = true, useGetSet = true, canBeNull = false)
    private Provincia provincia;

    public AreaHospitalaria() {
    }

    public AreaHospitalaria(int codPostal, Hospital hospital, Provincia provincia) {
        this.codPostal = codPostal;
        this.hospital = hospital;
        this.provincia = provincia;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }


    //Relaciones

    public ForeignCollection<CentroMedico> getCentrosMedicos() {
        return centrosMedicos;
    }

    public void setCentrosMedicos(ForeignCollection<CentroMedico> centrosMedicos) {
        this.centrosMedicos = centrosMedicos;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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
