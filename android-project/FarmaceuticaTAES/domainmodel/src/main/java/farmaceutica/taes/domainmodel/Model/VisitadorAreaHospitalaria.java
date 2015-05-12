package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.VisitadorAreaHospitalariaDao;

/**
 * Created by felix on 7/05/15.
 */

//Clase que representa la relacion entre un visitador y un area hospitalaria

@DatabaseTable(tableName = VisitadorAreaHospitalaria.TABLE, daoClass = VisitadorAreaHospitalariaDao.class)
public class VisitadorAreaHospitalaria
{
    //Tabla
    public final static String TABLE = "visitador_area";

    //Campos
    public final static String ID = "_id";

    //Relaciones
    public final static String VISITADOR = "fk_visitador";
    public final static String AREAHOSPITALARIA = "fk_area";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    //Relaciones
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitadorAreaHospitalaria)) return false;

        VisitadorAreaHospitalaria that = (VisitadorAreaHospitalaria) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
