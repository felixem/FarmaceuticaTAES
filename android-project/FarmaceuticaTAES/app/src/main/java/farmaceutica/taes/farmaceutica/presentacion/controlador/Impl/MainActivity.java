package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewAnimator;

import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.IniciarAsyncDB;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        ViewPager vp = (ViewPager) findViewById(R.id.vp_main);
        fpa = new MainPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(fpa);

        IniciarAsyncDB.iniciarDB(this);
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

        private String[] titles = {"Productos", "Clientes", "Valoracion", "Rutas", "Médicos"};

        public MainPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return 5;
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
                    fragment = EjemploFragment1.newInstance();
                    break;
                case 1:
                    fragment = EjemploFragment2.newInstance();
                    break;
                case 2:
                    fragment = EjemploFragment3.newInstance();
                    break;
                case 3:
                    fragment = EjemploFragment4.newInstance();
                    break;
                case 4:
                    fragment = EjemploFragment5.newInstance();
                    break;
            }
            return fragment;
        }
    }
}
