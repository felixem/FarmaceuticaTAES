package farmaceutica.taes.farmaceutica.presentacion.controlador;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.BaseFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.Linker;

/**
 * Created by John on 15/05/2015.
 *
 * Cuando se herede de MainAbstractFragment debe implementarse la funcion
 * onMainLinkFragment() en la que solo se indicara en la fragment principal que
 * se cargara en esa instancia.
 *
 * Para ello se recomienda implentar la funcionMainLinkFragment llamando
 * al atributo protected linker llamando a la funcion super y seleccionando
 * la pantalla destino.
 * ej: super.linker.Destino()
 *
 * Lo he realizado de esta manera para que se infle el layout adecuado con la ID de adecuada
 * y el atributo cache sea instancia a false la primera vez que se instancie el fragment.
 * En las siguientes instancias de linker se puede untilizar el parametro cacheable como mejor
 * convenga.
 */
public abstract class MainAbstractDynamicFragment extends BaseFragment {

    protected Linker linker;
    private FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        linker = new Linker(getFragmentManager(), false);

        onMainLinkFragment();



        return view;
    }



    public abstract void onMainLinkFragment();
}
