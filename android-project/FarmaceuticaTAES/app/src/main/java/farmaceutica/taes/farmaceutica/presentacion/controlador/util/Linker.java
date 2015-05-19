package farmaceutica.taes.farmaceutica.presentacion.controlador.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.CrearGastosFragment;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.MisGastosFragment;

/**
 * Created by John on 15/05/2015.
 *
 * Para poder cambiar un fragment el layout debe ser inflado en fragment_dynamic
 */
public class Linker {
    private FragmentTransaction transaction;
    private int KDYNAMICZONE = R.id.fragment_dynamic;

    public static String KDAYMONTH = "KDAYMONTH";
    public static String KMONTH = "KMONTH";
    public static String KYEAR = "KYEAR";

    public Linker(FragmentManager fm ,boolean cacheable){
        transaction = fm.beginTransaction();
        //transaction.replace(R.id.fragment_dynamic, );
        if(cacheable) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(null);
        }
    }

    public void CrearGasto(){
        Fragment fragment = new CrearGastosFragment();
        transaction.replace(KDYNAMICZONE, fragment);
        transaction.commit();
    }

    public void MisGastos(){
        transaction.replace(KDYNAMICZONE, new MisGastosFragment());
        transaction.commit();
    }

    public void ListaVisitas(){
        transaction.replace(KDYNAMICZONE, new ListaVisitas());
        transaction.commit();
    }
/*
    public void VerDetalles(int idVisita){
        Bundle bundle = new Bundle();
        bundle.putInt(Visita.ID, idVisita);
        Activity fragment = new VerDetalles;
        fragment.setArguments(bundle);

        transaction.replace(KDYNAMICZONE, fragment);
        transaction.commit();
    }
    */
}
