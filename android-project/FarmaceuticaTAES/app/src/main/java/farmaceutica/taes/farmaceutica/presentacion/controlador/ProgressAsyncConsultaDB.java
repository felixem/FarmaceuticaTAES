package farmaceutica.taes.farmaceutica.presentacion.controlador;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import farmaceutica.taes.farmaceutica.presentacion.controlador.OnConsultaListener;

/**
 * Created by John on 14/05/2015.
 */
public abstract class ProgressAsyncConsultaDB extends AsyncTask<Void, Void, Void> implements OnConsultaListener{

    ProgressDialog progressDialog;
    Context context;

    public ProgressAsyncConsultaDB(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando...");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        this.onConsultaDB();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
    }
}
