package farmaceutica.taes.domainmodel.Data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;


/**
 * Created by felix on 23-4-15;
 */
public class DatabaseManager {


    private DatabaseHelper databaseHelper = null;

    //gets a helper once one is created ensures it doesnt create a new one
    public DatabaseHelper getHelper(Context context)
    {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    //releases the helper once usages has ended
    public void releaseHelper(DatabaseHelper helper)
    {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

}