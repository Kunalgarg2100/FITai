package com.example.user.fitai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.fitai.adapter.CustomGoalsAdapter;
import com.example.user.fitai.adapter.CustomListAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecommendActivity extends AppCompatActivity {

    ListView list;
    //Button setGoals;
    //List<String> Goals = new ArrayList<>();
    //String[] simpleArray = new String[ Goals.size() ];
    //Goals.toArray(simpleArray);
    String[] itemname ={
            "Yoga",
            "Pilates",
            "Zumba",
            "Aerobics"
    };

    Integer[] imgid={
            R.drawable.body1,
            R.drawable.body2,
            R.drawable.body3,
            R.drawable.body4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        CustomGoalsAdapter adapter=new CustomGoalsAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
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
