package farmaceutica.taes.domainmodel.Model;

/**
 * Created by felix on 26/04/15.
 */
public enum TipoCliente {

    //Declaración de constantes
    A("Excelente", "Cliente Excelente"),
    B("Bueno", "Cliente Bueno"),
    C("Malo", "Cliente Malo");

    //Variables
    private final String nombre;
    private final String descripcion;

    TipoCliente(String nombre, String descripcion)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
