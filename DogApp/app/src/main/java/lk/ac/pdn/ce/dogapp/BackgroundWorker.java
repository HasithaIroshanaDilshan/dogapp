package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

    public BackgroundWorker(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url ="http://192.168.43.12/dogapp/login.php";
        String sign_up_url ="http://192.168.43.12/dogapp/signup.php";
        String suggesion_url ="http://192.168.43.12/dogapp/getsuggesion.php";
        String getImage_url ="http://192.168.43.12/dogapp/getimage.php";

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
        }else if(type.equals("suggestion")){
            String latitude=params[1];
            String longitude=params[2];
            String photo=params[3];
            String colorCode=params[4];
            String size=params[5];
            String type=params[6];
            String date=params[7];
            try {
                String post_data = URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(latitude,"UTF-8")+
                        "&"+URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(longitude,"UTF-8")+
                        "&"+URLEncoder.encode("photo","UTF-8")+"="+URLEncoder.encode(photo,"UTF-8")+
                        "&"+URLEncoder.encode("colorCode","UTF-8")+"="+URLEncoder.encode(colorCode,"UTF-8")+
                        "&"+URLEncoder.encode("size","UTF-8")+"="+URLEncoder.encode(size,"UTF-8")+
                        "&"+URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8")+
                        "&"+URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");
                return ioFunction(suggesion_url,post_data);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                return "network problem";
            }
        }else if(type.equals("getImage")){
            String image_id = params[1];
            try{
                String post_data = URLEncoder.encode("image_id","UTF-8")+"="+URLEncoder.encode(image_id,"UTF-8");
                return ioFunction(getImage_url,post_data);
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
        TextView pwordtxt=(TextView)context.findViewById(R.id.pwordtxt);
        if(type.equals("login")){
            if(result==null){
                result="Problem in the Server";
            }else if(result.split(",")[0].equals("login success")){
                Toast.makeText(context,"login success...",Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), MainPage.class);
                String userData[]={username,result.split(",")[1]};
                in.putExtra("userData", userData);
                SharedPreferences sharedPreferences = context.getSharedPreferences("login",context.MODE_PRIVATE);
                sharedPreferences.edit().putString("uname",username).apply();
                sharedPreferences.edit().putString("id",userData[1]).apply();
                context.startActivity(in);
                context.finish();
            }else if(result.equals("login not success")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Access Denied!!");
                alertDialog.show();
                pwordtxt.setText("");
            }else if(result.equals("network problem")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Check your network connection");
                alertDialog.show();
                pwordtxt.setText("");
            }
        }else if(type.equals("sign_up")){
            if(result==null){
                result="Problem in the Server";
            }else if(result.equals("signup successful")){
                Toast.makeText(context,result,Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), Login.class);
                context.startActivity(in);
                context.finish();
            }else if(result.equals("user_already")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("SignUp Status");
                alertDialog.setMessage("Cannot use password. Please use another one.");
                alertDialog.show();
            }else if(result.equals("signup not successful")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("SignUp Status");
                alertDialog.setMessage(result);
                alertDialog.show();
            }else if(result.equals("network problem")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("SignUp Status");
                alertDialog.setMessage("Check your network connection");
                alertDialog.show();
            }
        }else if(type.equals("suggestion")){
            if(result==null){
                result="Problem in the Server";
            }else if(result.equals("0")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Dog Add Status");
                alertDialog.setMessage("dog add not successful");
                alertDialog.show();
            }else if(result.equals("network problem")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Dog Add Status");
                alertDialog.setMessage("Check your network connection");
                alertDialog.show();
            }else{
                String no_of_suggesions = result.split("-")[0];
                String suggested_ids = result.split("-")[1];
                Intent in = new Intent(context.getApplicationContext(), Suggesions.class);
                String suggesions[]={no_of_suggesions,suggested_ids};
                in.putExtra("suggesions", suggesions);
                context.startActivity(in);
                context.finish();
            }
        }else if(type.equals("getImage")){
            if(result==null){
                result="Problem in the Server";
            }else{
                ImageView image= (ImageView)context.findViewById(R.id.suggestedimage);
                byte[] decodedString= Base64.decode(result, Base64.DEFAULT);
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
                image.setImageBitmap(imageBitmap);
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
