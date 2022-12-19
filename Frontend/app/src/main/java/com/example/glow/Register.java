package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText fname,lname,email,dob,gender,height,weight,pass;
    TextView text;
    public class DownloadTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params) {
            String first_name = params[0];
            String last_name = params[1];
            String email = params[2];
            String date_of_birth = params[3];
            String gender = params[4];
            String password = params[5];
            String height = params[6];
            String weight = params[7];

            URL url;
            HttpURLConnection http;

            try {
                url = new URL(params[8]);
                http = (HttpURLConnection) url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream out_stream = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out_stream, "UTF-8"));
                String post1 = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(first_name, "UTF-8") + "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(last_name, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("date_of_birth", "UTF-8") + "=" + URLEncoder.encode(date_of_birth, "UTF-8") + "&" + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" + URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8") + "&" + URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8");
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

        protected void onPostExecute(String result){

            super.onPostExecute(result);
            if(result.equals("Account Created!")){
                Toast.makeText(getApplicationContext(),"Account Created!", Toast.LENGTH_LONG).show();
                Intent intentt = new Intent(Register.this, Login.class);
                startActivity(intentt);
            }
            else{
                text.setText(result);
                Toast.makeText(getApplicationContext(),"This account already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        fname = (EditText) findViewById(R.id.fname_register);
        lname = (EditText) findViewById(R.id.lname_register);
        email = (EditText) findViewById(R.id.email_register);
        gender = (EditText) findViewById(R.id.gender_register);
        dob = (EditText)findViewById(R.id.dob_register);
        height = (EditText) findViewById(R.id.height_register);
        weight = (EditText) findViewById(R.id.weight_register);
        pass = (EditText) findViewById(R.id.pass_register);
        text = (TextView) findViewById(R.id.text_signup);

    }

    public void onClick(View view){
            String entered_first_name = fname.getText().toString();
            String entered_last_name = lname.getText().toString();
            String entered_email = email.getText().toString();
            String entered_password = pass.getText().toString();
            String entered_gender = gender.getText().toString();
            String entered_birth = dob.getText().toString();
            String entered_weight = weight.getText().toString();
            String entered_height = height.getText().toString();
            String output = text.getText().toString();

            if(!entered_first_name.matches( "[a-zA-Z]*" ) || entered_first_name.length() == 0){
                text.setText("Invalid first name format!");
            }else if(!entered_last_name.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" ) || entered_last_name.length() == 0){
                text.setText("Invalid last name format!");
            }else if(!(entered_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") || !(entered_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]") || entered_email.length() == 0))){
                text.setText("Invalid email format!");
            }else if(entered_password.length()<8 || !isValidPassword(entered_password)){
                text.setText("Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
            }else if(!isValidDate(entered_birth) || entered_birth.length() == 0){
                text.setText("Invalid birth date format! Make sure it will be of the form dd/MM/yyyy");
            }else if(!entered_gender.equalsIgnoreCase("male") && !entered_gender.equalsIgnoreCase("female") && !entered_gender.equalsIgnoreCase("none")){
                text.setText("Invalid gender format! Please enter female, male or none");
            }else if(!entered_height.matches( "[0-9]*" ) || entered_height.length() == 0){
                text.setText("Invalid height format!");
            }else if(!entered_weight.matches( "[0-9]*" ) || entered_weight.length() == 0) {
                text.setText("Invalid weight format!");
            }else{
                String url = "http://10.31.195.219/Final/Backend/signup.php";
                DownloadTask register_task = new DownloadTask();
                register_task.execute(entered_first_name, entered_last_name, entered_email, entered_birth, entered_gender, entered_password,entered_height, entered_weight, url);
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

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}