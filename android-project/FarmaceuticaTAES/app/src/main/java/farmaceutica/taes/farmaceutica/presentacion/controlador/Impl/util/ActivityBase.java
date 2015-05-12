package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by John on 12/05/2015.
 */
public class ActivityBase extends FragmentActivity {
    public static final String TAG = "SampleActivityBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected  void onStart() {
        super.onStart();
        //initializeLogging();
    }
}
