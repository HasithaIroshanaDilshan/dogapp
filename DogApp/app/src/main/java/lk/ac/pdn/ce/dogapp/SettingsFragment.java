package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hishan Indrajith on 11/23/2017.
 */
public class SettingsFragment extends android.support.v4.app.Fragment {
    public static SettingsFragment newInstance(){
        SettingsFragment fragment = new SettingsFragment();
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings,container,false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainPage) activity).onSectionAttached(3);
    }

    public SettingsFragment() {

    }


}
