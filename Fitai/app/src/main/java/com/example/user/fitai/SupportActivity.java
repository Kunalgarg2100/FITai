package com.example.user.fitai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        TextView textView = (TextView) findViewById(R.id.textView8);
        textView.setText("Variance.AI\n" +
                "THUB IIIT-H Campus\n" +
                "Gachchibowli Hyderabad TS 500032 India");
    }
}
