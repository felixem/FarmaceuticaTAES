/*package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toolbar;

import farmaceutica.taes.farmaceutica.R;
import farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.IniciarAsyncDB;*/

/**
 * Created by Jesus on 14/05/2015.
 */


/*public class DetallesVisita extends ActionBarActivity
{
    public static final String TAG = "DetallesVisita";
    private FragmentPagerAdapter fpa;

    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_detalles_visita);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        ViewPager vp = (ViewPager) findViewById(R.id.vp_main);
        fpa = new MainPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(fpa);

        IniciarAsyncDB.iniciarDB(this);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

       private String productosPromcionados;
       private String productosOfertados;

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
                    fragment = VisitasFragment.newInstance();
                    break;
            }
            return fragment;
        }
    }
}*/
