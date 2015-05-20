package farmaceutica.taes.farmaceutica.presentacion.controlador.util.view;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import farmaceutica.taes.domainmodel.Model.ConceptoGasto;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaGasto;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by John on 19/05/2015.
 */
public class CrearGastoView extends LinearLayout implements View.OnClickListener{

    //private ConceptoGasto mConGasto;
    //private float mCantidad;
    private Activity activity;

    private TextView txt_concepto_gasto;
    private TextView txt_cantidad;
    private ImageButton img_btn;

    private Gasto mGasto;
    private float ancho, largo;

    public CrearGastoView(Activity context, Gasto gasto) {
        super(context);
        this.activity = context;
        this.mGasto = gasto;
        InflarLayout(context);
        InitiateViews();
    }

    public CrearGastoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.activity = (Activity)context;
        //this.mGasto = gasto;
        InflarLayout(context);
        mGasto = FachadaGasto.obtenerGastoPorId(context, 1);
        //setImageButtonBackgroundRef(g.getImgFactura());
        InitiateViews();
    }

    private void InflarLayout(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        //Infla el layout dentro del groupView de este linearlayout
        //El tercer parámetro lo vincula como hijo del view group si está true.
        //Si está a false podemos vinclar el layout inflado mediante un this.addView()
        inflater.inflate(R.layout.view_crear_gasto, this, true);
    }

    private void InitiateViews(){
        //Instanciar views
        txt_concepto_gasto = (TextView) findViewById(R.id.txt_concpeto_gasto);
        txt_cantidad = (TextView) findViewById(R.id.txt_cantidad);
        img_btn = (ImageButton) findViewById(R.id.imgbtn_factura);

        //Instanciar listener
        this.setOnClickListener(this);

        //Cargar Datos
        txt_concepto_gasto.setText(mGasto.getConceptoGasto().getNombre());
        txt_cantidad.setText(getResources().getString(R.string.txt_coste) +  ' ' + String.valueOf(mGasto.getCoste()));
        ancho = getResources().getDimension(R.dimen.img_btn_factura);
        largo = getResources().getDimension(R.dimen.img_btn_factura);
        if (mGasto.getImgFactura() != null) {
            Bitmap bMap = decodeSampledBitmapFromFile(
                    Environment.getExternalStorageDirectory() +
                             mGasto.getImgFactura(), (ancho), (largo));
            img_btn.setImageBitmap(bMap);
        }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, float reqWidth, float reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, float reqWidth, float reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);

    }

    public void setOnImageButtonClickListener(View.OnClickListener listener){
        img_btn.setOnClickListener(listener);
    }

    private Bitmap getThumbnail(File file){
        byte[] imageData = null;
        Bitmap imageBitmap = null;

        try
        {

            final int THUMBNAIL_WIDTH = img_btn.getWidth();
            final int THUMBNAIL_HEIGHT = img_btn.getHeight();

            FileInputStream fis = new FileInputStream(file);
            imageBitmap = BitmapFactory.decodeStream(fis);

            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, false);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageData = baos.toByteArray();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return imageBitmap;
    }

    @Override
    public void onClick(View v) {
        final String[] items = {"Confirmar", "Eliminar", "Modificar"};

        AlertDialog.Builder builder =
                new AlertDialog.Builder(activity);

        builder.setTitle("Seleccion")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.i("Dialogos", "Opción elegida: " + items[item]);
                    }
                });
        builder.create().show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                setBackgroundColor(getResources().getColor(R.color.LightBlue));
                return true;
            case (MotionEvent.ACTION_UP):
                setBackgroundColor(getResources().getColor(android.R.color.transparent));
                onClick(this);
                return true;
            case (MotionEvent.ACTION_MOVE):
                return true;
            default:
                setBackgroundColor(getResources().getColor(android.R.color.transparent));
                return super.onTouchEvent(event);
        }
    }
}
