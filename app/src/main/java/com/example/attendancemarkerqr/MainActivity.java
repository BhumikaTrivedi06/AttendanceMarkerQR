package com.example.attendancemarkerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(MainActivity.this , ChoicePage.class);
        Timer timer = new Timer();              //for splash screen
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                //      finish();
            }
        },3000);            //milisecond

    }
}

//Intent intent_name = new Intent(Present_Activity_name.this,next_Activity_name.class);
