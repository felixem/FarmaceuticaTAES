package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;

import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.farmaceutica.R;

/**
 * Created by john on 7/05/15.
 */
public class MainActivity extends ActionBarActivity{

    ActionBar ab;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();
        //ab.setNavigationMode();

       DatabaseManager dataManager = new DatabaseManager();
        dataManager.getHelper(this).getWritableDatabase();
        // Set an OnMenuItemClickListener to handle menu item clicks
    }


}
