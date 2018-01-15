package lk.ac.pdn.ce.dogapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class Suggesions extends ActionBarActivity {
    int no_of_suggesions;
    String suggesions[];
    static int currentId;
    static String id_of_saved;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggesions);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        currentId=0;
        Intent loginIntent = getIntent();
        String [] suggesionsAr= loginIntent.getExtras().getStringArray("suggesions");
        id_of_saved=suggesionsAr[0];
        no_of_suggesions=Integer.parseInt(suggesionsAr[1]);
        suggesions=suggesionsAr[2].split(",");
        final TextView txtView = (TextView)findViewById(R.id.id);
        final String type ="getImage";
        txtView.setText(""+(currentId+1) +" of "+no_of_suggesions );
        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, "" + suggesions[currentId]);
        progressBar.setProgress((int)((1.0 / (double) no_of_suggesions) * 100));

        Button nobtn=(Button)findViewById(R.id.nobtn);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suggesions.currentId++;
                if (currentId < no_of_suggesions) {
                    txtView.setText("" + (currentId + 1) + " of " + no_of_suggesions);
                    BackgroundWorker newbackgroundWorker = new BackgroundWorker(Suggesions.this);
                    newbackgroundWorker.execute(type, "" + suggesions[currentId]);
                    progressBar.setProgress((int)(((double)(currentId+1) / (double) no_of_suggesions) * 100));
                } else {
                    Intent in = new Intent(getApplicationContext(), ThankActivity.class);
                    startActivity(in);
                    String msg = "SUCCESSFUL!!.Dog saved as a new Dog";
                    Toast.makeText(Suggesions.this, msg, Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
        Button yesbtn=(Button)findViewById(R.id.yesbtn);
        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type2 ="yes";
                BackgroundWorker newbackgroundWorker = new BackgroundWorker(Suggesions.this);
                newbackgroundWorker.execute(type2, "" + suggesions[currentId],id_of_saved);
            }
        });

        Button maybebtn=(Button)findViewById(R.id.maybebtn);
        maybebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type3 ="maybe";
                BackgroundWorker newbackgroundWorker = new BackgroundWorker(Suggesions.this);
                newbackgroundWorker.execute(type3, "" + suggesions[currentId],id_of_saved);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggesions, menu);
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
