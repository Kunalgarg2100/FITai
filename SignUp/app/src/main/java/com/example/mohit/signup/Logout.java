package com.example.mohit.signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logout extends AppCompatActivity {
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        logout = (Button)findViewById(R.id.logout_btn);
        logout();
    }

    public void logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = Logout.this;
                SharedPreferences sharedpreferences = context.getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Logout.this, LoginActivity.class));
            }
        });
    }

}
