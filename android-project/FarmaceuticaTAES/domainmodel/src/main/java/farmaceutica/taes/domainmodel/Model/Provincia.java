package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 25/04/15.
 */
@DatabaseTable(tableName = Provincia.TABLE)
public class Provincia {
    //Tabla
    public static final String TABLE = "provincia";

    //Campos
    public static final String ID = "cod_provincia";
    public static final String NOMBRE="provincia";

    //Atributos
    @DatabaseField(columnName = ID, id=true, useGetSet = true)
    private int cod;

    @DatabaseField(columnName = NOMBRE, useGetSet = true, unique = true)
    private String nombre;

    //Relaciones
    @ForeignCollectionField(eager = false, foreignFieldName = Medico.COLEGIOACTUAL_CAMPO)
    ForeignCollection<Medico> medicosColegioActual;

    @ForeignCollectionField(eager = false, foreignFieldName = Medico.PRIMERCOLEGIO_CAMPO)
    ForeignCollection<Medico> medicosPrimerColegio;

    @ForeignCollectionField(eager=false, foreignFieldName = AreaHospitalaria.PROVINCIA_CAMPO)
    ForeignCollection<AreaHospitalaria> areasHospitalarias;

    public Provincia() {
    }

    public Provincia(int cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Relaciones


    public ForeignCollection<Medico> getMedicosColegioActual() {
        return medicosColegioActual;
    }

    public void setMedicosColegioActual(ForeignCollection<Medico> medicosColegioActual) {
        this.medicosColegioActual = medicosColegioActual;
    }

    public ForeignCollection<Medico> getMedicosPrimerColegio() {
        return medicosPrimerColegio;
    }

    public void setMedicosPrimerColegio(ForeignCollection<Medico> medicosPrimerColegio) {
        this.medicosPrimerColegio = medicosPrimerColegio;
    }

    public ForeignCollection<AreaHospitalaria> getAreasHospitalarias() {
        return areasHospitalarias;
    }

    public void setAreasHospitalarias(ForeignCollection<AreaHospitalaria> areasHospitalarias) {
        this.areasHospitalarias = areasHospitalarias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provincia)) return false;

        Provincia provincia = (Provincia) o;

        if (cod != provincia.cod) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return cod;
    }
}
