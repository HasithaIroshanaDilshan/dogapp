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
import android.widget.Toast;


public class Suggesions extends ActionBarActivity {
    int no_of_suggesions;
    String suggesions[];
    static int currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggesions);

        currentId=0;
        Intent loginIntent = getIntent();
        String [] suggesionsAr= loginIntent.getExtras().getStringArray("suggesions");
        no_of_suggesions=Integer.parseInt(suggesionsAr[0]);
        suggesions=suggesionsAr[1].split(",");
        final String type ="getImage";
        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, "" + suggesions[currentId]);

        Button nobtn=(Button)findViewById(R.id.nobtn);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suggesions.currentId++;
                if(currentId<no_of_suggesions){
                    BackgroundWorker newbackgroundWorker = new BackgroundWorker(Suggesions.this);
                    newbackgroundWorker.execute(type, "" + suggesions[currentId]);
                }else{
                    String msg="SUCCESSFUL!!.Dog saved as a new Dog";
                    Toast.makeText(Suggesions.this, msg, Toast.LENGTH_LONG).show();
                    startActivity(MainPage.getMainIntent());
                    Suggesions.this.finish();
                }

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
