package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.MotionEventCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Calendar;

import farmaceutica.taes.domainmodel.Model.ConceptoGasto;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorSpinnerConceptoGasto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.MySession;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaGasto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaReporteGastos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.CrearGastoView;

/**
 * Created by John on 15/05/2015.
 */
public class CrearGastosFragment extends BaseFragment implements View.OnClickListener{
    private int year, month, day;
    private final String path = Gasto.DIRECTORIO + "/" + Gasto.IMGPROVISIONAL;
    private float coste;

    private float ancho, largo;
    private final View.OnClickListener GastosListener = this;

    private Spinner sp;
    private DatePicker date_picker;
    private ImageButton img_btn;
    private LinearLayout ll_container;
    private LinearLayout ll_main_gasto_container;
    private Button btn_crear_gasto;
    private Button btn_confirmar_dia;
    private ReporteGastos reporteGastos;
    private TextView txt_titulo_gastos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_gasto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Instanciamos vistas
        txt_titulo_gastos = (TextView) view.findViewById(R.id.txt_titulo_gastos);
        sp = (Spinner) view.findViewById(R.id.sp_dfault);
        date_picker = (DatePicker) view.findViewById(R.id.date_picker);
        ll_container = (LinearLayout) view.findViewById(R.id.ll_container);
        btn_crear_gasto = (Button) view.findViewById(R.id.btn_crear_gasto);
        btn_confirmar_dia = (Button) view.findViewById(R.id.btn_confirmar_dia);
        ll_main_gasto_container = (LinearLayout) view.findViewById(R.id.ll_main_gasto_container);

        //Instanciamos adaptador en el spinner
        sp.setAdapter(new AdaptadorSpinnerConceptoGasto(getActivity()));

        //Cargamos los datos
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        date_picker.init(year, month, day, null);

        txt_titulo_gastos.setVisibility(View.INVISIBLE);

        ancho = getResources().getDimension(R.dimen.img_btn_factura);
        largo = getResources().getDimension(R.dimen.img_btn_factura);

        //Evento onClick de el botón CrearGasto
        btn_crear_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(getActivity());
                dialog.setTitle("Introduce el coste");
                final EditText editText = new EditText(getActivity());
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                editText.setTextColor(Color.WHITE);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                dialog.setView(editText);
                dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Tras seleccionar el valor del coste genera el CrearGastoView
                        try {
                            coste = Float.valueOf(editText.getText().toString());
                            ConceptoGasto conceptoGasto = (ConceptoGasto) sp.getSelectedItem();
                            Gasto gasto = FachadaGasto.crearGasto(getActivity(), coste, conceptoGasto, reporteGastos);
                            CrearGastoView cgv = new CrearGastoView(getActivity(), gasto);
                            cgv.setOnImageButtonClickListener(GastosListener);
                            ll_container.addView(cgv);
                            dialog.dismiss();

                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(getActivity(), "Se ha creado el gasto", duration);
                            toast.show();
                            

                        } catch (Exception ex) {
                            dialog.dismiss();
                        }
                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.create().show();
            }
        });
        //Evento onClick del botón ConfirmarDia
        btn_confirmar_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                c.set(date_picker.getYear(), date_picker.getMonth(), date_picker.getDayOfMonth());
                Visitador visitador = ((MySession) getActivity().getApplication()).getVisitador();
                reporteGastos = FachadaReporteGastos.CrearReporteGasto(getActivity(), visitador, c.getTime());
                ll_main_gasto_container.setVisibility(View.VISIBLE);
                txt_titulo_gastos.setVisibility(View.VISIBLE);

            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Comprobamos que la foto se a realizado
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria

            Bitmap bMap = decodeSampledBitmapFromFile(
                    Environment.getExternalStorageDirectory() +
                            "/" + path, ancho, largo);
            //Añadimos el bitmap al imageView para
            //mostrarlo por pantalla
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

    @Override
    public void onClick(View v) {
        img_btn = (ImageButton)v;
        //Creamos el Intent para llamar a la Camara
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //Creamos una carpeta en la memeria del terminal
        File imagesFolder = new File(
                Environment.getExternalStorageDirectory(), Gasto.DIRECTORIO);
        imagesFolder.mkdirs();
        //añadimos el nombre de la imagen
        File image = new File(imagesFolder, Gasto.IMGPROVISIONAL);
        Uri uriSavedImage = Uri.fromFile(image);
        //Le decimos al Intent que queremos grabar la imagen
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        //Lanzamos la aplicacion de la camara con retorno (forResult)
        startActivityForResult(cameraIntent, 1);
    }

}
