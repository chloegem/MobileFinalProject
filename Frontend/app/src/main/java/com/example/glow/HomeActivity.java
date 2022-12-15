package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.text.DecimalFormat;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    String [] weight,height;
    double bmi_val, weight_val, height_val;
    String user_id, bmi, picked_date, date_display;
    TextView select_date, bmi_analysis, bmi_result;
    SharedPreferences shared;
    DatePickerDialog date_picker_dialog;
    Button date_button;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String first_param = params[0];

            URL url;
            HttpURLConnection http;

            try{
                url = new URL(params[1]);
                http = (HttpURLConnection) url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream out_stream = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out_stream, "UTF-8"));
                String post1 = URLEncoder.encode("user_id", "UTF-8")+"="+ URLEncoder.encode(first_param, "UTF-8");
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
            super.onPostExecute(result);
            if(result.equals("Invalid user.")){
                Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONArray array = new JSONArray(result);
                    ArrayList<Object> list = new ArrayList<>();
                    JSONObject obj;

                    for (int i = 0; i < array.length(); i ++){
                        list.add(array.get(i));
                    }
                    weight = new String[array.length()];
                    height = new String[array.length()];

                    obj = (JSONObject) array.get(0);
                    weight[0] = obj.getString("weight");
                    height[0] = obj.getString("height");

                    weight_val = Double.parseDouble(weight[0]);
                    height_val = Double.parseDouble(height[0]);
                    DecimalFormat df = new DecimalFormat("#.###");
                    bmi = df.format(weight_val/((height_val/100)*(height_val/100)));
                    bmi_result.setText("Your BMI is "+ bmi);
                    bmi_val = Double.parseDouble(bmi);

                    // Analyzing the bmi result
                    if (bmi_val < 18){
                        bmi_analysis.setText("You are Under Weight");
                    }
                    else if(bmi_val <= 25){
                        bmi_analysis.setText("Your are Normal");
                    }
                    else if(bmi_val <= 28){
                        bmi_analysis.setText("You are Over Weight");
                    }
                    else{
                        bmi_analysis.setText("You are Malnourished");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        bmi_analysis = (TextView) findViewById(R.id.weight_status);
        bmi_result = (TextView) findViewById(R.id.bmi_status);
        date_button = findViewById(R.id.datePickerButton);
        select_date = findViewById(R.id.selectdate);
    }

    public void goWater(View view){
    }
    public void goFood(View view){
    }
    public void goSport(View view){
    }
    public void goUser(View view){
    }
}