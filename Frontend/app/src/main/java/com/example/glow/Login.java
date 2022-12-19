package com.example.glow;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

                OutputStream out = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));

                String post = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(first_param, "UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(second_param, "UTF-8");
                bw.write(post);
                bw.flush();
                bw.close();
                out.close();

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

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if(result.equals("Incorrect Username or password")){
                Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONArray array = new JSONArray(result);
                    ArrayList<Object> list = new ArrayList<>();
                    JSONObject obj;

                    for (int i = 0; i < array.length(); i ++){
                        list.add(array.get(i));
                    }

                    user_id = new String[array.length()];
                    obj = (JSONObject) array.get(0);
                    user_id[0] = obj.getString("user_id");

                    shared = getApplicationContext().getSharedPreferences("com.lau.finalproject", Context.MODE_PRIVATE);
                    shared.edit().putString("id",user_id[0]).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this, HomeActivity.class));
            }
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.loginBtn);
        text = (TextView) findViewById(R.id.textView);
    }

    public void register_onclick(View view){
        startActivity(new Intent(Login.this, Register.class));
    }

    public void login_onclick(View view) {
        String input_email = email.getText().toString();
        String input_password = password.getText().toString();

        if (input_email.equals("") || input_password.equals("")) {
            Toast.makeText(getApplicationContext(), "Missing Info", Toast.LENGTH_LONG).show();
        } else {
            String url = "http://10.31.195.219/Final/Backend/login.php";
            DownloadTask task = new DownloadTask();
            task.execute(input_email, input_password, url);
        }
    }
}