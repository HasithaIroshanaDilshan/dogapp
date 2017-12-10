package lk.ac.pdn.ce.dogapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.Settings;
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
    String login_id;
    String login_uname;

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
        String checklogin_url = "http://192.168.43.12/dogapp/checklogin.php";
        String logout_url = "http://192.168.43.12/dogapp/logout.php";
        String verify_url = "http://192.168.43.12/dogapp/verify.php";

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
        }else if(type.equals("log_out")) {
            String id = params[2];
            String uname = params[1];
            try {
                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8");
                return ioFunction(logout_url, post_data);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                return "network problem";
            }
        }else if(type.equals("check_login")) {
            login_id = params[1];
            login_uname = params[2];
            try {
                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(login_id, "UTF-8") + "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(login_uname, "UTF-8");
                return ioFunction(checklogin_url, post_data);
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
        }else if(type.equals("verify")) {
            String id=params[1];
            String verification_code=params[2];
            try {
                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+URLEncoder.encode("verification_code","UTF-8")+"="+URLEncoder.encode(verification_code,"UTF-8");
                return ioFunction(verify_url,post_data);
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
        if(type.equals("login")) {
            if (result == null) {
                result = "Problem in the Server";
            } else if (result.split(",")[0].equals("login success")) {
                Toast.makeText(context, "login success...", Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), MainPage.class);
                String userData[] = {username, result.split(",")[1]};
                in.putExtra("userData", userData);
                SharedPreferences sharedPreferences = context.getSharedPreferences("login", context.MODE_PRIVATE);
                sharedPreferences.edit().putString("uname", username).apply();
                sharedPreferences.edit().putString("id", userData[1]).apply();
                context.startActivity(in);
                context.finish();
            } else if (result.equals("login not success")) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Access Denied!!");
                alertDialog.show();
                pwordtxt.setText("");
            } else if (result.equals("network problem")) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Check your network connection");
                alertDialog.show();
                pwordtxt.setText("");
            }else if (result.equals("error")) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Error making logged in status");
                alertDialog.show();
                pwordtxt.setText("");
            }
        }else if(type.equals("log_out")){
            if(result==null){
                result="Problem in the Server";
            }
            else if(result.equals("ok")){
                Toast.makeText(context,"Successfully Logged Out",Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), Login.class);
                context.startActivity(in);
                context.finish();
            }else if(result.equals("error")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Logout Status");
                alertDialog.setMessage("Log Out Not Successfull");
                alertDialog.show();
            }else if (result.equals("network problem")) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Check your network connection");
                alertDialog.show();
                pwordtxt.setText("");
            }

        }else if(type.equals("check_login")){
            if(result==null){
                result="Problem in the Server";
            }
            else if(result.equals("logged")){
                Intent in = new Intent(context.getApplicationContext(), MainPage.class);
                String userData[]={login_uname,""+login_id};
                in.putExtra("userData",userData);
                context.startActivity(in);
                context.finish();
            }else if(result.equals("not_logged")){
                Intent in = new Intent(context.getApplicationContext(), Login.class);
                context.startActivity(in);
                context.finish();
            }else if(result.equals("network problem")) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Network");
                alertDialog.setMessage("Check your network connection and try again");


                // on pressing ok
                alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                alertDialog.show();
            }

        }else if(type.equals("sign_up")){
            if(result==null){
                result="Problem in the Server";
            }else if(result.split(",")[0].equals("signup successful")){
                Toast.makeText(context,"Sign Up successful. Verify to complete.",Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), VerifySignUp.class);
                in.putExtra("id", result.split(",")[1]);
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
        }else if(type.equals("verify")) {
            if(result==null){
                result="Problem in the Server";
            }else if(result.equals("correct")){
                Toast.makeText(context,"Verified",Toast.LENGTH_LONG).show();
                Intent in = new Intent(context.getApplicationContext(), Login.class);
                context.startActivity(in);
                context.finish();
            }else if(result.equals("wrong")){
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("SignUp Status");
                alertDialog.setMessage("Wrong Verification Code");
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
