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
        TextView textView = (TextView) findViewById(R.id.travi_description);
        TextView textView1 = (TextView) findViewById(R.id.gruvi_description);
        int height = textView.getHeight();
        textView1.setHeight(height);

    }
}