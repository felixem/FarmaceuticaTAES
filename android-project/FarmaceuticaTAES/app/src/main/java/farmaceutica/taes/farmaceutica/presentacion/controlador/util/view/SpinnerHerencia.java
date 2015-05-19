package farmaceutica.taes.farmaceutica.presentacion.controlador.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by John on 17/05/2015.
 */
public class SpinnerHerencia extends Spinner {
    public SpinnerHerencia(Context context) {
        super(context);
    }

    /*public SpinnerHerencia(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }*/

    public SpinnerHerencia(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
