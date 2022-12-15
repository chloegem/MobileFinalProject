package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends AppCompatActivity {

    EditText fname, lname, email,dob, gender, height,weight,pass;

    protected String doInBackground(String... params) {
        String first_name = params[0];
        String last_name= params[1];
        String email= params[2];
        String date_of_birth= params[3];
        String gender= params[4];
        String password= params[5];
        String height= params[6];
        String weight= params[7];

        URL url;
        HttpURLConnection http;

        try{
            url = new URL(params[8]);
            http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream out_stream = http.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out_stream, "UTF-8"));
            String post1 = URLEncoder.encode("first_name", "UTF-8")+"="+ URLEncoder.encode(first_name, "UTF-8")+"&"+URLEncoder.encode("last_name", "UTF-8")+"="+ URLEncoder.encode(last_name, "UTF-8")+"&"+URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(email, "UTF-8")+"&"+URLEncoder.encode("date_of_birth", "UTF-8")+"="+ URLEncoder.encode(date_of_birth, "UTF-8")+"&"+URLEncoder.encode("gender", "UTF-8")+"="+ URLEncoder.encode(gender, "UTF-8")+"&"+URLEncoder.encode("password", "UTF-8")+"="+ URLEncoder.encode(password, "UTF-8")+"&"+URLEncoder.encode("height", "UTF-8")+"="+ URLEncoder.encode(height, "UTF-8")+"&"+URLEncoder.encode("weight", "UTF-8")+"="+ URLEncoder.encode(weight, "UTF-8");
            bw.write(post1);
            bw.flush();
            bw.close();
            out_stream.close();
            InputStream in_stream = http.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in_stream, "iso-8859-1"));
            String result = "";
            String line = "";
            while((line = br.readLine())!= null){
                result += line;
            }
            br.close();
            in_stream.close();
            http.disconnect();
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}