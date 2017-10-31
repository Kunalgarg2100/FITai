package com.example.user.fitai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LogInOrNot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_log_in_or_not);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("nameKey",null);
        String password = sharedpreferences.getString("passKey",null);
        if(username != null){
            startActivity(new Intent(LogInOrNot.this, Dashboard.class));
            finish();
        } else{
            startActivity(new Intent(LogInOrNot.this, LoginActivity.class));
            finish();
        }
    }
}
