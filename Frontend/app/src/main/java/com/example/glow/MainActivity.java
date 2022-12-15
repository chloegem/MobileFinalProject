package com.example.glow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Thread thread = new Thread() {

            public void run(){
                try{
                    sleep(2000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    startActivity(new Intent(MainActivity.this,Login.class));
                }
            }
        };
        thread.start();
    }
}