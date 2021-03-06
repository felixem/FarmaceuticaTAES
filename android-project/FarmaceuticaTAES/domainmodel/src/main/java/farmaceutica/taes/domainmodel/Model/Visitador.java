package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.VisitadorDao;

/**
 * Created by felix on 26/04/15.
 */

@DatabaseTable(tableName = Visitador.TABLE, daoClass = VisitadorDao.class)
public class Visitador {
    //Tabla
    public static final String TABLE = "visitador";

    //Campos
    public static final String ID="codigo";
    public static final String PASSWORD="pass";
    public static final String NOMBRE="nombre";
    public static final String APELLIDOS="apellidos";
    public static final String TELEFONO="telefono";
    public static final String EMAIL="email";
    public static final String INFOADICIONAL="info_adicional";

    //Atributos

    @DatabaseField(columnName = ID, id=true, useGetSet = true)
    private int codigo;

    @DatabaseField(columnName = PASSWORD, useGetSet = true, canBeNull = false)
    private String password;

    @DatabaseField(columnName = NOMBRE, useGetSet = true, canBeNull = false)
    private String nombre;

    @DatabaseField(columnName = APELLIDOS, useGetSet = true, canBeNull = false)
    private String apellidos;

    @DatabaseField(columnName = TELEFONO, useGetSet = true, canBeNull = false)
    private String telefono;

    @DatabaseField(columnName = EMAIL, useGetSet = true, unique=true, canBeNull = false)
    private String email;

    @DatabaseField(columnName = INFOADICIONAL, useGetSet = true)
    private String infoAdicional;

    //Relaciones
    @ForeignCollectionField(eager = false, foreignFieldName = ReporteGastos.VISITADOR_CAMPO)
    private ForeignCollection<ReporteGastos> reportesGastos;

    @ForeignCollectionField(eager = false, foreignFieldName = Visita.VISITADOR_CAMPO)
    private ForeignCollection<Visita> visitas;

    @ForeignCollectionField(eager = false, foreignFieldName = Ruta.VISITADOR_CAMPO)
    private ForeignCollection<Ruta> rutas;


    public Visitador() {
    }

    public Visitador(int codigo, String password, String nombre, String apellidos, String telefono, String email) {
        this.codigo = codigo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    //Relaciones


    public ForeignCollection<ReporteGastos> getReportesGastos() {
        return reportesGastos;
    }

    public void setReportesGastos(ForeignCollection<ReporteGastos> reportesGastos) {
        this.reportesGastos = reportesGastos;
    }

    public ForeignCollection<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(ForeignCollection<Visita> visitas) {
        this.visitas = visitas;
    }

    public ForeignCollection<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(ForeignCollection<Ruta> rutas) {
        this.rutas = rutas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitador)) return false;

        Visitador visitador = (Visitador) o;

        if (codigo != visitador.codigo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
