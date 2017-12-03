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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contribute,container,false);
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
                GPSTracker gps = new GPSTracker(context);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    TextView locationtxt= (TextView)rootView.findViewById(R.id.location);
                    locationtxt.setText(latitude+" , "+longitude);
                    dog.setLocationLatitude(latitude);
                    dog.setLocationLongitude(longitude);
                    Toast.makeText(context.getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });
        nextbtn = (Button)rootView.findViewById(R.id.next1btn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext().getApplicationContext(), AddPhoto.class);
                startActivity(in);
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
