package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hishan Indrajith on 11/23/2017.
 */
public class ContributeFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    View rootView;
    Button mapbtn;

    public static ContributeFragment newInstance(){
        ContributeFragment fragment = new ContributeFragment();
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contribute,container,false);
        mapbtn = (Button)rootView.findViewById(R.id.mapbtn);
        mapbtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainPage) activity).onSectionAttached(1);
    }

    public ContributeFragment() {
    }


    @Override
    public void onClick(View v) {
        Intent in = new Intent(v.getContext().getApplicationContext(),MapsActivity.class);
        startActivity(in);
    }
}
