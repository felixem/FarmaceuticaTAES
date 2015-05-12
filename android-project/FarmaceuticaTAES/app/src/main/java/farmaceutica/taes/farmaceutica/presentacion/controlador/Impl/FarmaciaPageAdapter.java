package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.FarmaciaFragment;

public class FarmaciaPageAdapter extends FragmentPagerAdapter {

    public FarmaciaPageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FarmaciaFragment();
        Bundle args = new Bundle();
        args.putString(FarmaciaFragment.ARG_SECTION_NAME, getPageTitle(position).toString());
        //args.putInt(FarmaciaFragment.ARG_);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Productos";
            case 1: return "Médicos";
            case 2: return "Visitas";
            case 3: return "Rutas";
            case 4: return "Gastos";
            default: return "";

        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}