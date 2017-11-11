package com.example.user.fitai;

/**
 * Created by kunal on 2/11/17.
 */


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        ab.setTitle("Graph of " + title);
        Log.d("Title", title);
        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data;
        if (title.equals("Pushups"))
            data = new BarData(getXAxisValues(), getDataSet());
        else
            data = new BarData(getXAxisValues1(), getDataSet1());

        chart.setData(data);
        chart.setDescription(title);
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(10.000f, 0); // Day1
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(6.000f, 1);  // Day2
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(5.000f, 2);  // Day3
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(3.000f, 3);  // Day4
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(9.000f, 4);  // Day5
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(7.000f, 5);  // Day6
        valueSet1.add(v1e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, title);
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<BarDataSet> getDataSet1() {
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
        BarEntry v1e8 = new BarEntry(10.000f, 7);  // Day8
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(5.000f, 8);  // Day9
        valueSet1.add(v1e9);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, title);
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues1() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Day1");
        xAxis.add("Day2");
        xAxis.add("Day3");
        xAxis.add("Day4");
        xAxis.add("Day5");
        xAxis.add("Day6");
        xAxis.add("Day7");
        xAxis.add("Day8");
        xAxis.add("Day9");
        return xAxis;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Day1");
        xAxis.add("Day2");
        xAxis.add("Day3");
        xAxis.add("Day4");
        xAxis.add("Day5");
        xAxis.add("Day6");
        return xAxis;
    }
}