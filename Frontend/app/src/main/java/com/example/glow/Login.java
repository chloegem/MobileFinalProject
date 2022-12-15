package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button btn;
    TextView text;
    String[] user_id;
    SharedPreferences shared;


    public class DownloadTask extends AsyncTask<String, Void, String>{

        protected String doInBackground(String... params) {
            String first_param = params[0];
            String second_param = params[1];

            URL url;
            HttpURLConnection http;
            try {
                url = new URL(params[2]);

                http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }
}