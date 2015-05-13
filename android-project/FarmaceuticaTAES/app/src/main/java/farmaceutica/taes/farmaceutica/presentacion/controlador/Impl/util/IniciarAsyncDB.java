package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import farmaceutica.taes.domainmodel.Data.DatabaseManager;

/**
 * Created by John on 13/05/2015.
 */
public class IniciarAsyncDB{
    private static IniciarAsyncDB instance = null;

    private IniciarAsyncDB(){}
    public static void iniciarDB(Context c){
        if(instance == null) {
            instance = new IniciarAsyncDB();
            ProgressDialog progress = new ProgressDialog(c);
            progress.setMessage("Cargando datos...");
            new MyAsyncInit(progress).execute(c);
        }
    }

    private static class MyAsyncInit extends AsyncTask<Context, Void, Void> {

        ProgressDialog progress;

        public MyAsyncInit(ProgressDialog progress){
            this.progress = progress;
        }

        @Override
        protected void onPreExecute() {
            progress.show();
        }

        @Override
        protected Void doInBackground(Context... params) {

            DatabaseManager dataManager = new DatabaseManager();
            dataManager.getHelper(params[0]).getWritableDatabase();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progress.dismiss();
        }
    }
}