package com.example.user.fitai;

import android.content.Intent;
import android.support.constraint.solver.Goal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.fitai.adapter.CustomListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    ListView list;
    Button setGoals;
    List<String> Goals = new ArrayList<>();
    //String[] simpleArray = new String[ Goals.size() ];
    //Goals.toArray(simpleArray);
    String[] itemname ={
            "Flexibility",
            "General Fitness",
            "Stress Relief",
            "Improve overall health",
            "Physical Fitness",
            "Strength",
            "Mental Health",
            "Weight loss" ,
            "Spiritual Development" ,
            "Yoga Therepy"
    };

    Integer[] imgid={
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body,
            R.drawable.body
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
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
                startActivity(new Intent(GoalsActivity.this, RecommendActivity.class));
                finish();
            }
        });
    }
}
