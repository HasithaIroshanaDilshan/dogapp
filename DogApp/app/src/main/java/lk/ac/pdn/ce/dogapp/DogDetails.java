package lk.ac.pdn.ce.dogapp;

import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;


public class DogDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);

        final Spinner spSize = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.size,R.layout.my_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSize.setAdapter(adapter1);

        final Spinner spStray = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.stray,R.layout.my_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStray.setAdapter(adapter2);

        final RadioButton []radioColor = {(RadioButton)findViewById(R.id.radioButton),(RadioButton)findViewById(R.id.radioButton2),(RadioButton)findViewById(R.id.radioButton3),(RadioButton)findViewById(R.id.radioButton4)};
        for(int i=0;i<4;i++) {
            final int finalI = i;
            radioColor[i].setOnClickListener(new View.OnClickListener() {
                boolean reallyChecked = false;
                @Override
                public void onClick(View v) {
                    if (reallyChecked) {
                        radioColor[finalI].setChecked(false);
                        reallyChecked = false;
                    } else {
                        reallyChecked = true;
                    }
                }
            });
        }

        Button nextbtn = (Button)findViewById(R.id.next3btn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContributeFragment.dog.setColorCode((radioColor[0].isChecked()?"W":"")+(radioColor[1].isChecked()?"R":"")+(radioColor[2].isChecked()?"G":"")+(radioColor[3].isChecked()?"B":""));
                ContributeFragment.dog.setSize(spSize.getSelectedItem().toString());
                ContributeFragment.dog.setType(spStray.getSelectedItem().toString());
                String type= "suggestion";
                BackgroundWorker backgroundWorker = new BackgroundWorker(DogDetails.this);
                backgroundWorker.execute(type,""+ContributeFragment.dog.getLocationLatitude(),""+ ContributeFragment.dog.getLocationLongitude(), ContributeFragment.dog.getMainLocalPhoto(), ContributeFragment.dog.getColorCode(),ContributeFragment.dog.getSize(),ContributeFragment.dog.getType(),ContributeFragment.dog.getDateTime());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dog_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
