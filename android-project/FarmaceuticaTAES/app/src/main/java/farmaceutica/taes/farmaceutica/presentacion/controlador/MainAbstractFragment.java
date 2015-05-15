package farmaceutica.taes.farmaceutica.presentacion.controlador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.Linker;

/**
 * Created by John on 15/05/2015.
 *
 * Cuando se herede de MainAbstractFragment debe implementarse la funci�n
 * onMainLinkFragment() en la que solo se indicar� en la fragment principal que
 * se cargar� en esa instancia.
 *
 * Para ello se recomienda implentar la funci�nMainLinkFragment llamando
 * al atributo protected linker llamando a la funci�n super y seleccionando
 * la pantalla destino.
 * ej: super.linker.Destino()
 *
 * Lo he realizado de esta manera para que se infle el layout adecuado con la ID de adecuada
 * y el atributo cach� sea instancia a false la primera vez que se instancie el fragment.
 * En las siguientes instancias de linker se puede untilizar el par�metro cacheable como mejor
 * convenga.
 */
public abstract class MainAbstractFragment extends BaseFragment {

    protected Linker linker = new Linker(getFragmentManager(), false);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        onMainLinkFragment();

        return view;
    }

    public abstract void onMainLinkFragment();
}
