package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import farmaceutica.taes.farmaceutica.presentacion.controlador.MainAbstractDynamicFragment;

/**
 * Created by John on 15/05/2015.
 */
public class MainClienteDynamicFragment extends MainAbstractDynamicFragment {

    @Override
    public void onMainLinkFragment() {
        super.linker.VerCliente();    }

    public static MainClienteDynamicFragment newInstance() {

        // Instantiate a new fragment
        MainClienteDynamicFragment fragment = new MainClienteDynamicFragment();

        return fragment;

    }
}
