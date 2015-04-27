package farmaceutica.taes.domainmodel.Model;

/**
 * Created by felix on 26/04/15.
 */
public enum ConceptoGasto {

    //Declaración de constantes
    A("Comida", "Consumiciones"),
    B("Parking", "Aparcamiento"),
    C("Transporte", "Desplazamientos");

    //Variables
    private final String nombre;
    private final String descripcion;

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
