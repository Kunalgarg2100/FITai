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
            "DeskYoga",
            "OnFlightYoga",
            "Meditation",
            "Yoga",
            "Zumba",
            "Pilates",
            "Zumba",
            "Suryanamaskar",
            "Aerobics",
            "Yoga",
            "Brainyoga",
            "Deskyoga",
    } ;
    int[] imageId = {
            R.drawable.deskyoga,
            R.drawable.onflightyoga,
            R.drawable.meditation ,
            R.drawable.yoga,
            R.drawable.zumba,
            R.drawable.pilates,
            R.drawable.zumba,
            R.drawable.suryanamaskar,
            R.drawable.aerobics,
            R.drawable.yoga,
            R.drawable.brainyoga,
            R.drawable.deskyoga,
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