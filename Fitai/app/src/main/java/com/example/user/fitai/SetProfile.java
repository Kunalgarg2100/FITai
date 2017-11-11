package com.example.user.fitai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.fitai.adapter.MpagerAdapter;
import com.facebook.login.Login;

import java.util.Calendar;

//import static com.example.user.fitai.R.id.thing_proto;


public class SetProfile extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mPager;
    private int[] layouts = {R.layout.activity_screen1, R.layout.activity_screen2, R.layout.activity_screen3};
    private MpagerAdapter mpagerAdapter;
    private LinearLayout Dots_layout;
    private ImageView[] dots;
    private Button BnNext, BnSkip;
    public SharedPreferences sharedprefs;
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;
    public String weight_unit, weight_val, height_unit, height_val, user, age_val;
    //TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_set_profile);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts,this,SetProfile.this);
        mPager.setAdapter(mpagerAdapter);
        sharedprefs = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedprefs.edit();
        Dots_layout = (LinearLayout)findViewById(R.id.dotsLayout);
        BnNext = (Button)findViewById(R.id.bnNext);
        BnSkip = (Button)findViewById(R.id.bnSkip);
        BnNext.setOnClickListener(this);
        BnSkip.setOnClickListener(this);
        createDots(0);


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length-1){
                    SharedPreferences sharedprefs = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                    String weight_unit = sharedprefs.getString(LoginActivity.WEIGHT_UNIT, "");
                    String weight_val = sharedprefs.getString(LoginActivity.WEIGHT_VAL, "");
                    String height_unit = sharedprefs.getString(LoginActivity.HEIGHT_UNIT, "");
                    String height_val = sharedprefs.getString(LoginActivity.HEIGHT_VAL, "");
                    String age_val = sharedprefs.getString(LoginActivity.DOB, "");
                    String gender = sharedprefs.getString(LoginActivity.Gender, "");
                    String place = sharedprefs.getString(LoginActivity.PLACE, "");
                    Log.d("gender",gender);
                    Log.d("weight_unit",weight_unit);
                    Log.d("weight_val",weight_val);
                    Log.d("height_unit",height_unit);
                    Log.d("height_val",height_val);
                    Log.d("age_val",age_val);
                    if(weight_unit == null || weight_val == null || height_unit==null || height_val == null || age_val==null || gender==null || place == null){
                        BnNext.setVisibility(View.INVISIBLE);
                        //BnNext.setClickable(false);
                    }else
                        BnNext.setText("Start");
                    BnSkip.setVisibility(View.INVISIBLE);
                }
                else{
                    BnNext.setText("Next");
                    BnSkip.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void createDots(int current_position)
    {
        if(Dots_layout!=null)
            Dots_layout.removeAllViews();

        dots = new ImageView[layouts.length];

        for(int i = 0; i<layouts.length; i++) {

            dots[i] = new ImageView(this);
            if (i == current_position) {

                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));

            }
            else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            Dots_layout.addView(dots[i], params);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bnNext:
                loadNextSlide();
                break;

            case R.id.bnSkip:
                loadHome();
                break;
        }

    }

    private void loadHome(){
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(LoginActivity.Gender);
        editor.remove(LoginActivity.WEIGHT_VAL);
        editor.remove(LoginActivity.WEIGHT_UNIT);
        editor.remove(LoginActivity.HEIGHT_VAL);
        editor.remove(LoginActivity.HEIGHT_UNIT);
        editor.remove(LoginActivity.DOB);
        editor.remove(LoginActivity.PLACE);

        editor.commit();
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    private void loadGoals(){
        SharedPreferences sharedprefs = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String weight_unit = sharedprefs.getString(LoginActivity.WEIGHT_UNIT, "");
        String weight_val = sharedprefs.getString(LoginActivity.WEIGHT_VAL, "");
        String height_unit = sharedprefs.getString(LoginActivity.HEIGHT_UNIT, "");
        String height_val = sharedprefs.getString(LoginActivity.HEIGHT_VAL, "");
        String age_val = sharedprefs.getString(LoginActivity.DOB, "");
        String gender = sharedprefs.getString(LoginActivity.Gender, "");
        String place = sharedprefs.getString(LoginActivity.PLACE, "");
        Log.d("gender",gender);
        Log.d("weight_unit",weight_unit);
        Log.d("weight_val",weight_val);
        Log.d("height_unit",height_unit);
        Log.d("height_val",height_val);
        Log.d("age_val",age_val);
        Log.d("place",age_val);
        if(!weight_unit.equals("") && !weight_val.equals("") && !height_unit.equals("") && !height_val.equals("") && !age_val.equals("") && !gender.equals("") && !place.equals("")) {
            startActivity(new Intent(this, GoalsActivity.class));
            finish();
        }else{
            Toast.makeText(SetProfile.this, "Complete your Profile first", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadNextSlide(){
        int next_slide = mPager.getCurrentItem()+1;
        if(next_slide<layouts.length){
            mPager.setCurrentItem(next_slide);
        }
        else{
            loadGoals();
        }
    }

    public void dateUpdate(View view) {
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialog(DIALOG_ID);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SetProfile.this, Dashboard.class));
        finish();
    }
}
