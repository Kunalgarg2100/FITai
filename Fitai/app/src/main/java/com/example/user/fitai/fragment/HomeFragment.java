package com.example.user.fitai.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.fitai.R;
import com.example.user.fitai.SetProfile;
import com.example.user.fitai.adapter.Workout;
import com.example.user.fitai.adapter.WorkoutsAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends TabsFragment{
    TextView setProfile,name,scdule;
    private RecyclerView recyclerView;
    private WorkoutsAdapter adapter;
    private List<Workout> workoutList;

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
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);



        scdule = (TextView) view.findViewById(R.id.schedule);
        scdule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disp();
            }
        });


        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) view.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void disp() {
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int mnth = cal.get(Calendar.MONTH);
        int yr = cal.get(Calendar.YEAR);
        int dte = cal.get(Calendar.DATE);
        int tym = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        cal.set(yr, mnth, dte, tym, min);
        Uri uri = Uri.parse("content://com.android.calendar/time/"
                + String.valueOf(cal.getTimeInMillis()));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        // Use the Calendar app to view the time.
        startActivity(intent);
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(10.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(6.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(5.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(3.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(9.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(7.000f, 5); // Jun
        valueSet1.add(v1e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }
}
