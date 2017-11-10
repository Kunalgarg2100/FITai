package com.example.user.fitai.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.fitai.ProgramActivity;
import com.example.user.fitai.R;
import com.example.user.fitai.SetProfile;
import com.example.user.fitai.adapter.CustomProgramAdapter;
import com.example.user.fitai.adapter.Workout;
import com.example.user.fitai.adapter.WorkoutsAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends TabsFragment{
    TextView setProfile;
    ListView list;
    String[] programs ={
            "21 Day Program",
            "45 Day Program",
            "60 Day Program"
    };
    String[] programsDes ={
            "21 Day Program description",
            "45 Day Program description",
            "60 Day Program description"
    };


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_home, container, false);

        setProfile = (TextView) view.findViewById(R.id.set_profile);

        setProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetProfile.class));
            }
        });
        
        BarChart chart = (BarChart) view.findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar)
                ;
       CircularProgressBar circularProgressBar = (CircularProgressBar)view.findViewById(R.id.yourCircularProgressbar);

        circularProgressBar.setColor(ContextCompat.getColor(getContext(), R.color.progressBarColor));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundProgressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms


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
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
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
