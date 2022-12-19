package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FoodTracker extends AppCompatActivity {

    String [] breakfast, lunch, dinner, snack;
    String destination, user_id, picked_date;
    SharedPreferences shared;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String first_param = params[0];
            String second_param = params[1];

            URL url;
            HttpURLConnection http;

            try{
                url = new URL(params[2]);
                http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream out_stream = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out_stream, "UTF-8"));
                String post1 = URLEncoder.encode("user_id", "UTF-8")+"="+ URLEncoder.encode(first_param, "UTF-8")+"&"+URLEncoder.encode("date", "UTF-8")+"="+ URLEncoder.encode(second_param, "UTF-8");
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
        protected void onPostExecute(String result){
            Intent intentt = new Intent(FoodTracker.this, TrackYourFood.class);
            super.onPostExecute(result);
            if(result.equals("No data")){
                Toast.makeText(getApplicationContext(),"No data on this day", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONArray array = new JSONArray(result);
                    ArrayList<Object> list = new ArrayList<>();
                    JSONObject obj;

                    for (int i = 0; i < array.length(); i ++){
                        list.add(array.get(i));
                    }
                    breakfast = new String[array.length()];
                    lunch = new String[array.length()];
                    dinner = new String[array.length()];
                    snack = new String[array.length()];
                    obj = (JSONObject) array.get(0);
                    breakfast[0] = obj.getString("breakfast");
                    lunch[0] = obj.getString("lunch");
                    dinner[0] = obj.getString("dinner");
                    snack[0] = obj.getString("snack");
                    intentt.putExtra("breakfast", breakfast[0]);
                    intentt.putExtra("lunch", lunch[0]);
                    intentt.putExtra("dinner", dinner[0]);
                    intentt.putExtra("snack", snack[0]);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            intentt.putExtra("destination", destination);
            startActivity(intentt);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tracker);
        getSupportActionBar().hide();
    }
    public void goHome(View v){
        Intent intent = new Intent(FoodTracker.this, HomeActivity.class);
        startActivity(intent);
    }
    public void goWater(View v){
        Intent intent = new Intent(FoodTracker.this, WaterTracker.class);
        startActivity(intent);
    }
    public void goSport(View v){
        Intent intent = new Intent(FoodTracker.this, SportTracker.class);
        startActivity(intent);
    }
    public void goUser(View v){
        Intent intent = new Intent(FoodTracker.this, UserProfile.class);
        startActivity(intent);
    }
    public void goFood(View v){
        destination = v.getTag().toString();
        shared = getSharedPreferences("com.lau.finalproject", Context.MODE_PRIVATE);
        user_id = shared.getString("id", "");
        picked_date = shared.getString("chosen_date", "");
        String url = "http://10.31.195.219/Final/Backend/track_your_food.php";
        DownloadTask task = new DownloadTask();
        task.execute(user_id, picked_date, url);
    }
}
