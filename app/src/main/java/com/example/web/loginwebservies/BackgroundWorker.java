package com.example.web.loginwebservies;

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
 * Created by salal on 10/28/2016.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker(Context ctx){
        this.context =ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url ="http://crave.pk/login/login.php";
        if (type.equals("login")){
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpsURLConnection =(HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream =httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String Post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(Post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                //input Data
                InputStream inputStream =httpsURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line="";
                while((line =bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        Toast.makeText(context,result, Toast.LENGTH_LONG).show();
        if(result.equals("login Sucess")){
            Intent intent = new Intent(context,UserWindows.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        alertDialog =new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }
}
