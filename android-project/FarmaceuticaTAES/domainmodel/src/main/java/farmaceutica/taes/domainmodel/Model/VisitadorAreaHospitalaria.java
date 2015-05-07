package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 7/05/15.
 */

//Clase que representa la relacion entre un visitador y un area hospitalaria

@DatabaseTable(tableName = VisitadorAreaHospitalaria.TABLE)
public class VisitadorAreaHospitalaria
{
    //Tabla
    public final static String TABLE = "visitador_area";

    //Relaciones
    public final static String VISITADOR = "fk_visitador";
    public final static String AREAHOSPITALARIA = "fk_area";

    //Atributos
    @DatabaseField(columnName = VISITADOR, foreign = true, useGetSet = true,  unique = true, canBeNull = false)
    private Visitador visitador;

    @DatabaseField(columnName = AREAHOSPITALARIA, foreign = true, useGetSet = true, unique = true, canBeNull = false)
    private AreaHospitalaria area;

    public VisitadorAreaHospitalaria() {
    }

    public VisitadorAreaHospitalaria(Visitador visitador, AreaHospitalaria area) {
        this.visitador = visitador;
        this.area = area;
    }

    //Getters y Setters

    public Visitador getVisitador() {
        return visitador;
    }

    public void setVisitador(Visitador visitador) {
        this.visitador = visitador;
    }

    public AreaHospitalaria getArea() {
        return area;
    }

    public void setArea(AreaHospitalaria area) {
        this.area = area;
    }




}
