package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
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

public class WaterTracker extends AppCompatActivity {


    TextView cups;
    String user_id, picked_date, total_nb;
    String [] nb_of_glasses;
    int nb_of_cups = 0;
    SharedPreferences shared;
    ImageView empty_cup1, empty_cup2, empty_cup3, empty_cup4, empty_cup5, empty_cup6, empty_cup7, empty_cup8;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String first_param = params[0];
            String second_param= params[1];

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
            if(result.equals("0")){
                nb_of_cups = 0;
                Toast.makeText(getApplicationContext(),"No data stored on that date", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONArray array = new JSONArray(result);
                    ArrayList<Object> list = new ArrayList<>();
                    JSONObject obj;

                    for (int i = 0; i < array.length(); i ++){
                        list.add(array.get(i));
                    }
                    nb_of_glasses = new String[array.length()];
                    obj = (JSONObject) array.get(0);
                    nb_of_glasses[0] = obj.getString("nb_of_glasses");
                    nb_of_cups = Integer.parseInt(nb_of_glasses[0]);

                    if (nb_of_cups == 1){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("1/8");
                    }else if (nb_of_cups == 2){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        empty_cup2.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("2/8");
                    }else if (nb_of_cups == 3){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        empty_cup2.setImageResource(R.drawable.water_filled_icon);
                        empty_cup3.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("3/8");
                    }
                    else if (nb_of_cups == 4){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        empty_cup2.setImageResource(R.drawable.water_filled_icon);
                        empty_cup3.setImageResource(R.drawable.water_filled_icon);
                        empty_cup4.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("4/8");
                    }else if (nb_of_cups == 5){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        empty_cup2.setImageResource(R.drawable.water_filled_icon);
                        empty_cup3.setImageResource(R.drawable.water_filled_icon);
                        empty_cup4.setImageResource(R.drawable.water_filled_icon);
                        empty_cup5.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("5/8");
                    }else if (nb_of_cups == 6){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        empty_cup2.setImageResource(R.drawable.water_filled_icon);
                        empty_cup3.setImageResource(R.drawable.water_filled_icon);
                        empty_cup4.setImageResource(R.drawable.water_filled_icon);
                        empty_cup5.setImageResource(R.drawable.water_filled_icon);
                        empty_cup6.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("6/8");
                    }else if (nb_of_cups == 7){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        empty_cup2.setImageResource(R.drawable.water_filled_icon);
                        empty_cup3.setImageResource(R.drawable.water_filled_icon);
                        empty_cup4.setImageResource(R.drawable.water_filled_icon);
                        empty_cup5.setImageResource(R.drawable.water_filled_icon);
                        empty_cup6.setImageResource(R.drawable.water_filled_icon);
                        empty_cup7.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("7/8");
                    }
                    else if (nb_of_cups == 8){
                        empty_cup1.setImageResource(R.drawable.water_filled_icon);
                        empty_cup2.setImageResource(R.drawable.water_filled_icon);
                        empty_cup3.setImageResource(R.drawable.water_filled_icon);
                        empty_cup4.setImageResource(R.drawable.water_filled_icon);
                        empty_cup5.setImageResource(R.drawable.water_filled_icon);
                        empty_cup6.setImageResource(R.drawable.water_filled_icon);
                        empty_cup7.setImageResource(R.drawable.water_filled_icon);
                        empty_cup8.setImageResource(R.drawable.water_filled_icon);
                        cups.setText("8/8");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public class DownloadTask2 extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String first_param = params[0];
            String second_param = params[1];
            String third_param = params[2];

            URL url;
            HttpURLConnection http;

            try {
                url = new URL(params[3]);
                http = (HttpURLConnection) url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream out_stream = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out_stream, "UTF-8"));
                String post1 = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(first_param, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(second_param, "UTF-8") + "&" + URLEncoder.encode("nb_of_glasses", "UTF-8") + "=" + URLEncoder.encode(third_param, "UTF-8");
                bw.write(post1);
                bw.flush();
                bw.close();
                out_stream.close();
                InputStream in_stream = http.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in_stream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    result += line;
                }
                br.close();
                in_stream.close();
                http.disconnect();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);
    }
}