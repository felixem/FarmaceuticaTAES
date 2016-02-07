package farmaceutica.taes.farmaceutica.presentacion.controlador.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerTabStrip;
import android.util.AttributeSet;

import farmaceutica.taes.farmaceutica.R;

/**
 * Created by John on 13/05/2015.
 */
public class ColoredPagerTabStrip extends PagerTabStrip {

    public ColoredPagerTabStrip(Context context, AttributeSet attrs){
        super(context,attrs);
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColoredPagerTabStrip);
        try {
            setTabIndicatorColor(array.getColor(R.styleable.ColoredPagerTabStrip_indicatorColor, R.color.Black));
            setTextColor(array.getColor(R.styleable.ColoredPagerTabStrip_textColor, R.color.White));
        }finally {
            array.recycle();
        }
    }

}
