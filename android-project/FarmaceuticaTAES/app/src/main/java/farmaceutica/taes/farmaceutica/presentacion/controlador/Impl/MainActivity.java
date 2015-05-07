package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by felix on 7/05/15.
 */
public class MainActivity extends Activity{


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        TextView txt= new TextView(this);
        txt.setText("probando");
        setContentView(txt);
    }

}
