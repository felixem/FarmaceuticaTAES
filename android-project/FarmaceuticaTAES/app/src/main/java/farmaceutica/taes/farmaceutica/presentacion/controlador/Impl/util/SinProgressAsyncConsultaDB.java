package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import farmaceutica.taes.farmaceutica.presentacion.controlador.OnConsultaListener;

/**
 * Created by John on 14/05/2015.
 */
public abstract class SinProgressAsyncConsultaDB extends AsyncTask<Void, Void, Void> implements OnConsultaListener{

    Context context;

    public SinProgressAsyncConsultaDB(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... params) {
        this.onConsultaDB();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
    }
}
