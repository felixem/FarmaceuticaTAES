package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import farmaceutica.taes.domainmodel.Model.ConceptoGasto;
import farmaceutica.taes.domainmodel.Model.Gasto;
import farmaceutica.taes.domainmodel.Model.ReporteGastos;
import farmaceutica.taes.domainmodel.Model.Visitador;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.OnSpinnerListener;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaGastos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaReportes;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorSpinnerConceptoGasto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.Linker;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaGasto;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaReporteGastos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.CrearGastoView;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.view.SpinnerOnChangeAdapter;

/**
 * Created by John on 12/05/2015.
 */
public class MisGastosFragment extends BaseFragment implements OnSpinnerListener, View.OnClickListener{
    private final String path = Gasto.DIRECTORIO + "/" + Gasto.IMGPROVISIONAL;
    ImageButton img_btn;
    SpinnerOnChangeAdapter spinnerReportes;
    SpinnerOnChangeAdapter spinnerGastos;
    TextView textView_reportes;
    TextView textView_gastos;
    //Button button_ver_detalles;

    private LinearLayout ll_container;
    private Button btn_reportar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_gastos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ll_container = (LinearLayout) view.findViewById(R.id.ll_container);
        spinnerReportes = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_reportes);
        spinnerGastos = (SpinnerOnChangeAdapter) view.findViewById(R.id.spinner_gastos);
        textView_reportes = (TextView) view.findViewById(R.id.txt_reporte);
        textView_gastos = (TextView) view.findViewById(R.id.txt_gasto);
        //button_ver_detalles = (Button) view.findViewById(R.id.button_ver_detalles);
        btn_reportar = (Button) view.findViewById(R.id.btn_reportar);

                //Vincular los listeners
        spinnerReportes.setOnSpinnerListener(this);
        spinnerGastos.setOnSpinnerListener(this);

        //Creado provisionalmente un visitador
        Visitador visitador = new Visitador();
        visitador.setCodigo(1);

        //Vincular al spinner de reportes los reportes
        final BaseAdapter adapter = new AdaptadorListaReportes(getActivity(), FachadaReporteGastos.obtenerReportesGastosPorVisitador(getActivity(), visitador));
        spinnerReportes.setAdapter(adapter);

        //Establecer eventos en el spinner de centros médicos
        spinnerReportes.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View v, int position, long id) {

                        //Meter en el spinner de gastos los gastos del reporte
                        ReporteGastos reporte = (ReporteGastos) parent.getItemAtPosition(position);
                        List<Gasto> gastos = FachadaGasto.obtenerGastosPorReporteGasto(getActivity(), reporte);
                        //BaseAdapter adapter = new AdaptadorListaGastos(getActivity(), gastos);
                        BaseAdapter adapter = new AdaptadorSpinnerConceptoGasto(getActivity());
                        spinnerGastos.setAdapter(adapter);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        spinnerGastos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CargarLinearLayouContainer();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linker linker = new Linker(getFragmentManager(), true);
                linker.CrearGasto();
            }
        });
    }

    @Override
    public void onAdapterChange(View v)
    {
        SpinnerAdapter adapter;
        //Gestionar set adapter a spinner centros médicos
        if(v.equals(spinnerReportes))
        {
            adapter = spinnerReportes.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                textView_reportes.setVisibility(View.VISIBLE);
                spinnerReportes.setVisibility(View.INVISIBLE);
                textView_reportes.setText("No se encontraron reportes");
                spinnerGastos.setAdapter(new AdaptadorListaGastos(getActivity(),new ArrayList<Gasto>()));
            }
            else
            {
                textView_reportes.setVisibility(View.VISIBLE);
                spinnerReportes.setVisibility(View.VISIBLE);
                textView_reportes.setText("Selecciona el reporte de gastos");

                ReporteGastos reporte = (ReporteGastos)spinnerReportes.getSelectedItem();
                List<Gasto> gastos = FachadaGasto.obtenerGastosPorReporteGasto(getActivity(), reporte);
                BaseAdapter adaptadorbase = new AdaptadorListaGastos(getActivity(),gastos);
                spinnerGastos.setAdapter(adaptadorbase);
            }

        }
        //Gestionar set adapter a spinner médicos
        else if(v.equals(spinnerGastos))
        {
            adapter = spinnerGastos.getAdapter();

            //Comprobar si está vacío
            if(adapter.isEmpty())
            {
                textView_gastos.setVisibility(View.VISIBLE);
                spinnerGastos.setVisibility(View.INVISIBLE);
                textView_gastos.setText("No se encontraron gastos asociados");
                //button_ver_detalles.setVisibility(View.INVISIBLE);
            }
            else
            {
                textView_gastos.setVisibility(View.VISIBLE);
                spinnerGastos.setVisibility(View.VISIBLE);
                textView_gastos.setText("Selecciona gasto");
                //button_ver_detalles.setVisibility(View.VISIBLE);
                CargarLinearLayouContainer();
            }

        }

    }

    private void CargarLinearLayouContainer(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_container.getLayoutParams();
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        ll_container.removeAllViewsInLayout();
        ll_container.setLayoutParams(params);
        ReporteGastos rg = (ReporteGastos)spinnerReportes.getSelectedItem();
        ConceptoGasto cg = ((ConceptoGasto)spinnerGastos.getSelectedItem());
        List<Gasto> gastos = FachadaGasto.obtenerGastosPorReporteGasto(getActivity(), rg);
        CrearGastoView cgv;
        for(Gasto g : gastos){
            if(cg.equals(g.getConceptoGasto())) {
                cgv = new CrearGastoView(getActivity(), g);
                cgv.setOnImageButtonClickListener(this);
                ll_container.addView(cgv);
            }
        }
    }

    @Override
    public void onClick(View v) {
        img_btn = (ImageButton)v;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona opcion");
        String[] items = {"Ver en galeria", "Cambiar foto"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        String factura = ((CrearGastoView)img_btn.getParent().getParent().getParent()).getGasto().getImgFactura();
                        if(factura != null && factura.length() != 0) {
                            File imagen = Environment.getExternalStorageDirectory();
                            imagen = new File(imagen, factura);
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.fromFile(imagen), "image/*");
                            startActivity(intent);
                        }else
                            Toast.makeText(getActivity(), "No hay imagen vinculada", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
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
                        break;
                }
            }
        }).create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Comprobamos que la foto se a realizado
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            //Mover foto desde el path provisional al del gasto
            File sd = Environment.getExternalStorageDirectory();
            // File (or directory) to be moved
            String sourcePath = "/" + path;

            //Vinculamos la imagen al boton
            img_btn.setImageURI(Uri.fromFile(new File(sd, sourcePath)));

            File file = new File(sd, sourcePath);
            // Destination directory
            Gasto gasto = ((CrearGastoView)img_btn.getParent().getParent().getParent()).getGasto();
            String destino = "/" + Gasto.DIRECTORIO + "/" + gasto.getId() + gasto.EXT_JPG;
            boolean success = file.renameTo(new File(sd, destino));

            //Guardar foto
            gasto.setImgFactura(destino);
            FachadaGasto.update(getActivity(), gasto);
        }
    }
}
