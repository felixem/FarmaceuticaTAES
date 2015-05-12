package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import farmaceutica.taes.farmaceutica.R;

/**
 * Created by John on 07/05/2015.
 */
public class FarmaciaFragment extends Fragment {
    public static final String ARG_SECTION_NAME = "ARG_SECTION_NAME";
    private ViewPager vp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        Bundle b = getArguments();

        TextView txt = (TextView) v.findViewById(R.id.txt_main_fragment);
        txt.setText(b.getString(ARG_SECTION_NAME));

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        vp = (ViewPager)view.findViewById(R.id.vp_main);
        vp.setAdapter(new FarmaciaPageAdapter());
    }
}
