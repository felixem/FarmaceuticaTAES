package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
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
