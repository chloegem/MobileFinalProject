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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserProfile extends AppCompatActivity {

    SharedPreferences shared;
    String user_id, updated_birth_date, updated_email, updated_gender, updated_weight, updated_height;
    String [] first_name, last_name, email, gender, weight, height, date_of_birth;
    TextView username, result_text;
    EditText birth_date_text, mail_text, gender_text,weight_text, height_text;

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
            if(result.equals("Invalid user")){
                Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONArray array = new JSONArray(result);
                    ArrayList<Object> list = new ArrayList<>();
                    JSONObject obj;

                    for (int i = 0; i < array.length(); i ++){
                        list.add(array.get(i));
                    }
                    first_name = new String[array.length()];
                    last_name = new String[array.length()];
                    email = new String[array.length()];
                    gender = new String[array.length()];
                    weight = new String[array.length()];
                    height = new String[array.length()];
                    date_of_birth = new String[array.length()];
                    obj = (JSONObject) array.get(0);
                    first_name[0] = obj.getString("first_name");
                    last_name[0] = obj.getString("last_name");
                    email[0] = obj.getString("email");
                    gender[0] = obj.getString("gender");
                    weight[0] = obj.getString("weight");
                    height[0] = obj.getString("height");
                    date_of_birth[0] = obj.getString("date_of_birth");
                    username.setText(first_name[0]+" "+last_name[0]);
                    birth_date_text.setText(date_of_birth[0]);
                    mail_text.setText(email[0]);
                    gender_text.setText(gender[0]);
                    weight_text.setText(weight[0]);
                    height_text.setText(height[0]);
                    birth_date_text.setText(date_of_birth[0]);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public class DownloadTask2 extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String user_id_param = params[0];
            String date_of_birth_param= params[1];
            String email_param= params[2];
            String gender_param= params[3];
            String height_param= params[4];
            String weight_param= params[5];

            URL url;
            HttpURLConnection http;

            try{
                url = new URL(params[6]);
                http = (HttpURLConnection) url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream out_stream = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out_stream, "UTF-8"));
                String post1 = URLEncoder.encode("user_id", "UTF-8")+"="+ URLEncoder.encode(user_id_param, "UTF-8")+"&"+URLEncoder.encode("date_of_birth", "UTF-8")+"="+ URLEncoder.encode(date_of_birth_param, "UTF-8")+"&"+URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(email_param, "UTF-8")+"&"+URLEncoder.encode("gender", "UTF-8")+"="+ URLEncoder.encode(gender_param, "UTF-8")+"&"+URLEncoder.encode("weight", "UTF-8")+"="+ URLEncoder.encode(weight_param, "UTF-8")+"&"+URLEncoder.encode("height", "UTF-8")+"="+ URLEncoder.encode(height_param, "UTF-8");
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
            if(result.equals("This email already exist")){
                result_text.setText(result);
                Toast.makeText(getApplicationContext(),"This email already exist", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().hide();
        username = (TextView) findViewById(R.id.show_user);
        result_text = (TextView) findViewById(R.id.result);
        birth_date_text = (EditText) findViewById(R.id.change_dob);
        mail_text = (EditText) findViewById(R.id.change_email);
        gender_text = (EditText) findViewById(R.id.change_gender);
        weight_text = (EditText) findViewById(R.id.change_weight);
        height_text = (EditText) findViewById(R.id.change_height);

        shared = getSharedPreferences("com.lau.finalproject", Context.MODE_PRIVATE);
        user_id = shared.getString("id","");
        String url = "http://78.108.167.52/Final/Backend/getProfile.php";
        DownloadTask task = new DownloadTask();
        task.execute(user_id,url);
    }
    public void goToHome(View v){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
    public void goToWaterTracking(View v){
        Intent intent = new Intent(getApplicationContext(), WaterTracker.class);
        startActivity(intent);
    }
    public void goToFoodTracking(View v){
        Intent intent = new Intent(getApplicationContext(), FoodTracker.class);
        startActivity(intent);
    }
    public void goToExerciseTracking(View v){
        Intent intent = new Intent(getApplicationContext(), SportTracker.class);
        startActivity(intent);
    }

    public void editInfo(View v){
        updated_birth_date = birth_date_text.getText().toString();
        updated_email = mail_text.getText().toString();
        updated_gender = gender_text.getText().toString();
        updated_weight = weight_text.getText().toString();
        updated_height = height_text.getText().toString();
        if(!(updated_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") || !(updated_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]") || updated_email.length() == 0))){
            result_text.setText("Invalid email format!");
        }else if(!isValidDate(updated_birth_date) || updated_birth_date.length() == 0){
            result_text.setText("Make sure the birth date format is of the form dd/MM/yyyy");
        }else if(!updated_gender.equalsIgnoreCase("male") && !updated_gender.equalsIgnoreCase("female") && !updated_gender.equalsIgnoreCase("none")) {
            result_text.setText("Invalid gender format! Please enter female, male or none");
        }else if(!updated_weight.matches( "[0-9]*" ) || updated_weight.length() == 0){
            result_text.setText("Invalid weight format!");
        }else if(!updated_height.matches( "[0-9]*" ) || updated_height.length() == 0){
            result_text.setText("Invalid height format!");
        } else {
            shared = getSharedPreferences("com.lau.finalproject", Context.MODE_PRIVATE);
            user_id = shared.getString("id", "");

            String url = "http://78.108.167.52/Final/Backend/updateProfile.php";
            UserProfile.DownloadTask2 task = new UserProfile.DownloadTask2();
            task.execute(user_id, updated_birth_date, updated_email, updated_gender, updated_weight, updated_height, url);
        }
    }
    public static boolean isValidDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date d1 = sdf.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}