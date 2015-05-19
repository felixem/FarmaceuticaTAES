package farmaceutica.taes.domainmodel.Model;

/**
 * Created by felix on 26/04/15.
 */
public enum ConceptoGasto {

    //Declaración de constantes
    COMIDA("Comida", "Consumiciones"),
    PARKING("Parking", "Aparcamiento"),
    TRANSPORTE("Transporte", "Desplazamientos");

    //Variables
    private final String nombre;
    private final String descripcion;
    public final static int total_estados = 3;

    ConceptoGasto(String nombre, String descripcion)
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
