package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by felix on 28/04/15.
 */


public class MedicoLugarTrabajo
{
    //TABLA
    public static final String TABLE = "medico_lugar_trabajo";

    //Campos
    public static final String ID = "_id";

    //Relaciones
    public static final String MEDICO = "fk_medico";
    public static final String CENTROMEDICO = "fk_centro_medico";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    //Relaciones
    @DatabaseField(columnName = MEDICO, foreign = true, useGetSet = true, canBeNull = false)
    private Medico medico;

    @DatabaseField(columnName = CENTROMEDICO, foreign = true, useGetSet = true, canBeNull = false)
    private CentroMedico centroMedico;

    @ForeignCollectionField(eager = false, foreignFieldName = DiaVisitable.MEDICOLUGARTRABAJO_CAMPO)
    private ForeignCollection<DiaVisitable> diasVisitables;

    public MedicoLugarTrabajo() {
    }

    public MedicoLugarTrabajo(Medico medico, CentroMedico centroMedico) {
        this.medico = medico;
        this.centroMedico = centroMedico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Relaciones

    public ForeignCollection<DiaVisitable> getDiasVisitables() {
        return diasVisitables;
    }

    public void setDiasVisitables(ForeignCollection<DiaVisitable> diasVisitables) {
        this.diasVisitables = diasVisitables;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public CentroMedico getCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(CentroMedico centroMedico) {
        this.centroMedico = centroMedico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicoLugarTrabajo)) return false;

        MedicoLugarTrabajo that = (MedicoLugarTrabajo) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
