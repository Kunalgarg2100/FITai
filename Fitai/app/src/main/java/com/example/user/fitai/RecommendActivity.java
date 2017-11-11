package com.example.user.fitai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.fitai.adapter.CustomGoalsAdapter;
import com.example.user.fitai.adapter.CustomListAdapter;
import com.example.user.fitai.adapter.Workout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendActivity extends AppCompatActivity {

    ListView list;
    //Button setGoals;
    //List<String> Goals = new ArrayList<>();
    //String[] simpleArray = new String[ Goals.size() ];
    //Goals.toArray(simpleArray);
    String[] itemname = {
            "Yoga",
            "Pilates",
            "Zumba",
            "Aerobics"
    };

    Integer[] imgid = {
            R.drawable.deskyoga,
            R.drawable.onflightyoga,
            R.drawable.meditation,
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        Bundle b = this.getIntent().getExtras();
        String[] array = b.getStringArray("goals");
        //Log.d("string", array[0]);
        List<String> work = new ArrayList<>();

        if (Arrays.asList(array).contains("Mental Health")) {
            //itemname = new String[]{"Brain Yoga", "Meditation"};
            if (!work.contains("Brain Yoga")) {
                work.add("Brain Yoga");
            }
            if (!work.contains("Meditation")) {
                work.add("Meditation");
            }
        }

        if (Arrays.asList(array).contains("Weight Loss")) {
            if (!work.contains("Pilates")) {
                work.add("Pilates");
            }
            if (!work.contains("Zumba")) {
                work.add("Zumba");
            }
            if (!work.contains("Aerobics")) {
                work.add("Aerobics");
            }
            if (!work.contains("Suryanamaskar Yoga")) {
                work.add("Suryanamaskar Yoga");
            }
        }

        if (Arrays.asList(array).contains("Medical Condition")) {
            if (!work.contains("Meditation")) {
                work.add("Meditation");
            }
        }

        if (Arrays.asList(array).contains("Stress Relief")) {
            if (!work.contains("Zumba")) {
                work.add("Zumba");
            }
            if (!work.contains("Face Yoga")) {
                work.add("Face Yoga");
            }
            if (!work.contains("Meditation")) {
                work.add("Meditation");
            }
        }

        if (Arrays.asList(array).contains("Physical Fitness")) {
            if (!work.contains("Pilates")) {
                work.add("Pilates");
            }
            if (!work.contains("Zumba")) {
                work.add("Zumba");
            }
            if (!work.contains("Suryanamaskar Yoga")) {
                work.add("Suryanamaskar Yoga");
            }
            if (!work.contains("Brain Yoga")) {
                work.add("Brain Yoga");
            }
        }

        if (Arrays.asList(array).contains("Spiritual Development")) {
            if (!work.contains("Meditation")) {
                work.add("Meditation");
            }
        }

        itemname = work.toArray(new String[0]);

        CustomGoalsAdapter adapter = new CustomGoalsAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list);


        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[position].trim();
                Intent intent = new Intent(RecommendActivity.this, ProgramActivity.class);
                intent.putExtra("workout", Slecteditem);
                startActivity(intent);
                //finish();
            }
        });
    }
}
