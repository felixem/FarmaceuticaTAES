package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import farmaceutica.taes.farmaceutica.presentacion.controlador.MainAbstractFragment;

/**
 * Created by John on 15/05/2015.
 */
public class MainGastosFragment extends MainAbstractFragment{

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
