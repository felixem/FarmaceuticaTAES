package farmaceutica.taes.farmaceutica.presentacion.controlador.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;

/**
 * Created by felix on 14/05/15.
 */
public class SpinnerOnChangeAdapter extends Spinner {

    OnSpinnerListener listener;


    public SpinnerOnChangeAdapter(Context context) {
        super(context);
        listener=null;
    }

    public SpinnerOnChangeAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        listener=null;
    }

    public void setOnSpinnerListener(OnSpinnerListener l){
        listener = l;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        super.setAdapter(adapter);
        if(listener != null){
            listener.onAdapterChange(this);
        }
    }
}


