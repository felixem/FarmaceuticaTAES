package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import farmaceutica.taes.farmaceutica.presentacion.controlador.MainAbstractDynamicFragment;

/**
 * Created by John on 15/05/2015.
 */
public class MainVisitasDynamicFragment extends MainAbstractDynamicFragment {

    @Override
    public void onMainLinkFragment() {
        super.linker.MisVisitas();    }

    public static MainVisitasDynamicFragment newInstance() {

        // Instantiate a new fragment
        MainVisitasDynamicFragment fragment = new MainVisitasDynamicFragment();

        return fragment;

    }
}
