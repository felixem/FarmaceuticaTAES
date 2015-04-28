package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by felix on 26/04/15.
 */

@DatabaseTable(tableName = Gasto.TABLE)
public class Gasto
{
    //Tabla
    public static final String TABLE = "gasto";

    //Campos
    public static final String ID = "_id";
    public static final String COSTE = "coste";
    public static final String IMGFACTURA = "img_factura";
    public static final String CONCEPTOGASTO = "concepto_gasto";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = COSTE, useGetSet = true, canBeNull = false)
    private float coste;

    @DatabaseField(columnName = IMGFACTURA, useGetSet = true)
    private byte[] imgFactura;

    @DatabaseField(columnName = CONCEPTOGASTO, useGetSet = true, canBeNull = false)
    private ConceptoGasto conceptoGasto;

    public Gasto() {
    }

    public Gasto(float coste, ConceptoGasto conceptoGasto) {
        this.coste = coste;
        this.conceptoGasto = conceptoGasto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCoste() {
        return coste;
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }

    public byte[] getImgFactura() {
        return imgFactura;
    }

    public void setImgFactura(byte[] imgFactura) {
        this.imgFactura = imgFactura;
    }

    public ConceptoGasto getConceptoGasto() {
        return conceptoGasto;
    }

    public void setConceptoGasto(ConceptoGasto conceptoGasto) {
        this.conceptoGasto = conceptoGasto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasto)) return false;

        Gasto gasto = (Gasto) o;

        if (id != gasto.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}