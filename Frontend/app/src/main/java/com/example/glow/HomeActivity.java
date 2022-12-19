package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;

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

                    for (int i = 0; i < array.length(); i++){
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

        shared = getSharedPreferences("com.lau.finalproject", Context.MODE_PRIVATE);
        user_id = shared.getString("id","");
        String url = "http://10.31.195.219/Final/Backend/get_profile.php";
        HomeActivity.DownloadTask task = new HomeActivity.DownloadTask();
        task.execute(user_id,url);


        initDatePicker();

        if (picked_date != ""){
            date_button.setText(date_display);
        }
        else {
            date_button.setText(getTodaysDate());
        }
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                date_button.setText(date);
                picked_date = day + "" + month + "" + year;
                shared.edit().putString("chosen_date",picked_date).commit();
                date_button.setText(date);
                date_display = date;
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        date_picker_dialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year)
    {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        date_picker_dialog.show();
    }

    public void goWater(View view){
        startActivity(new Intent(HomeActivity.this, WaterTracker.class));
    }
    public void goFood(View view){
        startActivity(new Intent(HomeActivity.this, FoodTracker.class));
    }
    public void goSport(View view){
        startActivity(new Intent(HomeActivity.this, SportTracker.class));
    }
    public void goUser(View view){
        startActivity(new Intent(HomeActivity.this, UserProfile.class));
    }
}