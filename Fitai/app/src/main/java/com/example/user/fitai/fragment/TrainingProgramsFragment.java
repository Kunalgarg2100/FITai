package com.example.user.fitai.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user.fitai.adapter.CustomGrid;
import com.example.user.fitai.R;

public class TrainingProgramsFragment extends TabsFragment{
    GridView grid;
    public TrainingProgramsFragment() {
    }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_training_programs, container, false);
        CustomGrid adapter = new CustomGrid(getActivity(), web, imageId);
        grid = view.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        return view;

    }
}