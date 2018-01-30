package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
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
       // nextbtn.setEnabled(false);
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
                final TextView locationtxt = (TextView) rootView.findViewById(R.id.location);
                final double[] latitude = {0.0};
                final double[] longitude = {0.0};
                final Location[] previousLocation = new Location[1];
                final boolean[] isFirstFix = {true};
                final int[] noOfFixes = {0};
                final LocationManager locationManager =(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    showSettingsAlert();
                }else{
                    locationtxt.setText("Please wait until Precise GPS coordinates are obtained...");
                    dog.setLocationLatitude(0);
                    dog.setLocationLongitude(0);
                    final LocationListener locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            if(isFirstFix[0] || location.getAccuracy()< previousLocation[0].getAccuracy()){
                                isFirstFix[0] = false;
                                previousLocation[0] = location;
                                locationtxt.setText("Location"+ noOfFixes[0] +" -> Accuracy: " + location.getAccuracy()+" metres. Waiting for a better accuracy");
                                dog.setLocationLatitude(location.getLatitude());
                                dog.setLocationLongitude(location.getLongitude());
                            }
                            noOfFixes[0]++;
                            if(noOfFixes[0]>15){
                                // Remove the listener ryou previously added
                                locationManager.removeUpdates(this);
                                locationtxt.setText("Location Obtained with accuracy" + location.getAccuracy()+" metres. NOW YOU CAN CONTINUE...");
                                nextbtn.setEnabled(true);
                            }

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {}

                        @Override
                        public void onProviderEnabled(String provider) {}

                        @Override
                        public void onProviderDisabled(String provider) {}

                    };
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }

            }
        });

        return rootView;
    }

    //alert when gps not enabled
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainPage) activity).onSectionAttached(1);
    }

    public ContributeFragment() {
    }

}
