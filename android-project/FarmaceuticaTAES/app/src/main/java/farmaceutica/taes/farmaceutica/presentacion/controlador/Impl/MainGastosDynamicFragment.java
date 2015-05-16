package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import farmaceutica.taes.farmaceutica.presentacion.controlador.MainAbstractDynamicFragment;

/**
 * Created by John on 15/05/2015.
 */
public class MainGastosDynamicFragment extends MainAbstractDynamicFragment {

    @Override
    public void onMainLinkFragment() {
        super.linker.MisGastos();
    }

    public static MisGastosFragment newInstance() {

        // Instantiate a new fragment
        MisGastosFragment fragment = new MisGastosFragment();

        return fragment;

    }
}
