package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.content.Context;
import android.os.AsyncTask;

import farmaceutica.taes.domainmodel.Data.DatabaseManager;

/**
 * Created by John on 13/05/2015.
 */
public class IniciarAsyncDB extends AsyncTask<Context, Void, Void> {
    @Override
    protected Void doInBackground(Context... params) {

        DatabaseManager dataManager = new DatabaseManager();
        dataManager.getHelper(params[0]).getWritableDatabase();

        return null;
    }
}
