package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SignUp extends ActionBarActivity {

    Activity thisContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        thisContext=this;
        Button signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView unametxt=(TextView)findViewById(R.id.usernametxt);
                TextView emailtxt=(TextView)findViewById(R.id.emailtxt);
                TextView pword1txt=(TextView)findViewById(R.id.pword1txt);
                TextView pword2txt=(TextView)findViewById(R.id.pword2txt);
                String uname =unametxt.getText().toString().trim();
                String email =emailtxt.getText().toString().trim();
                String pword1 =pword1txt.getText().toString().trim();
                String pword2 =pword2txt.getText().toString().trim();
                if(uname.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                    alertDialog.setTitle("User Name");
                    alertDialog.setMessage("Must add a valid User Name");
                    alertDialog.show();
                }else if(email.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                    alertDialog.setTitle("Email");
                    alertDialog.setMessage("Must add a valid Email");
                    alertDialog.show();
                }else if(pword1.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                    alertDialog.setTitle("Password");
                    alertDialog.setMessage("Password cannot be empty");
                    alertDialog.show();
                }else if(!pword1.equals(pword2)){
                    AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                    alertDialog.setTitle("Password");
                    alertDialog.setMessage("Passwords doesn't match");
                    alertDialog.show();
                }else{
                    String type= "sign_up";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(thisContext);
                    backgroundWorker.execute(type,uname,email,pword1);
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
