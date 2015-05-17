package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.util.IniciarAsyncDB;

/**
 * Created by john on 7/05/15.
 */
public class MainActivity extends ActionBarActivity {

    public static final String TAG = "MainActivity";
    private FragmentPagerAdapter fpa;

    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        IniciarAsyncDB.iniciarDB(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        ViewPager vp = (ViewPager) findViewById(R.id.vp_main);
        fpa = new MainPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(fpa);
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

        private String[] titles = {"Productos", "Clientes", "Valoracion", "Rutas", "Mis Gastos", "Realizar Foto", "Registrar Visita"};

        public MainPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
                Fragment fragment = null;
            switch (position) {
                default:
                case 0:
                    fragment = ProductosFragment.newInstance();
                    break;
                case 1:
                    fragment = ClientesFragment.newInstance();
                    break;
                case 2:
                    fragment = Ejemplo3Fragment.newInstance();
                    break;
                case 3:
                    fragment = ListaRutasFragment.newInstance();
                    break;
                case 4:
                    fragment =  MainGastosDynamicFragment.newInstance();
                    break;
                case 5:
                    fragment = RealizarFotoFragment.newInstance();
                    break;
                case 6:
                    fragment = RegistrarVisitaFragment.newInstance();
                    break;
            }

            return fragment;
        }
    }
}
