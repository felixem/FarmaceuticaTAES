package farmaceutica.taes.domainmodel.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import farmaceutica.taes.domainmodel.Data.Dao.GastoDao;

/**
 * Created by felix on 26/04/15.
 */

@DatabaseTable(tableName = Gasto.TABLE, daoClass = GastoDao.class)
public class Gasto
{
    //Tabla
    public static final String TABLE = "gasto";

    //Carpeta de las imagénes y nombre provisional
    public static final String DIRECTORIO= "Gastos";
    public static final String IMGPROVISIONAL= "prov.jpg";
    public static final String EXT_JPG =".jpg";

    //Campos
    public static final String ID = "_id";
    public static final String COSTE = "coste";
    public static final String IMGFACTURA = "img_factura";
    public static final String CONCEPTOGASTO = "concepto_gasto";

    //Relaciones
    public static final String REPORTEGASTOS = "reporte_gastos";

    //Campos relacionados
    public static final String REPORTEGASTOS_CAMPO = "reporteGastos";

    //Atributos
    @DatabaseField(columnName = ID, generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(columnName = COSTE, useGetSet = true, canBeNull = false)
    private float coste;

    @DatabaseField(columnName = IMGFACTURA, useGetSet = true)
    private String imgFactura;

    @DatabaseField(columnName = CONCEPTOGASTO, useGetSet = true, canBeNull = false)
    private ConceptoGasto conceptoGasto;

    //Relaciones
    @DatabaseField(columnName = REPORTEGASTOS, foreign = true, useGetSet = true, canBeNull = false)
    private ReporteGastos reporteGastos;

    public Gasto() {
    }

    public Gasto(float coste, ConceptoGasto conceptoGasto, ReporteGastos reporteGastos) {
        this.coste = coste;
        this.conceptoGasto = conceptoGasto;
        this.reporteGastos = reporteGastos;
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

    public String getImgFactura() {
        return imgFactura;
    }

    public void setImgFactura(String imgFactura) {
        this.imgFactura = imgFactura;
    }

    public ConceptoGasto getConceptoGasto() {
        return conceptoGasto;
    }

    public void setConceptoGasto(ConceptoGasto conceptoGasto) {
        this.conceptoGasto = conceptoGasto;
    }

    //Relaciones


    public ReporteGastos getReporteGastos() {
        return reporteGastos;
    }

    public void setReporteGastos(ReporteGastos reporteGastos) {
        this.reporteGastos = reporteGastos;
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
