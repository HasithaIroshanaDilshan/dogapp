package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Hishan Indrajith on 12/2/2017.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Activity context;
    String username;
    String type;
    AlertDialog alertDialog;

    public BackgroundWorker(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url ="http://192.168.43.12/dogapp/login.php";
        String sign_up_url ="http://192.168.43.12/dogapp/signup.php";
        if(type.equals("login")){
            username = params[1];
            String password = params[2];
            try {
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                return ioFunction(login_url,post_data);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                return "network problem";
            }
        }else if(type.equals("sign_up")){
            String signup_username=params[1];
            String signup_email=params[2];
            String signup_password=params[3];
            try {
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(signup_username,"UTF-8")+"&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(signup_email,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(signup_password,"UTF-8");
                return ioFunction(sign_up_url,post_data);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                return "network problem";
            }
        }
        return null;
    }

    private static String ioFunction(String stringUrl,String post_data)throws MalformedURLException,IOException{
        URL url = new URL(stringUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        bufferedWriter.write(post_data);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
        String result="";
        String line="";
        while((line=bufferedReader.readLine())!=null){
            result+=line;
        }
        inputStream.close();
        bufferedReader.close();
        httpURLConnection.disconnect();
        return result;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        if(type.equals("login")){
            if(result==null){
                result="Problem in the Server";
            }else if(result.equals("login success")){
                Toast.makeText(context,result,Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), MainPage.class);
                in.putExtra("uname", username);
                context.startActivity(in);
                context.finish();
            }else if(result.equals("login not success")){
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage(result);
                alertDialog.show();
            }else if(result.equals("network problem")){
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Check your network connection");
                alertDialog.show();
            }
        }else if(type.equals("sign_up")){
            if(result==null){
                result="Problem in the Server";
            }else if(result.equals("signup successful")){
                Toast.makeText(context,result,Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), Login.class);
                context.startActivity(in);
            }else if(result.equals("signup not successful")){
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage(result);
                alertDialog.show();
            }else if(result.equals("network problem")){
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Check your network connection");
                alertDialog.show();
            }
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
