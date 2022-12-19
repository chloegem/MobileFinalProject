package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TrackYourFood extends AppCompatActivity {

    TextView food_header;
    EditText food_info;
    String header_to_display, user_id, picked_date, info_to_add;
    SharedPreferences shared;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String first_param = params[0];
            String second_param = params[1];
            String third_param = params[2];

            URL url;
            HttpURLConnection http;

            try{
                url = new URL(params[3]);
                http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream out_stream = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out_stream, "UTF-8"));
                String post1 = URLEncoder.encode("user_id", "UTF-8")+"="+ URLEncoder.encode(first_param, "UTF-8")+"&"+URLEncoder.encode("date", "UTF-8")+"="+ URLEncoder.encode(second_param, "UTF-8")+"&"+URLEncoder.encode("breakfast", "UTF-8")+"="+ URLEncoder.encode(third_param, "UTF-8");
                String post2 = URLEncoder.encode("user_id", "UTF-8")+"="+ URLEncoder.encode(first_param, "UTF-8")+"&"+URLEncoder.encode("date", "UTF-8")+"="+ URLEncoder.encode(second_param, "UTF-8")+"&"+URLEncoder.encode("lunch", "UTF-8")+"="+ URLEncoder.encode(third_param, "UTF-8");
                String post3 = URLEncoder.encode("user_id", "UTF-8")+"="+ URLEncoder.encode(first_param, "UTF-8")+"&"+URLEncoder.encode("date", "UTF-8")+"="+ URLEncoder.encode(second_param, "UTF-8")+"&"+URLEncoder.encode("dinner", "UTF-8")+"="+ URLEncoder.encode(third_param, "UTF-8");
                String post4 = URLEncoder.encode("user_id", "UTF-8")+"="+ URLEncoder.encode(first_param, "UTF-8")+"&"+URLEncoder.encode("date", "UTF-8")+"="+ URLEncoder.encode(second_param, "UTF-8")+"&"+URLEncoder.encode("snack", "UTF-8")+"="+ URLEncoder.encode(third_param, "UTF-8");

                if (header_to_display.equalsIgnoreCase("breakfast")){
                    bw.write(post1);
                }
                else if (header_to_display.equalsIgnoreCase("lunch")){
                    bw.write(post2);
                }
                else if (header_to_display.equalsIgnoreCase("dinner")){
                    bw.write(post3);
                }
                else if (header_to_display.equalsIgnoreCase("snacks")){
                    bw.write(post4);
                }
                bw.flush();
                bw.close();
                out_stream.close();

                // Reading the result from the API
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
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_your_food);
        getSupportActionBar().hide();
        food_header = (TextView) findViewById(R.id.header_food);
        food_info = (EditText) findViewById(R.id.food_info);
        Intent intent = getIntent();
        header_to_display = intent.getStringExtra("destination");
        String breakfast = intent.getStringExtra("breakfast");
        String lunch = intent.getStringExtra("lunch");
        String dinner = intent.getStringExtra("dinner");
        String snack = intent.getStringExtra("snack");

        food_header.setText(header_to_display);

        if(breakfast != "" && header_to_display.equalsIgnoreCase("breakfast")){
            food_info.setText(breakfast);
        }
        else if(lunch != "" && header_to_display.equalsIgnoreCase("lunch")){
            food_info.setText(lunch);
        }
        else if(dinner != "" && header_to_display.equalsIgnoreCase("dinner")){
            food_info.setText(dinner);
        }
        else if(snack != "" && header_to_display.equalsIgnoreCase("snacks")){
            food_info.setText(snack);
        }
    }
    public void goHome(View v){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
    public void goWater(View v){
        Intent intent = new Intent(TrackYourFood.this, WaterTracker.class);
        startActivity(intent);
    }
    public void goFood(View v){
        Intent intent = new Intent(TrackYourFood.this, TrackYourFood.class);
        startActivity(intent);
    }
    public void goSport(View v){
        Intent intent = new Intent(TrackYourFood.this, TrackYourSport.class);
        startActivity(intent);
    }

    public void goUser(View v){
        Intent intent = new Intent(TrackYourFood.this, UserProfile.class);
        startActivity(intent);
    }

    public void addInfo(View v){
        shared = getSharedPreferences("com.lau.finalproject", Context.MODE_PRIVATE);
        user_id = shared.getString("id", "");
        picked_date = shared.getString("chosen_date", "");
        info_to_add = food_info.getText().toString();
        String url = "http://10.31.195.219/Final/Backend/add_to_food.php";
        DownloadTask task = new DownloadTask();
        task.execute(user_id, picked_date, info_to_add, url);
    }
}