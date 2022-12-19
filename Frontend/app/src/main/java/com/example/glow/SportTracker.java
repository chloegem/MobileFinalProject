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

public class SportTracker extends AppCompatActivity {

    String[] running, dancing, boxing, baseball, basketball, football, swimming, skiing, hiking, gymnastics, tennis, golf;
    String user_id, picked_date, destination;
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
            Intent intent = new Intent(getApplicationContext(), TrackYourSport.class);
            super.onPostExecute(result);
            if(result.equals("No data")) {
                Toast.makeText(getApplicationContext(), "No data on this day", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONArray array = new JSONArray(result);
                    ArrayList<Object> list = new ArrayList<>();
                    JSONObject obj;

                    for (int i = 0; i < array.length(); i ++){
                        list.add(array.get(i));
                    }
                    running = new String[array.length()];
                    dancing = new String[array.length()];
                    boxing = new String[array.length()];
                    baseball = new String[array.length()];
                    basketball = new String[array.length()];
                    football = new String[array.length()];
                    swimming = new String[array.length()];
                    skiing = new String[array.length()];
                    hiking = new String[array.length()];
                    gymnastics = new String[array.length()];
                    tennis = new String[array.length()];
                    golf = new String[array.length()];
                    obj = (JSONObject) array.get(0);
                    running[0] = obj.getString("running");
                    dancing[0] = obj.getString("dancing");
                    boxing[0] = obj.getString("boxing");
                    baseball[0] = obj.getString("baseball");
                    basketball[0] = obj.getString("basketball");
                    football[0] = obj.getString("football");
                    swimming[0] = obj.getString("swimming");
                    skiing[0] = obj.getString("skiing");
                    hiking[0] = obj.getString("hiking");
                    gymnastics[0] = obj.getString("gymnastics");
                    tennis[0] = obj.getString("tennis");
                    golf[0] = obj.getString("golf");
                    intent.putExtra("running", running[0]);
                    intent.putExtra("dancing", dancing[0]);
                    intent.putExtra("boxing", boxing[0]);
                    intent.putExtra("baseball", baseball[0]);
                    intent.putExtra("basketball", basketball[0]);
                    intent.putExtra("football", football[0]);
                    intent.putExtra("swimming", swimming[0]);
                    intent.putExtra("skiing", skiing[0]);
                    intent.putExtra("hiking", hiking[0]);
                    intent.putExtra("gymnastics", gymnastics[0]);
                    intent.putExtra("tennis", tennis[0]);
                    intent.putExtra("golf", golf[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            intent.putExtra("destination", destination);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_tracker);
        getSupportActionBar().hide();
    }

    public void goWrite(View view) {
            destination = view.getTag().toString();
            shared = getSharedPreferences("com.lau.finalproject", Context.MODE_PRIVATE);
            user_id = shared.getString("id", "");
            picked_date = shared.getString("chosen_date", "");
            String url = "http://10.31.195.219/Final/Backend/track_your_sport.php";
            DownloadTask task = new DownloadTask();
            task.execute(user_id, picked_date, url);
        }

    public void goWater(View view) {
            startActivity(new Intent(SportTracker.this, WaterTracker.class));
    }

    public void goFood(View view) {
            startActivity(new Intent(SportTracker.this, FoodTracker.class));
    }

    public void goUser(View view) {
            startActivity(new Intent(SportTracker.this, UserProfile.class));
    }

    public void goHome(View view) {
            startActivity(new Intent(SportTracker.this, HomeActivity.class));
    }
}