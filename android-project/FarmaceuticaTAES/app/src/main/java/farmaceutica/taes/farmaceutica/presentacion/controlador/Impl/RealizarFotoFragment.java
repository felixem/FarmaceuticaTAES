package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.Utilities.ImageConverter;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaGasto;


/**
 * Created by John on 12/05/2015.
 */
public class RealizarFotoFragment extends BaseFragment {

    //Necesitamos un Boton y un imageView
    private Button bt_hacerfoto;
    private Button bt_guardarfoto;
    private ImageView img;
    private Gasto gasto;
    final String path = Gasto.DIRECTORIO + "/" + Gasto.IMGPROVISIONAL;
    final int ancho = 400;
    final int largo = 400;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_realizar_foto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Relacionamos con el XML
        img = (ImageView) view.findViewById(R.id.imageView1);
        bt_hacerfoto = (Button) view.findViewById(R.id.button1);
        bt_guardarfoto = (Button) view.findViewById(R.id.button_guardar_foto);

        //Cargar gasto
        gasto = FachadaGasto.obtenerGastoPorId(getActivity(), 1);
        if (gasto.getImgFactura() != null) {
            Bitmap bMap = decodeSampledBitmapFromFile(
                    Environment.getExternalStorageDirectory() +
                            "/" + gasto.getImgFactura(),ancho,largo);
            img.setImageBitmap(bMap);
        }

        //Añadimos el Listener Boton
        bt_hacerfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        //Añadimos el Listener Boton
        bt_guardarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("¿Almacenar foto?.");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Confirmar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //Mover foto desde el path provisional al del gasto
                                File sd = Environment.getExternalStorageDirectory();
                                // File (or directory) to be moved
                                String sourcePath = "/" + path;
                                File file = new File(sd, sourcePath);
                                // Destination directory
                                String destino = "/" + Gasto.DIRECTORIO + "/" + gasto.getId() + gasto.EXT_JPG;
                                boolean success = file.renameTo(new File(sd, destino));

                                //Guardar foto
                                gasto.setImgFactura(destino);
                                FachadaGasto.update(getActivity(), gasto);
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Comprobamos que la foto se a realizado
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria

            Bitmap bMap = decodeSampledBitmapFromFile(
                    Environment.getExternalStorageDirectory() +
                            "/" + path,ancho,largo);
            //Añadimos el bitmap al imageView para
            //mostrarlo por pantalla
            img.setImageBitmap(bMap);
        }
    }

    public static RealizarFotoFragment newInstance() {

        // Instantiate a new fragment
        RealizarFotoFragment fragment = new RealizarFotoFragment();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
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

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {

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
}
