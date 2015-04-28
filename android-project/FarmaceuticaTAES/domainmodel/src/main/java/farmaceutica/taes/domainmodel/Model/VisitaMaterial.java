package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.VisitaMaterialDao;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = VisitaMaterial.TABLE, daoClass = VisitaMaterialDao.class)
public class VisitaMaterial {

    //Tablas
    public static final String TABLE = "visita_material";

    //Campos
    public static final String ID = "_id";
    public static final String CANTIDAD = "cantidad";

    //Relaciones
    public static final String VISITA ="fk_visita";
    public static final String MATERIALPROMOCIONAL ="fk_material";


    //Atributos
    @DatabaseField (columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField (columnName = CANTIDAD, useGetSet = true, canBeNull = false)
    private int cantidad;

    //Relaciones
    @DatabaseField(columnName = VISITA, foreign = true, useGetSet = true, canBeNull = false)
    private Visita visita;

    @DatabaseField(columnName = MATERIALPROMOCIONAL, foreign = true, useGetSet = true, canBeNull = false)
    private MaterialPromocional materialPromocional;

    public VisitaMaterial() {
    }

    public VisitaMaterial(int cantidad, Visita visita, MaterialPromocional materialPromocional) {
        this.cantidad = cantidad;
        this.visita = visita;
        this.materialPromocional = materialPromocional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public MaterialPromocional getMaterialPromocional() {
        return materialPromocional;
    }

    public void setMaterialPromocional(MaterialPromocional materialPromocional) {
        this.materialPromocional = materialPromocional;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaMaterial)) return false;

        VisitaMaterial that = (VisitaMaterial) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
