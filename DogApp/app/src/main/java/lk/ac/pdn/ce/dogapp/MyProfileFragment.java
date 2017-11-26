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
public class MyProfileFragment extends android.support.v4.app.Fragment {
    public static MyProfileFragment newInstance(){
        MyProfileFragment fragment = new MyProfileFragment();
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_profile,container,false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainPage) activity).onSectionAttached(2);
    }

    public MyProfileFragment() {

    }


}
