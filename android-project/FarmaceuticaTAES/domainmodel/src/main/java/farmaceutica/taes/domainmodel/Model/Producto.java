package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 26/04/15.
 */

@DatabaseTable(tableName = Producto.TABLE)
public class Producto {
    //Tabla
    public static final String TABLE = "producto";

    //Campos
    public static final String ID = "cod_nacional";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";

    //Atributos
    @DatabaseField(columnName = ID, id = true, useGetSet = true)
    private int codNacional;

    @DatabaseField(columnName = NOMBRE, useGetSet = true, unique = true, canBeNull = false)
    private String nombre;

    @DatabaseField(columnName = NOMBRE, useGetSet = true, canBeNull = false)
    private String descripcion;

    public Producto() {
    }

    public Producto(int codNacional, String nombre, String descripcion) {
        this.codNacional = codNacional;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getCodNacional() {
        return codNacional;
    }

    public void setCodNacional(int codNacional) {
        this.codNacional = codNacional;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;

        Producto producto = (Producto) o;

        if (codNacional != producto.codNacional) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codNacional;
    }
}
