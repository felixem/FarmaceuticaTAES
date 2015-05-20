package farmaceutica.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.Dao.MedicoDao;
import farmaceutica.taes.domainmodel.Data.Dao.ProductoDao;
import farmaceutica.taes.domainmodel.Data.Dao.VisitaProductoDao;
import farmaceutica.taes.domainmodel.Data.DatabaseHelper;
import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.domainmodel.Model.Producto;
import farmaceutica.taes.domainmodel.Model.Visita;

import farmaceutica.taes.domainmodel.Model.ValoracionProducto;
import farmaceutica.taes.domainmodel.Model.VisitaProducto;

/**
 * Created by felix on 28/04/15.
 */

public class ProductoRepository {
    private DatabaseHelper db;

    private ProductoDao mainDao;
    private VisitaProductoDao visitaProductoDao;

    public ProductoRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);

            mainDao = db.getProductoDao();
            visitaProductoDao = db.getVisitaProductoDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(Producto data)
    {
        try {
            return mainDao.create(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Producto data)
    {
        try {
            return mainDao.update(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Producto data)
    {
        try {
            return mainDao.delete(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public int refresh(Producto data)
    {
        try {
            return mainDao.refresh(data);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<Producto> getAll()
    {
        try {
            return mainDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Producto> getAllNotIn(List<Integer> idProductos)
    {
        try {
            QueryBuilder<Producto,Integer> builder = mainDao.queryBuilder();
            builder.where().notIn(Producto.ID,idProductos);
            return builder.query();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public Producto getProductoById(int id){

        try{
            QueryBuilder<Producto,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Producto.ID, id);
            builder.orderBy(Producto.NOMBRE,true);
            return builder.query().get(0);
        }catch(SQLException e){
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
    public long getCantidadValoracionProducto(int idProducto, ValoracionProducto valoracion) throws SQLException {

        try{
            QueryBuilder<Producto,Integer> builder = mainDao.queryBuilder();
            builder.where().eq(Producto.ID, idProducto);
            QueryBuilder<VisitaProducto,Integer> builderVisita = visitaProductoDao.queryBuilder();
            builderVisita.where().eq(VisitaProducto.VALORACION, valoracion);
            builder.join(builderVisita);
            return builder.countOf();

        }catch(Exception e){
            // TODO: Exception Handling
            throw e;
        }
    }
}
