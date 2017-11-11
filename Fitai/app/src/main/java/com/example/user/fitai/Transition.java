package com.example.user.fitai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Transition extends AppCompatActivity {
    private static int time_out = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Transition.this, LogInOrNot.class);
                startActivity(intent);
                finish();
            }
        }, time_out);
        Log.d("hello", "mc");
    }
}
