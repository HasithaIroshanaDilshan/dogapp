package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hishan Indrajith on 11/23/2017.
 */
public class ContributeFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton mapbtn;
    ImageButton gpsbtn;
    Button nextbtn;
    static Context context;
    static Dog dog;

    public static ContributeFragment newInstance(Context cntx){
        context=cntx;
        dog = new Dog();
        ContributeFragment fragment = new ContributeFragment();
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contribute,container,false);


        nextbtn = (Button) rootView.findViewById(R.id.next1btn);
        nextbtn.setEnabled(false);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext().getApplicationContext(), AddPhoto.class);
                startActivity(in);
                MainPage.getActivity().finish();
            }
        });

        mapbtn = (ImageButton)rootView.findViewById(R.id.mapbtn);
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext().getApplicationContext(),MapsActivity.class);
                startActivity(in);
            }
        });

        gpsbtn = (ImageButton)rootView.findViewById(R.id.gpsbtn);
        gpsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView locationtxt = (TextView) rootView.findViewById(R.id.location);
                double latitude=0.0;
                double longitude=0.0;
                GPSTracker2 gps = new GPSTracker2(context);
                if(!gps.canGetLocation()){
                    gps.showSettingsAlert();
                }else{
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    locationtxt.setText(latitude + " , " + longitude);
                    dog.setLocationLatitude(latitude);
                    dog.setLocationLongitude(longitude);
                    nextbtn.setEnabled(true);
                    Toast.makeText(context.getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainPage) activity).onSectionAttached(1);
    }

    public ContributeFragment() {
    }

}
