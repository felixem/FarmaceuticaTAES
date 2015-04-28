package farmaceutica.taes.domainmodel.Data.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import farmaceutica.taes.domainmodel.Model.Ambulatorio;
import farmaceutica.taes.domainmodel.Model.MaterialPromocional;

/**
 * Created by felix on 28/04/15.
 */

public class MaterialPromocionalDao extends BaseDaoImpl<MaterialPromocional,Integer> implements IMaterialPromocionalDao
{
    // Constructor
    public MaterialPromocionalDao(ConnectionSource connectionSource)
            throws SQLException {
        super(connectionSource, MaterialPromocional.class);
    }
}
