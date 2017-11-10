package com.example.user.fitai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.solver.Goal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.fitai.adapter.CustomGoalsAdapter;
import com.example.user.fitai.adapter.CustomListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GoalActivity1 extends AppCompatActivity {

    ListView list;
    Button setGoals;
    List<String> Goals = new ArrayList<>();
    //String[] simpleArray = new String[ Goals.size() ];
    //Goals.toArray(simpleArray);
    String[] itemname ={
            "Weight Loss",
            "Mental Health",
            "Medical condition",
            "Stress Relief",
            "Physical Fitness",
            "Spiritual Development",
            "Yoga as Therapy",
            "Strength",
            "General Fitness",
            "Flexibility",
            "Physical Fitness"
    };

    Integer[] imgid={
            R.drawable.body,
            R.drawable.body1,
            R.drawable.body2,
            R.drawable.body3,
            R.drawable.body3,
            R.drawable.body3,
            R.drawable.body3,
            R.drawable.body3,
            R.drawable.body3,
            R.drawable.body3,
            R.drawable.body4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        CustomGoalsAdapter adapter=new CustomGoalsAdapter(this, itemname, imgid);
        setGoals = (Button) findViewById(R.id.set_goals);
        setGoals.setVisibility(View.GONE);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[position].trim();
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                if (Goals.contains(Slecteditem)) {
                    parent.getChildAt(position).setBackgroundResource(R.drawable.list_element);
                    Goals.remove(Slecteditem);
                }else{
                    parent.getChildAt(position).setBackgroundResource(R.drawable.active_list_element);
                    Goals.add(Slecteditem);
                }
                if(Goals.size()>0){
                    setGoals.setVisibility(View.VISIBLE);
                }else{
                    setGoals.setVisibility(View.GONE);
                }
            }
        });

        setGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoalActivity1.this, RecommendActivity.class);
                //String str = String.join(",", Goals);
                Bundle b=new Bundle();
                String[] array = Goals.toArray(new String[0]);
                b.putStringArray("goals", array);
                intent.putExtras(b);
                startActivity(intent);
                //finish();
            }
        });
    }
}
