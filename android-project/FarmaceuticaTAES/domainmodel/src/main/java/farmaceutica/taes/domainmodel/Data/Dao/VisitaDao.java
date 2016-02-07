package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.Visita;
import farmaceutica.taes.domainmodel.Model.VisitaMaterial;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;

/**
 * Created by felix on 28/04/15.
 */

public class VisitaDao extends BaseDaoImpl<Visita,Integer> implements IVisitaDao
{
    //Daos auxiliares
    IVisitaMaterialDao visitaMaterialDao;
    IVisitaProductoDao visitaProductoDao;

    // Constructor
    public VisitaDao(ConnectionSource connectionSource)
            throws SQLException {

        super(connectionSource, Visita.class);
        visitaMaterialDao = new VisitaMaterialDao(connectionSource);
        visitaProductoDao = new VisitaProductoDao(connectionSource);
    }

    @Override
    public int create(Visita data) throws SQLException
    {
        this.assignEmptyForeignCollection(data,Visita.FC_VISITAMATERIAL);
        this.assignEmptyForeignCollection(data,Visita.FC_VISITAPRODUCTO);
        int res = super.create(data);

        //Crear los materiales entregados
        for(VisitaMaterial visitaMaterial : data.getMaterialesEntregados())
        {
            visitaMaterial.setVisita(data);
            visitaMaterialDao.create(visitaMaterial);
        }

        //Crear los productos ofertados
        for(VisitaProducto visitaProducto : data.getProductosOfertados())
        {
            visitaProducto.setVisita(data);
            visitaProductoDao.create(visitaProducto);
        }

        return res;
    }

    @Override
    public int update(Visita data) throws SQLException
    {
        int res = super.update(data);

        //Actualizar los materiales entregados
        for(VisitaMaterial visitaMaterial : data.getMaterialesEntregados())
        {
            visitaMaterial.setVisita(data);
            visitaMaterialDao.update(visitaMaterial);
        }

        //Actualizar los productos ofertados
        for(VisitaProducto visitaProducto : data.getProductosOfertados())
        {
            visitaProducto.setVisita(data);
            visitaProductoDao.update(visitaProducto);
        }

        return res;
    }

    @Override
    public int delete(Visita data) throws SQLException
    {
        //Borrar los materiales entregados
        for(VisitaMaterial visitaMaterial : data.getMaterialesEntregados())
        {
            visitaMaterialDao.delete(visitaMaterial);
        }

        //Borrar los productos ofertados
        for(VisitaProducto visitaProducto : data.getProductosOfertados())
        {
            visitaProductoDao.delete(visitaProducto);
        }

        return super.delete(data);
    }
}
