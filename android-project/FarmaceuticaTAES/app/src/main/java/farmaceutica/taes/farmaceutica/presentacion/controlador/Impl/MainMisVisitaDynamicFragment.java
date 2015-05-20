package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import farmaceutica.taes.farmaceutica.presentacion.controlador.MainAbstractDynamicFragment;

/**
 * Created by John on 15/05/2015.
 */
public class MainMisVisitaDynamicFragment extends MainAbstractDynamicFragment {


    @Override
    public void onMainLinkFragment() {
        super.linker.MisVisitas();    }

    public static MainMisVisitaDynamicFragment newInstance() {
        // Instantiate a new fragment
        MainMisVisitaDynamicFragment fragment = new MainMisVisitaDynamicFragment();

        return fragment;

    }
}
