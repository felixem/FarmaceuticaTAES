package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.MedicoDao;

/**
 * Created by felix on 25/04/15.
 */

@DatabaseTable(tableName = Medico.TABLE, daoClass = MedicoDao.class)
public class Medico {
    //Tabla
    public static final String TABLE = "medico";

    //Campos
    public static final String ID = "_id";
    public static final String NUMCORRELATIVO = "num_correlativo";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDOS = "apellidos";
    public static final String TELEFONO = "telefono";
    public static final String EMAIL = "email";
    public static final String INFORMACIONADICIONAL = "info_adicional";
    public static final String VISITABLE = "visitable";
    public static final String TIPOCLIENTE = "tipo_cliente";

    //Relaciones
    public static final String COLEGIOACTUAL = "fk_colegio_actual";
    public static final String PRIMERCOLEGIO = "fk_primer_colegio";
    public static final String ESPECIALIDAD = "fk_especialidad";

    //Campos relacionados
    public static final String COLEGIOACTUAL_CAMPO = "colegioActual";
    public static final String PRIMERCOLEGIO_CAMPO = "primerColegio";
    public static final String ESPECIALIDAD_CAMPO = "especialidad";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = NUMCORRELATIVO, useGetSet = true, canBeNull = false)
    private int numCorrelativo;

    @DatabaseField(columnName = NOMBRE, useGetSet = true, canBeNull = false)
    private String nombre;

    @DatabaseField(columnName = APELLIDOS, useGetSet = true, canBeNull = false)
    private String apellidos;

    @DatabaseField(columnName = TELEFONO, useGetSet = true)
    private String telefono;

    @DatabaseField(columnName = EMAIL, useGetSet = true)
    private String email;

    @DatabaseField(columnName = INFORMACIONADICIONAL, useGetSet = true)
    private String informacionAdicional;

    @DatabaseField(columnName = VISITABLE, useGetSet = true, canBeNull = false)
    private boolean visitable;

    @DatabaseField(columnName = TIPOCLIENTE, useGetSet = true, canBeNull = false)
    private TipoCliente tipoCliente;

    //Relaciones
    @DatabaseField(columnName = COLEGIOACTUAL, foreign = true, useGetSet = true, canBeNull = false)
    private Provincia colegioActual;

    @DatabaseField(columnName = PRIMERCOLEGIO, foreign = true, useGetSet = true, canBeNull = false)
    private Provincia primerColegio;

    @DatabaseField(columnName = ESPECIALIDAD, foreign = true, useGetSet = true)
    private EspecialidadMedica especialidad;

    @ForeignCollectionField(eager = false, foreignFieldName = Visita.MEDICO_CAMPO)
    private ForeignCollection<Visita> visitas;

    public Medico() {
    }

    public Medico(int numCorrelativo, String nombre, String apellidos, boolean visitable, TipoCliente tipoCliente, Provincia colegioActual, Provincia primerColegio) {
        this.numCorrelativo = numCorrelativo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.visitable = visitable;
        this.tipoCliente = tipoCliente;
        this.colegioActual = colegioActual;
        this.primerColegio = primerColegio;
    }

    public boolean getVisitable() {
        return visitable;
    }

    public void setVisitable(boolean visitable) {
        this.visitable = visitable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCorrelativo() {
        return numCorrelativo;
    }

    public void setNumCorrelativo(int numCorrelativo) {
        this.numCorrelativo = numCorrelativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    //Relaciones


    public Provincia getColegioActual() {
        return colegioActual;
    }

    public void setColegioActual(Provincia colegioActual) {
        this.colegioActual = colegioActual;
    }

    public Provincia getPrimerColegio() {
        return primerColegio;
    }

    public void setPrimerColegio(Provincia primerColegio) {
        this.primerColegio = primerColegio;
    }

    public ForeignCollection<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(ForeignCollection<Visita> visitas) {
        this.visitas = visitas;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadMedica especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico)) return false;

        Medico medico = (Medico) o;

        if (id != medico.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
