package com.example.user.fitai.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.fitai.LoginActivity;
import com.example.user.fitai.R;
import com.example.user.fitai.SetProfile;
import com.example.user.fitai.WorkoutActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

public class HomeFragment extends TabsFragment {
    TextView setProfile, userInfo;
    ImageView img;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_home, container, false);
        setProfile = view.findViewById(R.id.set_profile);
        userInfo = view.findViewById(R.id.user_info);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String uname = sharedpreferences.getString(LoginActivity.Name, "");
        String height = sharedpreferences.getString(LoginActivity.HEIGHT_VAL, "");
        String weight = sharedpreferences.getString(LoginActivity.WEIGHT_VAL, "");
        String gender = sharedpreferences.getString(LoginActivity.Gender, "");
        String dob = sharedpreferences.getString(LoginActivity.DOB, "");
        Log.d("name", uname);
        Log.d("height", height);
        Log.d("weight", weight);
        Log.d("gender", gender);
        Log.d("dob", dob);

        if (!height.equals("") && !weight.equals("") && !gender.equals("") && !dob.equals("")) {
            setProfile.setVisibility(view.GONE);
            userInfo.setText("Height " + height + "\n" + "Weight " + weight + "\n" + "Gender " + gender + "\n" + "Dob " + dob);
        } else {
            userInfo.setVisibility(view.GONE);
            setProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), SetProfile.class));
                    getActivity().finish();
                }
            });
        }
        BarChart chart = view.findViewById(R.id.chart);
        img = view.findViewById(R.id.imageView2);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Yoga");
        chart.animateXY(2000, 2000);
        chart.invalidate();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        CircularProgressBar circularProgressBar = view.findViewById(R.id.yourCircularProgressbar);

        circularProgressBar.setColor(ContextCompat.getColor(getContext(), R.color.progressBarColor));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundProgressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkoutActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) view.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(8.000f, 0); // Day1
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(7.000f, 1);  // Day2
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(10.000f, 2);  // Day3
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(5.000f, 3);  // Day4
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(9.000f, 4);  // Day5
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(7.000f, 5);  // Day6
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(7.000f, 6);  // Day7
        valueSet1.add(v1e7);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Yoga");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Day1");
        xAxis.add("Day2");
        xAxis.add("Day3");
        xAxis.add("Day4");
        xAxis.add("Day5");
        xAxis.add("Day6");
        xAxis.add("Day7");
        return xAxis;
    }
}
