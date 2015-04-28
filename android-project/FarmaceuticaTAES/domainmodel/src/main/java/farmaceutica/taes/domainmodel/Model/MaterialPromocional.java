package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = MaterialPromocional.TABLE)
public class MaterialPromocional
{
    //Tabla
    public static final String TABLE = "material_promocional";

    //Campos
    public static final String ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String TIPOMATERIAL = "tipo_material";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = NOMBRE, useGetSet = true, canBeNull = false)
    private String nombre;

    @DatabaseField(columnName = DESCRIPCION, useGetSet = true)
    private String descripcion;

    @DatabaseField(columnName = TIPOMATERIAL, useGetSet = true, canBeNull = false)
    private TipoMaterial tipoMaterial;

    public MaterialPromocional() {
    }

    public MaterialPromocional(String nombre, TipoMaterial tipoMaterial) {
        this.nombre = nombre;
        this.tipoMaterial = tipoMaterial;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaterialPromocional)) return false;

        MaterialPromocional that = (MaterialPromocional) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
