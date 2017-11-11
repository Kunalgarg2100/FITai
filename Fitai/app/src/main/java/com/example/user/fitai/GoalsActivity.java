package com.example.user.fitai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.fitai.adapter.CustomGoalsAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    ListView list;
    Button setGoals;
    List<String> Goals = new ArrayList<>();
    //String[] simpleArray = new String[ Goals.size() ];
    //Goals.toArray(simpleArray);
    String[] itemname = {
            "Weight Loss",
            "Mental Health",
            "Medical Condition",
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
        SharedPreferences sharedprefs = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String weight_unit = sharedprefs.getString(LoginActivity.WEIGHT_UNIT, "");
        String weight_val = sharedprefs.getString(LoginActivity.WEIGHT_VAL, "");
        String height_unit = sharedprefs.getString(LoginActivity.HEIGHT_UNIT, "");
        String height_val = sharedprefs.getString(LoginActivity.HEIGHT_VAL, "");
        String age_val = sharedprefs.getString(LoginActivity.DOB, "");
        String gender = sharedprefs.getString(LoginActivity.Gender, "");
        String user = sharedprefs.getString(LoginActivity.Name, "");
        Log.d("gender", gender);
        Log.d("weight_unit", weight_unit);
        Log.d("weight_val", weight_val);
        Log.d("height_unit", height_unit);
        Log.d("height_val", height_val);
        Log.d("age_val", age_val);

        Double weight = Double.parseDouble(weight_val);
        if (weight_unit.equals("lb"))
            weight = weight / 2.2;

        Double height = Double.parseDouble(height_val);
        if (height_unit.equals("inch"))
            height = height * 2.54;

        /*Date age_date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        try {
            age_date = dateFormat.parse(age_val);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        /*String[] timef=age_val.split("/");

        int hour=Integer.parseInt(timef[0]);
        int minute=Integer.parseInt(timef[1]);
        int second=Integer.parseInt(timef[2]);

        int temp;
        temp = second + (60 * minute) + (3600 * hour);*/
        Log.d("height", height.toString());
        Log.d("weight", weight.toString());
        Log.d("age_val", age_val);
        Log.d("gender", gender);
        Log.d("user", user);

        LoginActivity.myDB.updateProfile(height, weight, age_val, gender, user);

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
                Intent intent = new Intent(GoalsActivity.this, RecommendActivity.class);
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
