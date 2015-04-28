package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by felix on 28/04/15.
 */


public class HorarioVisitable
{
    //TABLA
    public static final String TABLE = "horario_visitable";

    //Campos
    public static final String ID = "_id";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    public HorarioVisitable() {
    }

    public HorarioVisitable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HorarioVisitable)) return false;

        HorarioVisitable that = (HorarioVisitable) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
