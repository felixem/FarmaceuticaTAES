package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import farmaceutica.taes.farmaceutica.presentacion.controlador.MainAbstractDynamicFragment;

/**
 * Created by Javi on 19/05/2015.
 */
public class MainListaVisitas extends MainAbstractDynamicFragment {
    @Override
    public void onMainLinkFragment() {
        super.linker.ListaVisitas();
    }

    public static MainListaVisitas newInstance() {

        // Instantiate a new fragment
        MainListaVisitas fragment = new MainListaVisitas();

        // Save the parameters
        //Bundle bundle = new Bundle();
        //bundle.putInt(BACKGROUND_COLOR, color);
        //bundle.putInt(INDEX, index);
        //fragment.setArguments(bundle);
        //fragment.setRetainInstance(true);

        return fragment;

    }
}
