package farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.listeners;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import farmaceutica.taes.domainmodel.Model.CentroMedico;
import farmaceutica.taes.domainmodel.Model.Medico;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.AdaptadorListaMedicos;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.app.fachadas.FachadaMedico;

/**
 * Created by felix on 13/05/15.
 */
public class ListenerSpinnerCentrosMedicos implements AdapterView.OnItemSelectedListener
{
    //Variables necesarias
    private Spinner spinnerMedicos;
    private Spinner spinnerCentros;
    private TextView textView_centros;
    private TextView textView_medicos;
    private Context contexto;

    //Constructor con los elementos necesarios
    public ListenerSpinnerCentrosMedicos(Spinner spinnerCentros, Spinner spinnerMedicos,
                                        TextView textView_centros, TextView textView_medicos,
                                        Context contexto)
    {
        this.spinnerCentros = spinnerCentros;
        this.spinnerMedicos = spinnerMedicos;
        this.textView_centros = textView_centros;
        this.textView_medicos = textView_medicos;
        this.contexto = contexto;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent,
                               android.view.View v, int position, long id) {

        spinnerCentros.setVisibility(View.VISIBLE);
        textView_centros.setText("Selecciona un centro médico");

        //Meter en el spinner de médicos los médicos del centro
        CentroMedico centro = (CentroMedico)parent.getItemAtPosition(position);
        List<Medico> medicos = FachadaMedico.obtenerMedicosPorCentroMedico(contexto, centro);
        BaseAdapter adapter = new AdaptadorListaMedicos(contexto, medicos);

        spinnerMedicos.setAdapter(adapter);
        textView_medicos.setVisibility(View.VISIBLE);

        if(medicos.size() != 0)
        {
            textView_medicos.setText("Selecciona un médico");
            spinnerMedicos.setVisibility(View.VISIBLE);
        }
        else
        {
            spinnerMedicos.setVisibility(View.INVISIBLE);
            textView_medicos.setText("No se encontraron médicos");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        textView_centros.setText("No se encontraron centros médicos");
        spinnerCentros.setVisibility(View.INVISIBLE);
        textView_medicos.setVisibility(View.INVISIBLE);
    }
}
