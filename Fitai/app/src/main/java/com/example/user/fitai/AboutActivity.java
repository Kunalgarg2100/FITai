package com.example.user.fitai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class AboutActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView about_title  = (TextView) findViewById(R.id.app_about_header);
        TextView about_description  = (TextView) findViewById(R.id.app_about_description);
        ImageView about_fitailogo   = (ImageView) findViewById(R.id.app_about_image);

        about_title.setText("This is Fitai . Welcome to Fitai .  Be like Fitai");
        about_description.setText(" Hello guys this is the app before the launch of the actusl fitai device ");
    }
}