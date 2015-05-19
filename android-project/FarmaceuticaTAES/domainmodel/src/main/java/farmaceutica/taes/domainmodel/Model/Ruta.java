package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.RutaDao;

/**
 * Created by felix on 8/05/15.
 */

@DatabaseTable(tableName = Ruta.TABLE, daoClass = RutaDao.class)
public class Ruta
{
    //Tabla
    public static final String TABLE = "ruta";

    //Campos
    public static final String ID = "_id";
    public static final String FECHA = "fecha";
    public static final String VALIDADA = "validada";

    //Relaciones
    public static final String VISITADOR = "fk_visitador";

    //Campos relacionados
    public static final String VISITADOR_CAMPO = "visitador";

    //Campos relacionales
    public static final String CITAS_CAMPO = "fc_citas";

    //Atributos
    @DatabaseField(columnName = ID,generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = FECHA, useGetSet = true, canBeNull = false)
    private Date fecha;

    @DatabaseField(columnName = VALIDADA, useGetSet = true, canBeNull = false)
    private boolean validada;

    //Relaciones
    @DatabaseField(columnName = VISITADOR, foreign = true, useGetSet = true, canBeNull = false)
    private Visitador visitador;

    @ForeignCollectionField(eager=false, foreignFieldName = Cita.RUTA_CAMPO)
    private ForeignCollection<Cita> fc_citas;
    //Lista de citas wrap
    private List<Cita> citas;


    public Ruta() {
    }

    public Ruta(Date fecha, boolean validada, Visitador visitador) {
        this.fecha = fecha;
        this.validada = validada;
        this.visitador = visitador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getValidada() {
        return validada;
    }

    public void setValidada(boolean validada) {
        this.validada = validada;
    }

    public Visitador getVisitador() {
        return visitador;
    }

    public void setVisitador(Visitador visitador) {
        this.visitador = visitador;
    }

    public List<Cita> getCitas() {
        if(citas == null)
            citas = new ArrayList<Cita>(fc_citas);

        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
