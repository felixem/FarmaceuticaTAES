package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.ListaVisitas;

/**
 * Created by Javi on 14/05/2015.
 */
public class EditVisitasFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_vista, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {



    }

    public void Modificar_Click(View v)
    {
        //Recibire la pregunta tanto para obtener la respuesta correcta indicada por el usuario
        //Como para obtener el resto de datos
        //Luego haré la modificación en la bolsa de preguntas
        //BolsaPregunta.getInstance().ModificarPreguntaInsertada(pregunta);
        //Haga lo que tenga que hacer
        //    Intent intent = new Intent(EditVisita.this, ListaVisitas.class);
        //     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //     startActivity(intent);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    /*public void onBackPressed() {

        fachadaComunicador.volverAtras();
        super.onBackPressed();
    }*/

}
