package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.MaterialPromocionalDao;

/**
 * Created by felix on 28/04/15.
 */

@DatabaseTable(tableName = MaterialPromocional.TABLE, daoClass = MaterialPromocionalDao.class)
public class MaterialPromocional
{
    //Tabla
    public static final String TABLE = "material_promocional";

    //Campos
    public static final String ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String TIPOMATERIAL = "tipo_material";

    //Relaciones
    public static final String PRODUCTO = "fk_producto";

    //Campos relacionados
    public static final String PRODUCTO_CAMPO = "producto";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = NOMBRE, useGetSet = true, canBeNull = false)
    private String nombre;

    @DatabaseField(columnName = DESCRIPCION, useGetSet = true)
    private String descripcion;

    @DatabaseField(columnName = TIPOMATERIAL, useGetSet = true, canBeNull = false)
    private TipoMaterial tipoMaterial;

    //Relaciones
    @DatabaseField(columnName = PRODUCTO, foreign = true, useGetSet = true, canBeNull = false)
    private Producto producto;

    public MaterialPromocional() {
    }

    public MaterialPromocional(String nombre, TipoMaterial tipoMaterial, Producto producto) {
        this.nombre = nombre;
        this.tipoMaterial = tipoMaterial;
        this.producto = producto;
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

    //Relaciones


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
