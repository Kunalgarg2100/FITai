package com.example.user.fitai;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class TrainingPrograms extends AppCompatActivity {
    GridView grid;
    String[] web = {
            "Leg",
            "Shoulder",
            "Biceps",
            "Triceps",
            "Upper Leg",
            "Lower Leg",
            "Chest",
            "Abs",
            "Back",
            "Cardio",
            "Glutes",
            "Forearm",
            "Chest",
            "Chest",
            "Chest",
    } ;
    int[] imageId = {
            R.drawable.body1,
            R.drawable.body,
            R.drawable.body2,
            R.drawable.body3,
            R.drawable.body4,
            R.drawable.body5,
            R.drawable.body6,
            R.drawable.body7,
            R.drawable.body8,
            R.drawable.body9,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_programs);

        CustomGrid adapter = new CustomGrid(TrainingPrograms.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(TrainingPrograms.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }

}