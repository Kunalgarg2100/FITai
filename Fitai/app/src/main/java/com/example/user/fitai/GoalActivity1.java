package com.example.user.fitai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.fitai.adapter.CustomGoalsAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoalActivity1 extends AppCompatActivity {

    ListView list;
    Button setGoals;
    List<String> Goals = new ArrayList<>();
    //String[] simpleArray = new String[ Goals.size() ];
    //Goals.toArray(simpleArray);
    String[] itemname = {
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

    Integer[] imgid = {
            R.drawable.pilates,
            R.drawable.zumba,
            R.drawable.suryanamaskar,
            R.drawable.aerobics,
            R.drawable.yoga,
            R.drawable.brainyoga,
            R.drawable.deskyoga,
            R.drawable.onflightyoga,
            R.drawable.meditation,
            R.drawable.yoga,
            R.drawable.zumba,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        CustomGoalsAdapter adapter = new CustomGoalsAdapter(this, itemname, imgid);
        setGoals = (Button) findViewById(R.id.set_goals);
        setGoals.setVisibility(View.GONE);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[position].trim();
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                if (Goals.contains(Slecteditem)) {
                    parent.getChildAt(position).setBackgroundResource(R.drawable.list_element);
                    Goals.remove(Slecteditem);
                } else {
                    parent.getChildAt(position).setBackgroundResource(R.drawable.active_list_element);
                    Goals.add(Slecteditem);
                }
                if (Goals.size() > 0) {
                    setGoals.setVisibility(View.VISIBLE);
                } else {
                    setGoals.setVisibility(View.GONE);
                }
            }
        });

        setGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoalActivity1.this, RecommendActivity.class);
                //String str = String.join(",", Goals);
                Bundle b = new Bundle();
                String[] array = Goals.toArray(new String[0]);
                b.putStringArray("goals", array);
                intent.putExtras(b);
                startActivity(intent);
                //finish();
            }
        });
    }
}
