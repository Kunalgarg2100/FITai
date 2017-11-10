package com.example.user.fitai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.fitai.adapter.CustomGoalsAdapter;
import com.example.user.fitai.adapter.CustomProgramAdapter;

import java.io.IOException;
import java.io.InputStream;

public class ProgramActivity extends AppCompatActivity {

    ListView list;
    String[] programs ={
            "21 Day Program",
            "45 Day Program",
            "60 Day Program"
    };
    String[] programsDes ={
            "21 day program is mainly for new users who want to try out Fitai",
            "45 day program is mainly for users who are already familiar with Fitai",
            "60 day program is for users who already have tried out short programs and interested in more workouts"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        CustomProgramAdapter adapter=new CustomProgramAdapter(this, programs, programsDes);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = programs[position].trim();
                Intent intent = new Intent(ProgramActivity.this, Dashboard.class);
                intent.putExtra("workout", Slecteditem);
                startActivity(intent);
                finish();
            }
        });
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
