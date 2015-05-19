package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import farmaceutica.taes.farmaceutica.R;

/**
 * Created by Paco on 13/05/2015.
 */
public class AlertaDialogo extends DialogFragment {

    private boolean salir = false;
    private String mensaje;
    private Class<?> destino;
    private String titulo;
    private String boton1;
    private String boton2;
    private boolean flags;


    public void Salir()
    {
        salir = true;
    }
    public void setMensaje(String m)
    {
        mensaje = m;
    }
    public void setDestino(Class<?> des)
    {
        destino = des;
    }

    public void setBoton2(String boton2) {
        this.boton2 = boton2;
    }

    public void setBoton1(String boton1) {
        this.boton1 = boton1;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public void setFlags(boolean f)
    {flags = f;}
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if(boton2=="") {
            builder.setMessage(mensaje).
                    setTitle(titulo).setPositiveButton(boton1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
        }else
        {
            builder.setMessage(mensaje).
                    setTitle(titulo).setPositiveButton(boton1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    if (salir) {
                        System.exit(0);


                    } else {
                        //Lanzamos la actividad
                        if (destino != null) {
                            Intent intent = new Intent(getActivity(), destino);
                            if (flags)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                            startActivity(intent);

                        }
                    }
                }
            }).setNegativeButton(boton2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            }).setOnKeyListener(new DialogInterface.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                    return true;


                }
            });

        }

        return builder.create();
    }




}
